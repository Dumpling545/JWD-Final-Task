package by.tc.task05.entity;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = -567615856330086286L;
    
    private int id;
    private int hotelId;
    private String name;
    private int numberOfBeds;
    private String description;
    private int cost;
    private String icon;

    public Room(int id, int hotelId, String name, int numberOfBeds,
            String description, int cost, String icon) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.numberOfBeds = numberOfBeds;
        this.description = description;
        this.cost = cost;
        this.icon = icon;
    }

    public Room() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
