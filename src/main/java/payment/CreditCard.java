package payment;

public class CreditCard {

    private String nameOnCard;
    private String number;
    private String cvv;
    private String expirationDate;
    private CreditCardType creditCardType;

    public CreditCard (String nameOnCard, String number, String cvv, String expirationDate, CreditCardType creditCardType) {
        this.nameOnCard = nameOnCard;
        this.number = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.creditCardType = creditCardType;
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
