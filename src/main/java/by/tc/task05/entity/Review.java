package by.tc.task05.entity;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    
    private static final long serialVersionUID = 5704141413366695697L;
    private int reservationId;
    private String title;
    private String text;
    private int rating;
    private Date date;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
