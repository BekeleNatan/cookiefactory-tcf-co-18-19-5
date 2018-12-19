package payment.unfaithpass;

import external.UnfaithPassService;
import payment.InsufficientFundsExcpetion;
import payment.PaymentMethod;

public abstract class UnfaithPassPayment implements PaymentMethod {
	private String qrCode;
	private UnfaithPassService unfaithPassService;

	public UnfaithPassPayment(String qrCode){
		this.qrCode=qrCode;
	}

	public abstract void pay(Double aAmount) throws InsufficientFundsExcpetion;

	public String getQrCode() {
		return qrCode;
	}

	public UnfaithPassService getUnfaithPassService() {
		return unfaithPassService;
	}

	public void setUnfaithPassService(UnfaithPassService unfaithPassService) {
		this.unfaithPassService = unfaithPassService;
	}
}