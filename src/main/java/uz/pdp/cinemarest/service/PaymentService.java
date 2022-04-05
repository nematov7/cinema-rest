package uz.pdp.cinemarest.service;

import com.stripe.model.checkout.Session;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.entity.Ticket;
import uz.pdp.cinemarest.entity.TransactionalHistory;
import uz.pdp.cinemarest.entity.enums.TicketStatus;
import uz.pdp.cinemarest.repository.TicketRepository;
import uz.pdp.cinemarest.repository.TransactionalHistoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TransactionalHistoryRepository transactionalHistoryRepository;


    public void fulfillOrder(Session session) {

        /*        Optional<PayType> payTypeRepositoryById = payTypeRepository.findById(1);
        double summa=0;
        for (Ticket ticket : allByCartIdAndStatus) {
            ticket.setTicketStatus(TicketStatus.PURCHASED);
            summa+=ticket.getPrice();
        }
        List<Ticket> tickets = ticketRepository.saveAll(allByCartIdAndStatus);
        TransactionalHistory transactionalHistory = new TransactionalHistory();
        transactionalHistory.setPaymentIntent(paymentIntent);
        transactionalHistory.setStatus(TicketStatus.PURCHASED);
        transactionalHistory.setTicketList(tickets);
        transactionalHistory.setTotalAmount(summa);
        transactionalHistory.setPayType(payTypeRepositoryById.get());
        transactionalHistoryRepository.save(transactionalHistory);*/

        Integer userId=1;

        addTransactionHistory(userId ,session.getPaymentIntent());
        changeTicketStatusToPurchase(userId);
    }

    private void addTransactionHistory(Integer userId, String paymentIntent) {
        //  List<Ticket> ticketList = ticketRepository.findByUserIdAndTicketStatus(userId, TicketStatus.NEW);
        List<Ticket> ticketList =
                ticketRepository.findAllByUserIdAndTicketStatus(userId, TicketStatus.NEW);

        double totalAmount = ticketList.stream().map(Ticket::getPrice).
                collect(Collectors.toList()).stream().mapToDouble(value -> value).sum();


        TransactionalHistory transactionHistory = new TransactionalHistory();
        transactionHistory.setTicketList(ticketList);
        transactionHistory.setTotalAmount(totalAmount);
        transactionHistory.setPaymentIntent(paymentIntent);
        transactionHistory.setRefunded(false);

        transactionalHistoryRepository.save(transactionHistory);
    }

    private void changeTicketStatusToPurchase(Integer userId) {
        try {
            List<Ticket> ticketList =
                    ticketRepository.findAllByUserIdAndTicketStatus(userId, TicketStatus.NEW);
            for (Ticket ticket : ticketList) {
                ticket.setTicketStatus(TicketStatus.PURCHASED);
            }
            ticketRepository.saveAll(ticketList);


        } catch (Exception e) {
            e.printStackTrace();


        }

    }


    @SneakyThrows
    public void refundOrder(List<Ticket> allByCartIdAndStatus) {
        for (Ticket ticket : allByCartIdAndStatus) {
            ticket.setTicketStatus(TicketStatus.REFUNDED);
            ticketRepository.save(ticket);
        }
    }
}
