package by.tc.task05.entity;

import java.io.Serializable;

/**
 * Hotel entity that matches database structure
 */
public class Hotel implements Serializable {

    private static final long serialVersionUID = -4513059396813774587L;

    private int id;
    private String name;
    private String cachedAddress;
    private double longitudeAddress;
    private double latitudeAddress;
    private String bankAccount;
    private String icon;

    public Hotel() {
    }

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

    public double getLongitudeAddress() {
        return longitudeAddress;
    }

    public void setLongitudeAddress(double longitudeAddress) {
        this.longitudeAddress = longitudeAddress;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (id != hotel.id) return false;
        if (Double.compare(hotel.longitudeAddress, longitudeAddress) != 0) {
            return false;
        }
        if (Double.compare(hotel.latitudeAddress, latitudeAddress) != 0) {
            return false;
        }
        if (name != null ? !name.equals(hotel.name) : hotel.name != null) {
            return false;
        }
        if (cachedAddress != null ? !cachedAddress.equals(hotel.cachedAddress) :
                hotel.cachedAddress != null) {
            return false;
        }
        if (bankAccount != null ? !bankAccount.equals(hotel.bankAccount) :
                hotel.bankAccount != null) {
            return false;
        }
        return icon != null ? icon.equals(hotel.icon) : hotel.icon == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result +
                (cachedAddress != null ? cachedAddress.hashCode() : 0);
        temp = Double.doubleToLongBits(longitudeAddress);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitudeAddress);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result +
                (bankAccount != null ? bankAccount.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", name='" + name + '\'' +
                ", cachedAddress='" + cachedAddress + '\'' +
                ", longitudeAddress=" + longitudeAddress +
                ", latitudeAddress=" + latitudeAddress + ", bankAccount='" +
                bankAccount + '\'' + ", icon='" + icon + '\'' + '}';
    }
}
