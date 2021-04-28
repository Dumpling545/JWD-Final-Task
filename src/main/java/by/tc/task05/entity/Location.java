package by.tc.task05.entity;

import java.io.Serializable;

/**
 * Entity that provides geographical information about point on map (latitude
 * and longitude)
 */
public class Location implements Serializable {

	private static final long serialVersionUID = -1835836785721884262L;

	private double latitude;
	private double longitude;

	public Location() {
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Location location = (Location) o;

		if (Double.compare(location.latitude, latitude) != 0) {
			return false;
		}
		return Double.compare(location.longitude, longitude) == 0;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Location{" + "latitude=" + latitude + ", longitude=" +
				longitude + '}';
	}
}
