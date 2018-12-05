package payment.cash;

import payment.PaymentMethod;

public class CashPayment implements PaymentMethod {

	public boolean pay(Double aAmount) {
		// todo save the amount payed
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean refund(Double aAmount) {
		return false; // todo
	}
}