package by.tc.task05.entity;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = -1694724994354286800L;

    private int id;
    private int hotelId;
    private String name;
    private int numberOfBeds;
    private String shortDescription;
    private double cost;
    private String icon;
    public Room() {
    }

    public Room(int id, int hotelId, String name, int numberOfBeds,
                String shortDescription, double cost, String icon) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.numberOfBeds = numberOfBeds;
        this.shortDescription = shortDescription;
        this.cost = cost;
        this.icon = icon;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (hotelId != room.hotelId) return false;
        if (numberOfBeds != room.numberOfBeds) return false;
        if (Double.compare(room.cost, cost) != 0) return false;
        if (name != null ? !name.equals(room.name) : room.name != null) {
            return false;
        }
        if (shortDescription != null ?
                !shortDescription.equals(room.shortDescription) :
                room.shortDescription != null) {
            return false;
        }
        return icon != null ? icon.equals(room.icon) : room.icon == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + hotelId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + numberOfBeds;
        result = 31 * result +
                (shortDescription != null ? shortDescription.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", hotelId=" + hotelId + ", name='" +
                name + '\'' + ", numberOfBeds=" + numberOfBeds +
                ", shortDescription='" + shortDescription + '\'' + ", cost=" +
                cost + ", icon='" + icon + '\'' + '}';
    }
}
