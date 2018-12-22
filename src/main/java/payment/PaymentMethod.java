package payment;

public interface PaymentMethod {


	public boolean pay(Double aAmount) throws InsufficientFundsExcpetion;

}