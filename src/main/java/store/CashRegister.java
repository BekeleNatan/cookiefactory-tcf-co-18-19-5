package store;

import order.Order;
import payment.PaymentMethod;
import payment.PaymentMethodFactory;

public class CashRegister {



	public void pay(Order aOrder, PaymentMethod aPaymentMethod, Double aAmount) {
		try{
			if(aPaymentMethod.pay(aAmount)){
				aOrder.deductPayedAmount(aAmount);
			}
		}
		catch (Exception e){

		}



	}



	// can be done before we only have tha amount here
	public boolean applyDiscount(Order aOrder, Double aDiscountRate) {
		throw new UnsupportedOperationException();
	}

	public void refund(Order order) {
		// todo implement this method using PaymentInfos

	}
}