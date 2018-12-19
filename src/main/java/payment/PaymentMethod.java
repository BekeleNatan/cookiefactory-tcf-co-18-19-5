package payment;

public interface PaymentMethod {

	public void pay(Double aAmount) throws InsufficientFundsExcpetion;

	public void refund(Double aAmount);
}