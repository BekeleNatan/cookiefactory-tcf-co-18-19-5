package store;

import order.Order;
import payment.PaymentMethod;
import payment.PaymentMethodFactory;

public class CashRegister {

	PaymentMethodFactory paymentMethodFactory;

	public void pay(Order aOrder, PaymentMethod aPaymentMethod, Double aAmount) {
		try{
			aPaymentMethod.pay(aAmount);
		}
		catch (Exception e){

		}



	}

	//toRemove
	public Double remainToPay(Order aOrder) {
		throw new UnsupportedOperationException();
	}


	// can be done before we only have tha amount here
	public boolean applyDiscount(Order aOrder, Double aDiscountRate) {
		throw new UnsupportedOperationException();
	}

	public void refund(Order order) {
		// todo implement this method using PaymentInfos

	}
}