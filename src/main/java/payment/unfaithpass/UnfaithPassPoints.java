package payment.unfaithpass;

import payment.InsufficientFundsExcpetion;

public class UnfaithPassPoints extends UnfaithPassPayment {
    private Double conversionRate;

    public UnfaithPassPoints(String qrCode, Double conversionRate) {
        super(qrCode);
        this.conversionRate = conversionRate;
    }

    public boolean pay(Double aAmount) throws InsufficientFundsExcpetion {
        Double pointsLeftOnAccount = this.getUnfaithPassService().getPointsLeft(this.getQrCode());
        Double pointsNeeded = aAmount * conversionRate;
        if (Double.compare(pointsLeftOnAccount, pointsNeeded) < 0) {
            String errMsg = "Not enough Points! Points needed : " + pointsNeeded;
            throw new InsufficientFundsExcpetion(errMsg, pointsNeeded);
        } else {
            this.getUnfaithPassService().removePoints(this.getQrCode(), pointsNeeded);
            return true;
        }
    }

    @Override
    public void refund(Double aAmount) {
        throw new UnsupportedOperationException();
    }
}