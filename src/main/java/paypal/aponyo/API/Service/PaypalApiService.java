package paypal.aponyo.API.Service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;

import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import paypal.aponyo.API.Component.PaymentParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class PaypalApiService {

    @Value("${paypal.client.clientId}")
    private String clientId;
    @Value("${paypal.client.clientSecret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;



    /*Create Payment*/
    public Map<String, Object> createPayment(PaymentParam paymentCredentials){
        Map<String, Object> response = new HashMap<>();
        /*Set Amount Parameters*/
        Amount amount = new Amount();
        amount.setCurrency(paymentCredentials.getCurrency());/*Currency eg KSH*/
        amount.setTotal(String.format("%.2f",paymentCredentials.getTotal()));

        /*Transaction*/
        Transaction transaction = new Transaction();
        transaction.setDescription(paymentCredentials.getDescription());
        transaction.setAmount(amount);
        List<Transaction> transactions = new LinkedList<Transaction>();
        transactions.add(transaction);

        /*Payer*/
        Payer payer = new Payer();
        payer.setPaymentMethod(paymentCredentials.getPaymentMethod());/*Either paypal or credit card*/

        /*Payment*/
        Payment payment = new Payment();
        payment.setIntent(paymentCredentials.getPaymentIntent());/*Payment intent e.g sale, authorize, order*/
        payment.setPayer(payer);

        /*Redirect*/
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(paymentCredentials.getCancelUrl());/*Cancel url*/
        redirectUrls.setReturnUrl(paymentCredentials.getSuccessUrl()); /*Success url*/

        /*Payment Send Redirect*/
        payment.setRedirectUrls(redirectUrls);

        response = createApiContext(payment);

        return response;
    }



    /*APIContext Parameters*/
    protected  Map<String,Object> createApiContext(Payment payment){
        Map<String,Object> response = new HashMap<>();
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if(createdPayment != null){
                List<Links> links = createdPayment.getLinks();
                for (Links link:links) {
                    if(link.getRel().equals("approval_url")){
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }

        return response;
    }



    public Map<String, Object> completePayment(HttpServletRequest req){
        Map<String, Object> response = new HashMap();
        Payment payment = new Payment();
        payment.setId(req.getParameter("paymentId"));
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(req.getParameter("payerId"));
        try {
            APIContext context = new APIContext(clientId, clientSecret, mode);
            Payment createdPayment = payment.execute(context, paymentExecution);
            if(createdPayment != null){
                response.put("status", "success");
                response.put("payment", createdPayment);
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return response;
    }


}
