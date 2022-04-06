package uz.pdp.cinemarest.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.entity.*;
import uz.pdp.cinemarest.entity.enums.TicketStatus;
import uz.pdp.cinemarest.projection.CustomTicketForCart;
import uz.pdp.cinemarest.repository.*;

import java.io.IOException;
import java.util.Optional;
import java.util.TimerTask;
import java.util.Timer;

@Service
public class TicketService {

    @Autowired
    WaitingPurchaseTimeRepository waitingPurchaseTimeRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    MovieSessionRepository movieSessionRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    AttachmentContentRepository attachmentContentRepository;


    @Autowired
    AttachmentRepository attachmentRepository;


    public byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }


    public HttpEntity<?> generationTicket(Integer movieSessionId, Integer seatId, Integer userId) throws IOException, WriterException {

        Optional<MovieSession> movieSessionRepositoryById = movieSessionRepository.findById(movieSessionId);
        Optional<Seat> seatOptional = seatRepository.findById(seatId);


        Optional<User> userOptional = userRepository.findById(userId);

        Double ticketPriceByMovieSessionIdAndSeatId = ticketRepository.getTicketPriceByMovieSessionIdAndSeatId(movieSessionId, seatId);

        Ticket ticket = new Ticket(movieSessionRepositoryById.get(), seatOptional.get(), null, ticketPriceByMovieSessionIdAndSeatId, TicketStatus.NEW, userOptional.get());
        byte[] qrCodeImage = getQRCodeImage(ticket.getSerialNumber(), 200, 200);
        Attachment attachment = new Attachment();
        attachment.setName(ticket.getSerialNumber());
        attachment.setContentType("image/png");
        AttachmentContent attachmentContent = new AttachmentContent(attachment, qrCodeImage);
        attachmentContentRepository.save(attachmentContent);
        attachmentRepository.save(attachment);
        ticket.setQrCode(attachment);
        boolean b = ticketRepository.existsBySeatId(seatId);
        if (b)
            return new ResponseEntity(new ApiResponse("wrong",
                    false, null), HttpStatus.BAD_REQUEST);
        else {
            ticketRepository.save(ticket);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    CustomTicketForCart ticketByIdForCart = ticketRepository.getTicketByIdForCart(ticket.getId());
                    try {
                        if (ticketByIdForCart.getTicketStatus().equals(TicketStatus.NEW)) {
                            ticketRepository.deleteById(ticket.getId());
                            System.out.println("Ticket is deleted " + ticket.getId());
                        }
                    } catch (NullPointerException ignored) {
                    }
                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask, waitingPurchaseTimeRepository.getWaitingMinute() * 60000);
            return new ResponseEntity(new ApiResponse("Success",
                    true, ticket.getId()), HttpStatus.OK);
        }
    }

    public HttpEntity<?> getTicketById(Integer ticketId) {

        CustomTicketForCart ticket = ticketRepository.getTicketByIdForCart(ticketId);
        if (ticket != null) {
            return new ResponseEntity(new ApiResponse("Success",
                    true, ticket), HttpStatus.OK);
        }
        return new ResponseEntity(new ApiResponse("wrong",
                false, null), HttpStatus.BAD_REQUEST);
    }
}




