package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.payment.PaymentMethod;

import java.util.HashMap;
import java.util.Map;

public class PaymentInfos {
    Map<PaymentMethod, Double> payments = new HashMap<>();
    PaymentInfos(){ }

    public void addPayment(PaymentMethod paymentMethod, Double price){
        if(payments.get(paymentMethod) == null){
            payments.put(paymentMethod, price);
        }else{
            payments.put(paymentMethod,payments.get(paymentMethod)+price);
        }
    }
}
