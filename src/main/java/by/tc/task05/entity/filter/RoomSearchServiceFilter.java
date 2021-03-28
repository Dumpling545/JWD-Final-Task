package by.tc.task05.entity.filter;

import java.time.LocalDate;
import java.util.Optional;

public class RoomSearchServiceFilter {
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
}
