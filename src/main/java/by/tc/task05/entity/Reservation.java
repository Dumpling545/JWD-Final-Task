package by.tc.task05.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
/**
 * (Active) Reservation entity that matches database structure
 */
public class Reservation implements Serializable {
    private static final long serialVersionUID = 8682604997339145083L;
    private int id;
    private int userId;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String paymentToken;
    private int status;
    private double paymentAmount;
    /**
     * Status of active reservation which means that hotel owner does not accepted it
     */
    public static final int PROCESSING = 0;
    /**
     * Status of active reservation which means that hotel owner has already accepted it
     */
    public static final int ACCEPTED = 1;


    public Reservation() {}

    public Reservation(int id, int userId, int roomId, LocalDate checkInDate,
                       LocalDate checkOutDate, String paymentToken, int status,
                       double paymentAmount) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.paymentToken = paymentToken;
        this.status = status;
        this.paymentAmount = paymentAmount;
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (roomId != that.roomId) return false;
        if (status != that.status) return false;
        if (Double.compare(that.paymentAmount, paymentAmount) != 0) {
            return false;
        }
        if (checkInDate != null ? !checkInDate.equals(that.checkInDate) :
                that.checkInDate != null) {
            return false;
        }
        if (checkOutDate != null ? !checkOutDate.equals(that.checkOutDate) :
                that.checkOutDate != null) {
            return false;
        }
        return paymentToken != null ? paymentToken.equals(that.paymentToken) :
                that.paymentToken == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + userId;
        result = 31 * result + roomId;
        result = 31 * result +
                (checkInDate != null ? checkInDate.hashCode() : 0);
        result = 31 * result +
                (checkOutDate != null ? checkOutDate.hashCode() : 0);
        result = 31 * result +
                (paymentToken != null ? paymentToken.hashCode() : 0);
        result = 31 * result + status;
        temp = Double.doubleToLongBits(paymentAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", userId=" + userId +
                ", roomId=" + roomId + ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate + ", paymentToken='" +
                paymentToken + '\'' + ", status=" + status +
                ", paymentAmount=" + paymentAmount + '}';
    }
}
