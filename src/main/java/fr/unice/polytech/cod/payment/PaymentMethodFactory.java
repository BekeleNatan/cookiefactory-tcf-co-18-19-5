package fr.unice.polytech.cod.payment;

import fr.unice.polytech.cod.payment.cash.CashPayment;
import fr.unice.polytech.cod.payment.creditcard.CreditCardPayment;
import fr.unice.polytech.cod.payment.unfaithpass.UnfaithPassPayment;

public class PaymentMethodFactory {
	public CashRegister _unnamed_CashRegister_;

	public CreditCardPayment createCreditCard(String aNameOnCard, String aNumber, String aCvv, String aExpirationDate) {
		throw new UnsupportedOperationException();
	}

	public CashPayment createCash() {
		throw new UnsupportedOperationException();
	}

	public UnfaithPassPayment createUnfaithPass(String aQrCode) {
		throw new UnsupportedOperationException();
	}
}