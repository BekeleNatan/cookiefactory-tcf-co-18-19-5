package payment.unfaithpass;

import payment.PaymentMethod;

public abstract class UnfaithPassPayment implements PaymentMethod {
	private String qrCode;

	public boolean pay(Double aAmount) {
		throw new UnsupportedOperationException();
	}
}