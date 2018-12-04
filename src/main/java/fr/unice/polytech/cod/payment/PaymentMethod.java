package fr.unice.polytech.cod.payment;

public interface PaymentMethod {

	public boolean pay(Double aAmount);

	public boolean refund(Double aAmount);
}