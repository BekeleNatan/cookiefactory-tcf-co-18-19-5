package payment.cash;

import payment.PaymentMethod;
import payment.services.UnfaithPassService;

public class CashPayment implements PaymentMethod {
    private Double moneyToPointRate;
    private String unfaithPassQrCode;
    private UnfaithPassService unfaithPassService;

    public CashPayment(Double moneyToPointRate, String unfaithPassQrCode) {
        this.moneyToPointRate = moneyToPointRate;
        this.unfaithPassQrCode = unfaithPassQrCode;
    }

    public boolean pay(Double aAmount) {
        if (this.unfaithPassQrCode != null) {
            Double pointsGained = aAmount * this.moneyToPointRate;
            return this.unfaithPassService.addPoints(this.unfaithPassQrCode,pointsGained);
        }
        return true;
    }

    public UnfaithPassService getUnfaithPassService() {
        return unfaithPassService;
    }

    public void setUnfaithPassService(UnfaithPassService unfaithPassService) {
        this.unfaithPassService = unfaithPassService;
    }


}