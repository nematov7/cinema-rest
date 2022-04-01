package uz.pdp.cinemarest.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemarest.projection.CustomTicketForCart;
import uz.pdp.cinemarest.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping
    public HttpEntity<?> creatStripeSession() {

        Stripe.apiKey = "sk_test_51Kjcy8J9RDHCvMCefQ3912e84uHHDmAKLRvnmVPyt7BkbAkK1HNwtd9PPH7Q8rYiZn9VW8mRW5F7p9zOw3Y5luef00VB8TvMw0";


        //============== KEYINCHALIK @CURRENTUSER GA O'ZGARADI
        Integer userId = 1;
       //===============================


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
}
