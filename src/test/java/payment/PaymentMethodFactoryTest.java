package payment;

import payment.creditcard.CreditCardPayment;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentMethodFactoryTest {

    PaymentMethodFactory paymentMethodFactory = new PaymentMethodFactory();


    @Test
    public void createMasterCardCreditCardSuccessfully() {
        try {
            CreditCardPayment creditCard = paymentMethodFactory.createCreditCard("Chapy Mourinho Girma", "5468994925020925", "123", "12/22");
            assertEquals(CreditCardType.MASTERCARD, creditCard.getCreditCardType());
            assertEquals("Chapy Mourinho Girma", creditCard.getNameOnCard());
            assertEquals("5468994925020925", creditCard.getNumber());
            assertEquals("123", creditCard.getCvv());
            assertEquals("12/22", creditCard.getExpirationDate());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void createVisaCardCreditCardSuccessfully() {
        try {
            CreditCardPayment creditCard = paymentMethodFactory.createCreditCard("Yon Kooijman", "4024007191080711", "123", "12/22");
            assertEquals(CreditCardType.VISA, creditCard.getCreditCardType());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void createCreditCardFailName() {
        try {
            CreditCardPayment creditCard = paymentMethodFactory.createCreditCard("09982", "4024007191080711", "123", "12/22");
            assertEquals(CreditCardType.VISA, creditCard.getCreditCardType());
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Wrong format for name on card");
        }
    }

    @Test
    public void createCreditCardFailNumber() {
        try {
            CreditCardPayment creditCard = paymentMethodFactory.createCreditCard("Yon Kooijman", "999907191080711", "123", "12/22");
            assertEquals(CreditCardType.VISA, creditCard.getCreditCardType());
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Wrong format for number on card");
        }
    }

    @Test
    public void createCreditCardFailCVV() {
        try {
            CreditCardPayment creditCard = paymentMethodFactory.createCreditCard("Yon Kooijman", "4024007191080711", "1928", "12/22");
            assertEquals(CreditCardType.VISA, creditCard.getCreditCardType());
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Wrong format for cvv on card");
        }
    }

    @Test
    public void createCreditCardFailExpirationDate() {
        try {
            CreditCardPayment creditCard = paymentMethodFactory.createCreditCard("Yon Kooijman", "4024007191080711", "123", "12/22/12/12");
            assertEquals(CreditCardType.VISA, creditCard.getCreditCardType());
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Wrong format for ExpirationDate on card");
        }
    }


    @Ignore
    public void createCash() {
    }

    @Ignore
    public void createUnfaithPass() {
    }
}