package uz.pdp.cinemarest.controller;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.Refund;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.entity.Ticket;
import uz.pdp.cinemarest.entity.User;
import uz.pdp.cinemarest.entity.enums.TicketStatus;
import uz.pdp.cinemarest.projection.CustomTicketForCart;
import uz.pdp.cinemarest.repository.TicketRepository;
import uz.pdp.cinemarest.repository.TransactionalHistoryRepository;
import uz.pdp.cinemarest.repository.UserRepository;
import uz.pdp.cinemarest.service.PaymentService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaymentService paymentService;

    @Autowired
    TransactionalHistoryRepository transactionalHistoryRepository;


    @PostMapping("/webhook")
    public void handle(@RequestBody String payload, @RequestHeader(name = "Stripe-Signature") String signHeader, HttpServletResponse response) throws SignatureVerificationException {

        String endpointSecret = "whsec_38361c47dd8d7fd0760bf192792bec5807feb22ced9640609b7a5269c98d253e";
        Stripe.apiKey = "sk_test_51Kjcy8J9RDHCvMCefQ3912e84uHHDmAKLRvnmVPyt7BkbAkK1HNwtd9PPH7Q8rYiZn9VW8mRW5F7p9zOw3Y5luef00VB8TvMw0";

//           to activate:  stripe listen --forward-to localhost:8080/api/webhook

        Event event = Webhook.constructEvent(payload, signHeader, endpointSecret);
        System.out.println("Order fulfilled");

        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();

          //  Optional<User> optionalUser = userRepository.findById(1);

            List<Ticket> allByCartIdAndStatus =
                    ticketRepository.findAllByUserIdAndTicketStatus(1, TicketStatus.NEW);


            if (allByCartIdAndStatus.size() != 0) {
                paymentService.fulfillOrder(session);

            }

        }

    }


    @GetMapping("/charge")
    public HttpEntity<?> creatStripeSession() {

        Stripe.apiKey = "sk_test_51Kjcy8J9RDHCvMCefQ3912e84uHHDmAKLRvnmVPyt7BkbAkK1HNwtd9PPH7Q8rYiZn9VW8mRW5F7p9zOw3Y5luef00VB8TvMw0";


        //============== KEYINCHALIK @CURRENTUSER GA O'ZGARADI
        Integer userId = 1;
        //===============================

        // whsec_38361c47dd8d7fd0760bf192792bec5807feb22ced9640609b7a5269c98d253e


        List<CustomTicketForCart> allTicket = ticketRepository.getUserCart(userId);

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();


        for (CustomTicketForCart ticketForCart : allTicket) {

            String movieTitle = ticketForCart.getMovieTitle();
            Double ticketPrice = ticketForCart.getPrice();


            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
                    .builder()
                    .setName(movieTitle)
                    .build();

            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
                    .builder()
                    .setCurrency("usd")
                    .setProductData(productData)
                    .setUnitAmount((long) (ticketPrice * 100))
                    .build();

            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                    .builder()
                    .setPriceData(priceData)
                    .setQuantity(1L)
                    .build();

            lineItems.add(lineItem);
        }


        SessionCreateParams params = SessionCreateParams

                .builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl("http://locolhost:8080/failed")
                .setSuccessUrl("http://locolhost:8080/success")
                .addAllLineItem(lineItems)
                .build();


        try {


            Session session = Session.create(params);
            String checkoutUrl = session.getUrl();

            return ResponseEntity.ok(checkoutUrl);
        } catch (StripeException e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

//
//    @PostMapping("/refund")
//    public HttpEntity createCharge(@RequestBody List<Integer> ticketIds) throws StripeException {
//        Stripe.apiKey = "sk_test_51Kjcy8J9RDHCvMCefQ3912e84uHHDmAKLRvnmVPyt7BkbAkK1HNwtd9PPH7Q8rYiZn9VW8mRW5F7p9zOw3Y5luef00VB8TvMw0";
//
//        String paymentIntent = transactionalHistoryRepository.getPaymentIntentByTicketId(ticketIds.get(0));
//        System.out.println(paymentIntent);
//        RefundCreateParams params =
//                RefundCreateParams
//                        .builder()
//                        .setPaymentIntent(paymentIntent)
//                        .setAmount(10 * 100L)
//                        .build();
//        Refund.create(params);
//
//Integer userId = 1;
//
//
//        List<Ticket> allByCartIdAndStatus = ticketRepository.findAllByUserIdAndTicketStatus(userId, TicketStatus.PURCHASED);
//
//        if (allByCartIdAndStatus.size() != 0) {
//            paymentService.refundOrder(allByCartIdAndStatus);
//        }
//        return new ResponseEntity<String>("Success", HttpStatus.CREATED);
//    }
}
