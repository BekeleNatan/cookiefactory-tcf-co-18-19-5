package payment.unfaithpass;

import payment.services.UnfaithPassService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import payment.InsufficientFundsExcpetion;

import static org.junit.Assert.*;

public class UnfaithPassMoneyTest {

    UnfaithPassMoney unfaithPassMoney = new UnfaithPassMoney("code");
    UnfaithPassService unfaithPassService;

    @Before
    public void initialize(){
        unfaithPassService = Mockito.mock(UnfaithPassService.class);
        unfaithPassMoney.setUnfaithPassService(unfaithPassService);
        Mockito.when(unfaithPassService.getCashLeft(unfaithPassMoney.getQrCode())).thenReturn(100.0);
        Mockito.when(unfaithPassService.removeCash(unfaithPassMoney.getQrCode(),80.0)).thenReturn(Boolean.TRUE);
    }
    @Test(expected = InsufficientFundsExcpetion.class)
    public void payWithInsufficiantFunds() throws InsufficientFundsExcpetion {
        unfaithPassMoney.pay(150.0);
    }

    @Test
    public void payWithSufficientFunds() throws  InsufficientFundsExcpetion {
        assertTrue(unfaithPassMoney.pay(80.0));
    }

}