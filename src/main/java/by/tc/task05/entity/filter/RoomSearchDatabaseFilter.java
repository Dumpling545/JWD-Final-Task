package by.tc.task05.entity.filter;

import by.tc.task05.entity.Hotel;

import java.time.LocalDate;

/**
 * Entity needed to filter room list on DAO level. Unlike {@link
 * RoomSearchServiceFilter}, does not contain 'initialized' flags for primitive
 * properties, also contains {@link by.tc.task05.entity.Location} instead of
 * {@link String} query
 */
public class RoomSearchDatabaseFilter {
	private static final long serialVersionUID = 775084556368664862L;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private double latitudeLowBound;
	private double latitudeHighBound;
	private double longitudeLowBound;
	private double longitudeHighBound;
	private double costLowBound;
	private double costHighBound;
	private double ratingLowBound;
	private double ratingHighBound;
	private int numberOfBeds;
	private int skipAmount;
	private int takeAmount;

	public RoomSearchDatabaseFilter() {
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public double getLatitudeLowBound() {
		return latitudeLowBound;
	}

	public void setLatitudeLowBound(double latitudeLowBound) {
		this.latitudeLowBound = latitudeLowBound;
	}

	public double getLatitudeHighBound() {
		return latitudeHighBound;
	}

	public void setLatitudeHighBound(double latitudeHighBound) {
		this.latitudeHighBound = latitudeHighBound;
	}

	public double getLongitudeLowBound() {
		return longitudeLowBound;
	}

	public void setLongitudeLowBound(double longitudeLowBound) {
		this.longitudeLowBound = longitudeLowBound;
	}

	public double getLongitudeHighBound() {
		return longitudeHighBound;
	}

	public void setLongitudeHighBound(double longitudeHighBound) {
		this.longitudeHighBound = longitudeHighBound;
	}

	public double getCostLowBound() {
		return costLowBound;
	}

	public void setCostLowBound(double costLowBound) {
		this.costLowBound = costLowBound;
	}

	public double getCostHighBound() {
		return costHighBound;
	}

	public void setCostHighBound(double costHighBound) {
		this.costHighBound = costHighBound;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public int getSkipAmount() {
		return skipAmount;
	}

	public void setSkipAmount(int skipAmount) {
		this.skipAmount = skipAmount;
	}

	public int getTakeAmount() {
		return takeAmount;
	}

	public void setTakeAmount(int takeAmount) {
		this.takeAmount = takeAmount;
	}

	public double getRatingLowBound() {
		return ratingLowBound;
	}

	public void setRatingLowBound(double ratingLowBound) {
		this.ratingLowBound = ratingLowBound;
	}

	public double getRatingHighBound() {
		return ratingHighBound;
	}

	public void setRatingHighBound(double ratingHighBound) {
		this.ratingHighBound = ratingHighBound;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		RoomSearchDatabaseFilter that = (RoomSearchDatabaseFilter) o;

		if (Double.compare(that.latitudeLowBound, latitudeLowBound) != 0) {
			return false;
		}
		if (Double.compare(that.latitudeHighBound, latitudeHighBound) != 0) {
			return false;
		}
		if (Double.compare(that.longitudeLowBound, longitudeLowBound) != 0) {
			return false;
		}
		if (Double.compare(that.longitudeHighBound, longitudeHighBound) != 0) {
			return false;
		}
		if (Double.compare(that.costLowBound, costLowBound) != 0) {
			return false;
		}
		if (Double.compare(that.costHighBound, costHighBound) != 0) {
			return false;
		}
		if (Double.compare(that.ratingLowBound, ratingLowBound) != 0) {
			return false;
		}
		if (Double.compare(that.ratingHighBound, ratingHighBound) != 0) {
			return false;
		}
		if (numberOfBeds != that.numberOfBeds) {
			return false;
		}
		if (skipAmount != that.skipAmount) {
			return false;
		}
		if (takeAmount != that.takeAmount) {
			return false;
		}
		if (checkInDate != null ? !checkInDate.equals(that.checkInDate) :
				that.checkInDate != null)
		{
			return false;
		}
		return checkOutDate != null ? checkOutDate.equals(that.checkOutDate) :
				that.checkOutDate == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = checkInDate != null ? checkInDate.hashCode() : 0;
		result = 31 * result +
				(checkOutDate != null ? checkOutDate.hashCode() : 0);
		temp = Double.doubleToLongBits(latitudeLowBound);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(latitudeHighBound);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitudeLowBound);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitudeHighBound);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(costLowBound);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(costHighBound);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ratingLowBound);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ratingHighBound);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + numberOfBeds;
		result = 31 * result + skipAmount;
		result = 31 * result + takeAmount;
		return result;
	}

	@Override
	public String toString() {
		return "RoomSearchDatabaseFilter{" +
				"checkInDate=" + checkInDate +
				", checkOutDate=" + checkOutDate +
				", latitudeLowBound=" + latitudeLowBound +
				", latitudeHighBound=" + latitudeHighBound +
				", longitudeLowBound=" + longitudeLowBound +
				", longitudeHighBound=" + longitudeHighBound +
				", costLowBound=" + costLowBound +
				", costHighBound=" + costHighBound +
				", ratingLowBound=" + ratingLowBound +
				", ratingHighBound=" + ratingHighBound +
				", numberOfBeds=" + numberOfBeds +
				", skipAmount=" + skipAmount +
				", takeAmount=" + takeAmount +
				'}';
	}
}
