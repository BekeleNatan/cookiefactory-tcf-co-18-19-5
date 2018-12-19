package payment;

public class InsufficientFundsExcpetion extends Exception {
    private double amount;
    private String errorMsg;

    public InsufficientFundsExcpetion(String errorMsg, Double amount) {
        this.setAmount(amount);
        this.errorMsg = errorMsg;

    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}

