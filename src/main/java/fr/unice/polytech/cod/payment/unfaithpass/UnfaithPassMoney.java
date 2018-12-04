package fr.unice.polytech.cod.payment.unfaithpass;

public class UnfaithPassMoney extends UnfaithPassPayment {

	public boolean pay(Double aAmount) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean refund(Double aAmount) {
		return false; // todo
	}
}