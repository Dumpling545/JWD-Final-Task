package by.tc.task05.entity;

/**
 * {@link Room} entity that extended with hotel info and rating
 */
public class ExtendedRoom extends Room {
    private static final long serialVersionUID = 1206903704279892684L;
    private String address;
    private double rating;
    private String hotelName;

    public ExtendedRoom() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ExtendedRoom that = (ExtendedRoom) o;

        if (Double.compare(that.rating, rating) != 0) return false;
        if (address != null ? !address.equals(that.address) :
                that.address != null) {
            return false;
        }
        return hotelName != null ? hotelName.equals(that.hotelName) :
                that.hotelName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.deleteCharAt(sb.indexOf("}"));
        sb.append(", address='").append(address).append('\'');
        sb.append(", rating=").append(rating);
        sb.append(", hotelName='").append(hotelName).append("'}");
        return sb.toString();
    }
}
