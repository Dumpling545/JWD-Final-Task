package by.tc.task05.entity;

import java.io.Serializable;

public class Location implements Serializable {
    
    private static final long serialVersionUID = -1835836785721884262L;

    private double latitude;
    private double longtitude;
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
    public Location(double latitude, double longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}
