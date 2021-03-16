package by.tc.task05.entity.filter;

import java.time.LocalDate;

public class RoomSearchDatabaseFilter {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double latitudeLowBound;
    private double latitudeHighBound;
    private double longtitudeLowBound;
    private double longtitudeHighBound;
    private double costLowBound;
    private double costHighBound;
    private double ratingLowBound;
    private double ratingHighBound;
    private int numberOfBeds;
    private int skipAmount;
    private int takeAmount;

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

    public double getLongtitudeLowBound() {
        return longtitudeLowBound;
    }

    public void setLongtitudeLowBound(double longtitudeLowBound) {
        this.longtitudeLowBound = longtitudeLowBound;
    }

    public double getLongtitudeHighBound() {
        return longtitudeHighBound;
    }

    public void setLongtitudeHighBound(double longtitudeHighBound) {
        this.longtitudeHighBound = longtitudeHighBound;
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
}
