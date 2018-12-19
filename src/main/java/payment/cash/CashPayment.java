package payment.cash;

import payment.PaymentMethod;

public class CashPayment implements PaymentMethod {

	public CashPayment(Double moneyToPointRate) {
	}

	public void pay(Double aAmount) {
		// todo save the amount payed
		throw new UnsupportedOperationException();
	}

	@Override
	public void refund(Double aAmount) {

	}
}