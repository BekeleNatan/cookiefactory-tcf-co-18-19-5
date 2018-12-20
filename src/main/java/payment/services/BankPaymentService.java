package payment.services;

import payment.CreditCard;

public interface BankPaymentService {
    public boolean makePayment(CreditCard usersCard, Double aAmount);
}
