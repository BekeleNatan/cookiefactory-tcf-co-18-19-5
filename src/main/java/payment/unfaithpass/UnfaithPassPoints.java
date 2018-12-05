package payment.unfaithpass;

public class UnfaithPassPoints extends UnfaithPassPayment {

	public boolean pay(Double aAmount) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean refund(Double aAmount) {
		return false; // todo
	}
}