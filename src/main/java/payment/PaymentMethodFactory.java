package payment;

import payment.cash.CashPayment;
import payment.creditcard.CreditCardPayment;
import payment.information.CreditCardType;
import payment.unfaithpass.UnfaithPassMoney;
import payment.unfaithpass.UnfaithPassPoints;
import store.CashRegister;

public class PaymentMethodFactory {
    public CashRegister cashRegister;
    private String visaRegex = "^4[0-9]{12}(?:[0-9]{3})?$";

    private String masterCardRegex = "^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$";
    private Double moneyToPointRate;
    private Double pointToMoneyRate;

    public PaymentMethodFactory(Double pointToMoneyRate, Double moneyToPointRate) {
        this.moneyToPointRate = moneyToPointRate;
        this.pointToMoneyRate = pointToMoneyRate;
    }

    public CreditCardPayment createCreditCard(String aNameOnCard, String aNumber, String aCvv, String aExpirationDate) {

        checkCard(aNameOnCard, aNumber, aCvv, aExpirationDate);

        return new CreditCardPayment(moneyToPointRate, new CreditCard(aNameOnCard, aNumber, aCvv, aExpirationDate, getTypeCard(aNumber)), null);
    }


    public CreditCardPayment createCreditCard(String aNameOnCard, String aNumber, String aCvv, String aExpirationDate, String unfaithPassQrCode) {

        checkCard(aNameOnCard, aNumber, aCvv, aExpirationDate);

        return new CreditCardPayment(moneyToPointRate, new CreditCard(aNameOnCard, aNumber, aCvv, aExpirationDate, getTypeCard(aNumber)), unfaithPassQrCode);
    }

    public CashPayment createCash() {
        return new CashPayment(moneyToPointRate, null);
    }

    // for client with unfaithpass
    public CashPayment createCash(String unfaithPassQrCode) {
        return new CashPayment(moneyToPointRate, unfaithPassQrCode);
    }

    public UnfaithPassMoney createUnfaithPassMoney(String aQrCode) {
        return new UnfaithPassMoney(aQrCode);
    }

    public UnfaithPassPoints createUnfaithPassPoints(String aQrCode) {
        return new UnfaithPassPoints(aQrCode, pointToMoneyRate);
    }

    private CreditCardType getTypeCard(String aNumber) {
        if (aNumber.matches(visaRegex)) {
            return CreditCardType.VISA;
        } else if (aNumber.matches(masterCardRegex)) {
            return CreditCardType.MASTERCARD;
        } else {
            return null;
        }
    }

    private void checkCard(String aNameOnCard, String aNumber, String aCvv, String aExpirationDate) {
        if (!aNameOnCard.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
            throw new IllegalArgumentException("Wrong format for name on card");
        } else if (!aNumber.matches(visaRegex) && !aNumber.matches(masterCardRegex)) {
            throw new IllegalArgumentException("Wrong format for number on card");
        } else if (!aCvv.matches("^(\\d{3})$")) {
            throw new IllegalArgumentException("Wrong format for cvv on card");
        } else if (!aExpirationDate.matches("^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$")) {
            throw new IllegalArgumentException("Wrong format for ExpirationDate on card");
        }
    }
}