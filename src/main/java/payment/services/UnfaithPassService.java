package payment.services;

public interface UnfaithPassService {

    public double getCashLeft(String qrCode);
    public Boolean removeCash(String qrCode, Double cashToRemove);

    public Double getPointsLeft(String qrCode);
    public Boolean addPoints(String qrCode, Double pointsToAdd);
    public Boolean removePoints(String qrCode,Double pointsToRemove);
}
