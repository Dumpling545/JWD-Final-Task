package by.tc.task05.entity;

import java.io.Serializable;

public class RoomShortView implements Serializable {
    private static final long serialVersionUID = 1206903704279892684L;

    private int id;
    private String name;
    private String address;
    private double cost;
    private String icon;
    private double rating;

    public RoomShortView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomShortView that = (RoomShortView) o;

        if (id != that.id) return false;
        if (Double.compare(that.cost, cost) != 0) return false;
        if (Double.compare(that.rating, rating) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (address != null ? !address.equals(that.address) :
                that.address != null) {
            return false;
        }
        return icon != null ? icon.equals(that.icon) : that.icon == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "RoomShortView{" + "id=" + id + ", name='" + name + '\'' +
                ", address='" + address + '\'' + ", cost=" + cost + ", icon='" +
                icon + '\'' + ", rating=" + rating + '}';
    }
}
