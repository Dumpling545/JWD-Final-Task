package by.tc.task05.entity;

import java.io.Serializable;

/**
 * Entity needed to add or change hotel. Unlike {@link Hotel}, does not contain
 * icon image path
 */
public class HotelForm implements Serializable {

	private static final long serialVersionUID = 2580006080862444992L;

	private int id;
	private String name;
	private String cachedAddress;
	private double longitudeAddress;
	private double latitudeAddress;
	private String bankAccount;

	@Override
	public String toString() {
		return "HotelForm{" + "id=" + id + ", name='" + name + '\'' +
				", cachedAddress='" + cachedAddress + '\'' +
				", longitudeAddress=" + longitudeAddress +
				", latitudeAddress=" + latitudeAddress + ", bankAccount='" +
				bankAccount + '}';
	}

	public HotelForm() {
	}

	public HotelForm(int id, String name, String cachedAddress,
	                 double longitudeAddress, double latitudeAddress,
	                 String bankAccount)
	{
		this.id = id;
		this.name = name;
		this.cachedAddress = cachedAddress;
		this.longitudeAddress = longitudeAddress;
		this.latitudeAddress = latitudeAddress;
		this.bankAccount = bankAccount;
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


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		HotelForm hotelForm = (HotelForm) o;

		if (id != hotelForm.id) {
			return false;
		}
		if (Double.compare(hotelForm.longitudeAddress, longitudeAddress) !=
				0)
		{
			return false;
		}
		if (Double.compare(hotelForm.latitudeAddress, latitudeAddress) != 0) {
			return false;
		}
		if (name != null ? !name.equals(hotelForm.name) :
				hotelForm.name != null)
		{
			return false;
		}
		if (cachedAddress != null ?
				!cachedAddress.equals(hotelForm.cachedAddress) :
				hotelForm.cachedAddress != null)
		{
			return false;
		}
		if (bankAccount != null ? !bankAccount.equals(hotelForm.bankAccount) :
				hotelForm.bankAccount != null)
		{
			return false;
		}
		return true;
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
		return result;
	}
}
