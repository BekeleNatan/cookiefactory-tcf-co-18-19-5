package store;

import order.Order;
import payment.PaymentMethod;

public class CashRegister {

	public boolean pay(Order aOrder, PaymentMethod aPaymentMethod, Double aAmount) {
		throw new UnsupportedOperationException();
	}

	public Double remainToPay(Order aOrder) {
		throw new UnsupportedOperationException();
	}

	public boolean applyDiscount(Order aOrder, Double aDiscountRate) {
		throw new UnsupportedOperationException();
	}

	public void refund(Order order) {
		// todo implement this method using PaymentInfos
	}
}