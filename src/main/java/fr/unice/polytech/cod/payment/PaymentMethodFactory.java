package fr.unice.polytech.cod.payment;

import fr.unice.polytech.cod.payment.cash.CashPayment;
import fr.unice.polytech.cod.payment.creditcard.CreditCardPayment;
import fr.unice.polytech.cod.payment.unfaithpass.UnfaithPassPayment;
import fr.unice.polytech.cod.store.CashRegister;

public class PaymentMethodFactory {
	public CashRegister cashRegister;

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