package payment.unfaithpass;

import payment.InsufficientFundsExcpetion;

public class UnfaithPassMoney extends UnfaithPassPayment {


    public UnfaithPassMoney(String qrCode) {
        super(qrCode);
    }

    public boolean pay(Double aAmount) throws InsufficientFundsExcpetion {
        Double moneyLeftOnAccount = this.getUnfaithPassService().getCashLeft(this.getQrCode());
        if (Double.compare(moneyLeftOnAccount,aAmount)< 0) {
            double neededAmount = aAmount - moneyLeftOnAccount;
            String errMsg = "Not enough Money! Money needed : " + neededAmount;
            throw new InsufficientFundsExcpetion(errMsg, neededAmount);
        } else {
            return this.getUnfaithPassService().removeCash(this.getQrCode(), aAmount);

        }

    }


}