package payment.unfaithpass;

import external.UnfaithPassService;
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
    }
    @Test(expected = InsufficientFundsExcpetion.class)
    public void payWithInsufficiantFunds() throws InsufficientFundsExcpetion {
        unfaithPassMoney.pay(150.0);
    }

    @Test
    public void payWithSufficientFunds() throws  InsufficientFundsExcpetion {
        unfaithPassMoney.pay(80.0);

    }

}