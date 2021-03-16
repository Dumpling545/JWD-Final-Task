package by.tc.task05.entity;

public class Hotel {
    private int id;
    private String name;
    private String cachedAddress;
    private double longtitudeAddress;
    private double latitudeAddress;
    private String bankAccount;
    private String icon;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCachedAddress() {
        return cachedAddress;
    }
    public void setCachedAddress(String cachedAddress) {
        this.cachedAddress = cachedAddress;
    }
    public double getLongtitudeAddress() {
        return longtitudeAddress;
    }
    public void setLongtitudeAddress(double longtitudeAddress) {
        this.longtitudeAddress = longtitudeAddress;
    }
    public double getLatitudeAddress() {
        return latitudeAddress;
    }
    public void setLatitudeAddress(double latitudeAddress) {
        this.latitudeAddress = latitudeAddress;
    }
    public String getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
