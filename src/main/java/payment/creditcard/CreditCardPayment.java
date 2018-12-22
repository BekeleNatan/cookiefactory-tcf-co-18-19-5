package payment.creditcard;

import payment.CreditCard;
import payment.PaymentMethod;
import payment.services.BankPaymentService;
import payment.services.UnfaithPassService;

public class CreditCardPayment implements PaymentMethod {
    private Double moneyToPointRate;
    private String unfaithPassQrCode;
    private BankPaymentService bankPaymentService;
    private CreditCard creditCard;
    private UnfaithPassService unfaithPassService;

    public CreditCardPayment(Double moneyToPointRate, CreditCard creditCard, String unfaithPassQrCode) {
        this.creditCard = creditCard;
        this.moneyToPointRate = moneyToPointRate;
        this.unfaithPassQrCode = unfaithPassQrCode;
    }

    public boolean pay(Double aAmount) {
        if (this.unfaithPassQrCode != null) {
            Double pointsGained = aAmount * this.moneyToPointRate;
            this.unfaithPassService.addPoints(this.unfaithPassQrCode,pointsGained);
        }
        return this.bankPaymentService.makePayment(creditCard,aAmount);
    }

    public UnfaithPassService getUnfaithPassService() {
        return unfaithPassService;
    }

    public void setUnfaithPassService(UnfaithPassService unfaithPassService) {
        this.unfaithPassService = unfaithPassService;
    }
    public BankPaymentService getBankPaymentService() {
        return bankPaymentService;
    }

    public void setBankPaymentService(BankPaymentService bankPaymentService) {
        this.bankPaymentService = bankPaymentService;
    }


    public CreditCard getCreditCard() {
        return creditCard;
    }
}