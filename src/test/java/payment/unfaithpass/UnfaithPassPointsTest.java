package payment.unfaithpass;

import payment.services.UnfaithPassService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import payment.InsufficientFundsExcpetion;

import static org.junit.Assert.*;

public class UnfaithPassPointsTest {

    UnfaithPassPoints unfaithPassPoints = new UnfaithPassPoints("code", 1.5);
    UnfaithPassService unfaithPassService;

    @Before
    public void initialize() {
        unfaithPassService = Mockito.mock(UnfaithPassService.class);
        unfaithPassPoints.setUnfaithPassService(unfaithPassService);
    }

    @Test(expected = InsufficientFundsExcpetion.class)
    public void payWithInsufficientFunds() throws InsufficientFundsExcpetion {
        Mockito.when(unfaithPassService.getPointsLeft(unfaithPassPoints.getQrCode())).thenReturn(50.0);
        unfaithPassPoints.pay(100.0);
    }

    @Test
    public void paywithSufficiantsPoints() throws InsufficientFundsExcpetion{
        Mockito.when(unfaithPassService.getPointsLeft(unfaithPassPoints.getQrCode())).thenReturn(200.0);
        assertTrue(unfaithPassPoints.pay(100.0));
    }
}