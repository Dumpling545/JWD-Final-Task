package by.tc.task05.entity;

import java.time.LocalDate;

public class ExtendedReservation extends Reservation {
    private static final long serialVersionUID = 3428028724625541511L;
    private String roomName;
    private String hotelName;
    private int hotelId;
    private String userEmail;

    public static final int REJECTED = 0;
    public static final int ENDED_SUCCESSFULLY = 1;
    public static final int CANCELLED = 2;

    public ExtendedReservation() {
    }

    public ExtendedReservation(int id, int userId, int roomId,
                               LocalDate checkInDate, LocalDate checkOutDate,
                               String paymentToken, int status,
                               double paymentAmount, String roomName,
                               String hotelName, int hotelId,
                               String userEmail) {
        super(id, userId, roomId, checkInDate, checkOutDate, paymentToken,
                status, paymentAmount);
        this.roomName = roomName;
        this.hotelName = hotelName;
        this.hotelId = hotelId;
        this.userEmail = userEmail;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ExtendedReservation that = (ExtendedReservation) o;

        if (hotelId != that.hotelId) return false;
        if (roomName != null ? !roomName.equals(that.roomName) :
                that.roomName != null) {
            return false;
        }
        if (hotelName != null ? !hotelName.equals(that.hotelName) :
                that.hotelName != null) {
            return false;
        }
        return userEmail != null ? userEmail.equals(that.userEmail) :
                that.userEmail == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (roomName != null ? roomName.hashCode() : 0);
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + hotelId;
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.deleteCharAt(sb.indexOf("}"));
        sb.append(", roomName='").append(roomName).append('\'').append('}');
        sb.append(", hotelName='").append(hotelName).append('\'');
        sb.append(", hotelId=").append(hotelId);
        sb.append(", userEmail='").append(userEmail).append('\'').append('}');
        return sb.toString();
    }
}
