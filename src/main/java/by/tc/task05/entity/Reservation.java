package by.tc.task05.entity;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 8682604997339145083L;
    private int id;
    private int userId;
    private int hotelId;
    private int mealPlanId;
    private Date checkInDate;
    private int numberOfNights;
    private String paymentToken;
    private ReservationStatus status;

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(int mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (hotelId != that.hotelId) return false;
        if (mealPlanId != that.mealPlanId) return false;
        if (numberOfNights != that.numberOfNights) return false;
        if (checkInDate != null ? !checkInDate.equals(that.checkInDate) :
                that.checkInDate != null) {
            return false;
        }
        if (paymentToken != null ? !paymentToken.equals(that.paymentToken) :
                that.paymentToken != null) {
            return false;
        }
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + hotelId;
        result = 31 * result + mealPlanId;
        result = 31 * result +
                (checkInDate != null ? checkInDate.hashCode() : 0);
        result = 31 * result + numberOfNights;
        result = 31 * result +
                (paymentToken != null ? paymentToken.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", userId=" + userId +
                ", hotelId=" + hotelId + ", mealPlanId=" + mealPlanId +
                ", checkInDate=" + checkInDate + ", numberOfNights=" +
                numberOfNights + ", paymentToken='" + paymentToken + '\'' +
                ", status=" + status + '}';
    }
}
