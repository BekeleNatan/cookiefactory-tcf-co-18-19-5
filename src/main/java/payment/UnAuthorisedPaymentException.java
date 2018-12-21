package payment;

public class UnAuthorisedPaymentException extends Exception {
    private double paymentLimit;
    private String errMsg;

    public UnAuthorisedPaymentException(double paymentLimit,String errMsg){
        this.paymentLimit = paymentLimit;
        this.errMsg = errMsg;
    }

    public double getPaymentLimit() {
        return paymentLimit;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
