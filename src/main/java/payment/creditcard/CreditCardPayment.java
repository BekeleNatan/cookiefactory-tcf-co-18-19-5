package payment.creditcard;

import payment.CreditCardType;
import payment.PaymentMethod;

public class CreditCardPayment implements PaymentMethod {
    private String nameOnCard;
    private String number;
    private String cvv;
    private String expirationDate;
    private CreditCardType creditCardType;

    public CreditCardPayment(Double moneyToPointRate, String nameOnCard, String number, String cvv, String expirationDate, CreditCardType creditCardType) {
        this.nameOnCard = nameOnCard;
        this.number = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.creditCardType = creditCardType;
    }

    public void pay(Double aAmount) {
        // call bank api to pay
        throw new UnsupportedOperationException();
    }

    @Override
    public void refund(Double aAmount) {
        throw new UnsupportedOperationException();
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getNumber() {
        return number;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }
}