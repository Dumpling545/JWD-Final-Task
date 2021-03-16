package by.tc.task05.entity.filter;

import java.time.LocalDate;

public class RoomSearchServiceFilter {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String location;
    private double latitude;
    private double longtitude;
    private double costLowBound;
    private double costHighBound;
    private double ratingLowBound;
    private double ratingHighBound;
    private int numberOfBeds;
    private int page;
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
        this.costLowBound = costLowBound;
    }
    public double getCostHighBound() {
        return costHighBound;
    }
    public void setCostHighBound(double costHighBound) {
        this.costHighBound = costHighBound;
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
    public int getNumberOfBeds() {
        return numberOfBeds;
    }
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongtitude() {
        return longtitude;
    }
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
