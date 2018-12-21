package payment.creditcard;

import payment.CreditCard;
import payment.PaymentMethod;
import payment.services.BankPaymentService;

public class CreditCardPayment implements PaymentMethod {
    private Double moneyToPointRate;
    private String unfaithPassQrCode;
    private BankPaymentService bankPaymentService;
    private CreditCard creditCard;

    public CreditCardPayment(Double moneyToPointRate, CreditCard creditCard, String unfaithPassQrCode) {
        this.creditCard = creditCard;
        this.moneyToPointRate = moneyToPointRate;
        this.unfaithPassQrCode = unfaithPassQrCode;
    }

    public boolean pay(Double aAmount) {
        return this.bankPaymentService.makePayment(creditCard,aAmount);
    }

    public BankPaymentService getUnfaithPassService() {
        return bankPaymentService;
    }

    public void setUnfaithPassService(BankPaymentService bankPaymentService) {
        this.bankPaymentService = bankPaymentService;
    }

    @Override
    public void refund(Double aAmount) {
        throw new UnsupportedOperationException();
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
}