package fr.unice.polytech.cod.payment.creditcard;

import fr.unice.polytech.cod.payment.CreditCardType;
import fr.unice.polytech.cod.payment.PaymentMethod;

public class CreditCardPayment implements PaymentMethod {
    private String nameOnCard;
    private String number;
    private String cvv;
    private String expirationDate;
    private CreditCardType creditCardType;

    public CreditCardPayment(String nameOnCard, String number, String cvv, String expirationDate, CreditCardType creditCardType) {
        this.nameOnCard = nameOnCard;
        this.number = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.creditCardType = creditCardType;
    }

    public boolean pay(Double aAmount) {
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