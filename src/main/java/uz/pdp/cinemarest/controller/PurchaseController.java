package uz.pdp.cinemarest.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {
    @GetMapping
    public HttpEntity<?> creatStripeSession() {
        List<SessionCreateParams.LineItem> lineItems= new ArrayList<>();


        SessionCreateParams params = SessionCreateParams
                .builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl("http://locolhost:8080/failed")
                .setSuccessUrl("http://locolhost:8080/success")
//                .addAllLineItem()
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
