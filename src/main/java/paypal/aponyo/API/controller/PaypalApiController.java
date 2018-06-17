package paypal.aponyo.API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import paypal.aponyo.API.Component.PaymentParam;
import paypal.aponyo.API.Service.PaypalApiService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class PaypalApiController {
    @Autowired
    private PaypalApiService paypalApiService;


    /*Make payment*/
    @PostMapping(value = "/make/payment")
    public Map<String, Object> makePayment(@RequestParam("total") Double total,
                                           @RequestParam("currency") String currency,
                                           @RequestParam("paymentMethod") String paymentMethod,
                                           @RequestParam("paymentIntent") String paymentIntent,
                                           @RequestParam("description") String description,
                                           @RequestParam("cancelUrl") String cancelUrl,
                                           @RequestParam("successUrl") String successUrl){

        PaymentParam paymentParam =
                new PaymentParam(total,currency,paymentMethod,
                        paymentIntent,description,cancelUrl,successUrl);
        return paypalApiService.createPayment(paymentParam);
    }


   /*Complete payment*/
    @PostMapping(value = "/complete/payment")
    public Map<String, Object> completePayment(HttpServletRequest request, @RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId){
        return paypalApiService.completePayment(request);
    }




}
