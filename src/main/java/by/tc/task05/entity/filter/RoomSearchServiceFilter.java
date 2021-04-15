package by.tc.task05.entity.filter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

public class RoomSearchServiceFilter implements Serializable {
    private static final long serialVersionUID = -2516914431220579962L;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String location;
    private boolean latitudeInitialized = false;
    private double latitude;
    private boolean longtitudeInitialized = false;
    private double longtitude;
    private boolean costLowBoundInitialized = false;
    private double costLowBound;
    private boolean costHighBoundInitialized = false;
    private double costHighBound;
    private boolean ratingLowBoundInitialized = false;
    private double ratingLowBound;
    private boolean ratingHighBoundInitialized = false;
    private double ratingHighBound;
    private boolean numberOfBedsInitialized = false;
    private int numberOfBeds;


    public RoomSearchServiceFilter() {
    }

    public RoomSearchServiceFilter(LocalDate checkInDate, String location,
                                   double latitude, double longtitude,
                                   double costLowBound, double costHighBound,
                                   double ratingLowBound,
                                   double ratingHighBound, int numberOfBeds) {
        this.checkInDate = checkInDate;
        this.location = location;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.costLowBound = costLowBound;
        this.costHighBound = costHighBound;
        this.ratingLowBound = ratingLowBound;
        this.ratingHighBound = ratingHighBound;
        this.numberOfBeds = numberOfBeds;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isLatitudeInitialized() {
        return latitudeInitialized;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        latitudeInitialized = true;
        this.latitude = latitude;
    }

    public boolean isLongtitudeInitialized() {
        return longtitudeInitialized;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        longtitudeInitialized = true;
        this.longtitude = longtitude;
    }

    public boolean isCostLowBoundInitialized() {
        return costLowBoundInitialized;
    }

    public double getCostLowBound() {
        return costLowBound;
    }

    public void setCostLowBound(double costLowBound) {
        costLowBoundInitialized = true;
        this.costLowBound = costLowBound;
    }

    public boolean isCostHighBoundInitialized() {
        return costHighBoundInitialized;
    }

    public double getCostHighBound() {
        return costHighBound;
    }

    public void setCostHighBound(double costHighBound) {
        costHighBoundInitialized = true;
        this.costHighBound = costHighBound;
    }

    public boolean isRatingLowBoundInitialized() {
        return ratingLowBoundInitialized;
    }

    public double getRatingLowBound() {
        return ratingLowBound;
    }

    public void setRatingLowBound(double ratingLowBound) {
        ratingLowBoundInitialized = true;
        this.ratingLowBound = ratingLowBound;
    }

    public boolean isRatingHighBoundInitialized() {
        return ratingHighBoundInitialized;
    }

    public double getRatingHighBound() {
        return ratingHighBound;
    }

    public void setRatingHighBound(double ratingHighBound) {
        ratingHighBoundInitialized = true;
        this.ratingHighBound = ratingHighBound;
    }

    public boolean isNumberOfBedsInitialized() {
        return numberOfBedsInitialized;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        numberOfBedsInitialized = true;
        this.numberOfBeds = numberOfBeds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomSearchServiceFilter filter = (RoomSearchServiceFilter) o;

        if (latitudeInitialized != filter.latitudeInitialized) return false;
        if (Double.compare(filter.latitude, latitude) != 0) return false;
        if (longtitudeInitialized != filter.longtitudeInitialized) return false;
        if (Double.compare(filter.longtitude, longtitude) != 0) {
            return false;
        }
        if (costLowBoundInitialized != filter.costLowBoundInitialized)
            return false;
        if (Double.compare(filter.costLowBound, costLowBound) != 0) {
            return false;
        }
        if (costHighBoundInitialized != filter.costHighBoundInitialized) {
            return false;
        }
        if (Double.compare(filter.costHighBound, costHighBound) != 0) {
            return false;
        }
        if (ratingLowBoundInitialized != filter.ratingLowBoundInitialized) {
            return false;
        }
        if (Double.compare(filter.ratingLowBound, ratingLowBound) != 0) {
            return false;
        }
        if (ratingHighBoundInitialized != filter.ratingHighBoundInitialized) {
            return false;
        }
        if (Double.compare(filter.ratingHighBound, ratingHighBound) != 0) {
            return false;
        }
        if (numberOfBedsInitialized != filter.numberOfBedsInitialized)
            return false;
        if (numberOfBeds != filter.numberOfBeds) return false;
        if (checkInDate != null ? !checkInDate.equals(filter.checkInDate) :
                filter.checkInDate != null) {
            return false;
        }
        if (checkOutDate != null ? !checkOutDate.equals(filter.checkOutDate) :
                filter.checkOutDate != null) {
            return false;
        }
        return location != null ? location.equals(filter.location) :
                filter.location == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = checkInDate != null ? checkInDate.hashCode() : 0;
        result = 31 * result +
                (checkOutDate != null ? checkOutDate.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (latitudeInitialized ? 1 : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (longtitudeInitialized ? 1 : 0);
        temp = Double.doubleToLongBits(longtitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (costLowBoundInitialized ? 1 : 0);
        temp = Double.doubleToLongBits(costLowBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (costHighBoundInitialized ? 1 : 0);
        temp = Double.doubleToLongBits(costHighBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ratingLowBoundInitialized ? 1 : 0);
        temp = Double.doubleToLongBits(ratingLowBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ratingHighBoundInitialized ? 1 : 0);
        temp = Double.doubleToLongBits(ratingHighBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (numberOfBedsInitialized ? 1 : 0);
        result = 31 * result + numberOfBeds;
        return result;
    }

    @Override
    public String toString() {
        return "RoomSearchServiceFilter{" + "checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate + ", location='" + location +
                '\'' + ", latitudeInitialized=" + latitudeInitialized +
                ", latitude=" + latitude + ", longtitudeInitialized=" +
                longtitudeInitialized + ", longtitude=" + longtitude +
                ", costLowBoundInitialized=" + costLowBoundInitialized +
                ", costLowBound=" + costLowBound +
                ", costHighBoundInitialized=" + costHighBoundInitialized +
                ", costHighBound=" + costHighBound +
                ", ratingLowBoundInitialized=" + ratingLowBoundInitialized +
                ", ratingLowBound=" + ratingLowBound +
                ", ratingHighBoundInitialized=" + ratingHighBoundInitialized +
                ", ratingHighBound=" + ratingHighBound +
                ", numberOfBedsInitialized=" + numberOfBedsInitialized +
                ", numberOfBeds=" + numberOfBeds + '}';
    }
}
