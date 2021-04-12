package by.tc.task05.entity.filter;

import java.time.LocalDate;
import java.util.Optional;

public class RoomSearchServiceFilter {
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
    private boolean pageInitialized = false;
    private int page;
    private boolean pageSizeInitialized = false;
    private int pageSize;

    public RoomSearchServiceFilter() {}

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

    public double getCostLowBound() {
        return costLowBound;
    }

    public void setCostLowBound(double costLowBound) {
        costHighBoundInitialized = true;
        this.costLowBound = costLowBound;
    }

    public double getCostHighBound() {
        return costHighBound;
    }

    public void setCostHighBound(double costHighBound) {
        costHighBoundInitialized = true;
        this.costHighBound = costHighBound;
    }

    public double getRatingLowBound() {
        return ratingLowBound;
    }

    public void setRatingLowBound(double ratingLowBound) {
        ratingLowBoundInitialized = true;
        this.ratingLowBound = ratingLowBound;
    }

    public double getRatingHighBound() {
        return ratingHighBound;
    }

    public void setRatingHighBound(double ratingHighBound) {
        ratingHighBoundInitialized = true;
        this.ratingHighBound = ratingHighBound;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        numberOfBedsInitialized = true;
        this.numberOfBeds = numberOfBeds;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        pageInitialized = true;
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        pageSizeInitialized = true;
        this.pageSize = pageSize;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        latitudeInitialized = true;
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        longtitudeInitialized = true;
        this.longtitude = longtitude;
    }

    public boolean isLatitudeInitialized() {
        return latitudeInitialized;
    }

    public boolean isLongtitudeInitialized() {
        return longtitudeInitialized;
    }

    public boolean isCostLowBoundInitialized() {
        return costLowBoundInitialized;
    }

    public boolean isCostHighBoundInitialized() {
        return costHighBoundInitialized;
    }

    public boolean isRatingLowBoundInitialized() {
        return ratingLowBoundInitialized;
    }

    public boolean isRatingHighBoundInitialized() {
        return ratingHighBoundInitialized;
    }

    public boolean isNumberOfBedsInitialized() {
        return numberOfBedsInitialized;
    }

    public boolean isPageInitialized() {
        return pageInitialized;
    }

    public boolean isPageSizeInitialized() {
        return pageSizeInitialized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomSearchServiceFilter that = (RoomSearchServiceFilter) o;

        if (latitudeInitialized != that.latitudeInitialized) return false;
        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (longtitudeInitialized != that.longtitudeInitialized) return false;
        if (Double.compare(that.longtitude, longtitude) != 0) return false;
        if (costLowBoundInitialized != that.costLowBoundInitialized) return false;
        if (Double.compare(that.costLowBound, costLowBound) != 0) return false;
        if (costHighBoundInitialized != that.costHighBoundInitialized) return false;
        if (Double.compare(that.costHighBound, costHighBound) != 0) return false;
        if (ratingLowBoundInitialized != that.ratingLowBoundInitialized) return false;
        if (Double.compare(that.ratingLowBound, ratingLowBound) != 0) return false;
        if (ratingHighBoundInitialized != that.ratingHighBoundInitialized) return false;
        if (Double.compare(that.ratingHighBound, ratingHighBound) != 0) return false;
        if (numberOfBedsInitialized != that.numberOfBedsInitialized) return false;
        if (numberOfBeds != that.numberOfBeds) return false;
        if (pageInitialized != that.pageInitialized) return false;
        if (page != that.page) return false;
        if (pageSizeInitialized != that.pageSizeInitialized) return false;
        if (pageSize != that.pageSize) return false;
        if (checkInDate != null ? !checkInDate.equals(that.checkInDate) : that.checkInDate != null) return false;
        if (checkOutDate != null ? !checkOutDate.equals(that.checkOutDate) : that.checkOutDate != null) return false;
        return location != null ? location.equals(that.location) : that.location == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = checkInDate != null ? checkInDate.hashCode() : 0;
        result = 31 * result + (checkOutDate != null ? checkOutDate.hashCode() : 0);
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
        result = 31 * result + (pageInitialized ? 1 : 0);
        result = 31 * result + page;
        result = 31 * result + (pageSizeInitialized ? 1 : 0);
        result = 31 * result + pageSize;
        return result;
    }

    @Override
    public String toString() {
        return "RoomSearchServiceFilter{" +
                "checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", location='" + location + '\'' +
                ", latitudeInitialized=" + latitudeInitialized +
                ", latitude=" + latitude +
                ", longtitudeInitialized=" + longtitudeInitialized +
                ", longtitude=" + longtitude +
                ", costLowBoundInitialized=" + costLowBoundInitialized +
                ", costLowBound=" + costLowBound +
                ", costHighBoundInitialized=" + costHighBoundInitialized +
                ", costHighBound=" + costHighBound +
                ", ratingLowBoundInitialized=" + ratingLowBoundInitialized +
                ", ratingLowBound=" + ratingLowBound +
                ", ratingHighBoundInitialized=" + ratingHighBoundInitialized +
                ", ratingHighBound=" + ratingHighBound +
                ", numberOfBedsInitialized=" + numberOfBedsInitialized +
                ", numberOfBeds=" + numberOfBeds +
                ", pageInitialized=" + pageInitialized +
                ", page=" + page +
                ", pageSizeInitialized=" + pageSizeInitialized +
                ", pageSize=" + pageSize +
                '}';
    }
}
