package paypal.aponyo.API.Component;


public class PaymentParam {
    private Double total;
    private String currency;
    private String paymentMethod;
    private String paymentIntent;
    private String description;
    private String cancelUrl;
    private String successUrl;

    public PaymentParam() {
    }

    public PaymentParam(Double total, String currency, String paymentMethod, String paymentIntent,
                        String description, String cancelUrl, String successUrl) {
        this.total = total;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.paymentIntent = paymentIntent;
        this.description = description;
        this.cancelUrl = cancelUrl;
        this.successUrl = successUrl;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentmethod) {
        this.paymentMethod = paymentmethod;
    }

    public String getPaymentIntent() {
        return paymentIntent;
    }

    public void setPaymentIntent(String paymentIntent) {
        this.paymentIntent = paymentIntent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }
}
