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

    public Review() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (reservationId != review.reservationId) return false;
        if (rating != review.rating) return false;
        if (title != null ? !title.equals(review.title) :
                review.title != null) {
            return false;
        }
        if (text != null ? !text.equals(review.text) : review.text != null) {
            return false;
        }
        return date != null ? date.equals(review.date) : review.date == null;
    }

    @Override
    public int hashCode() {
        int result = reservationId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Review{" + "reservationId=" + reservationId + ", title='" +
                title + '\'' + ", text='" + text + '\'' + ", rating=" + rating +
                ", date=" + date + '}';
    }
}
