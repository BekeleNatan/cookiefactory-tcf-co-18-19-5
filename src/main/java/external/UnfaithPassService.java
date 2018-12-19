package external;

public interface UnfaithPassService {

    public double getCashLeft(String qrCode);
    public void removeCash(String qrCode, int cashToremove);

    public int getPointsLeft(String qrCode);
    public void addPoints(String qrCode, int pointsToAdd);
    public void removePoints(String qrCode,int pointsToRemove);
}
