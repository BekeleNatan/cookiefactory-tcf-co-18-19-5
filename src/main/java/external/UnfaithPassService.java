package external;

public interface UnfaithPassService {

    public double getCashLeft(String qrCode);
    public void removeCash(String qrCode, Double cashToRemove);

    public Double getPointsLeft(String qrCode);
    public void addPoints(String qrCode, Double pointsToAdd);
    public void removePoints(String qrCode,Double pointsToRemove);
}
