package uz.pdp.cinemarest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StripPaymentController {


    @RequestMapping("/success")
    public HttpEntity getSuccess() {
        return ResponseEntity.ok("to'ov alamga oshdi");
    }
}
