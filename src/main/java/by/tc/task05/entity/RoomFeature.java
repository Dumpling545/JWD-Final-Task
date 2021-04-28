package by.tc.task05.entity;

import java.io.Serializable;
/**
 * Room feature entity that matches database structure
 */
public class RoomFeature implements Serializable {
    private static final long serialVersionUID = 3141244771836615973L;

    private int roomId;
    private boolean hasAirconditioning;
    private boolean hasHeating;
    private boolean hasBalcony;
    private boolean hasTV;
    private boolean hasRefrigerator;
    private boolean hasKitchen;
    private boolean hasHairDryer;
    private boolean hasToilet;
    private boolean hasShower;
    private boolean hasWashingMachine;
    private String description;
    public RoomFeature() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public boolean isHasAirconditioning() {
        return hasAirconditioning;
    }

    public void setHasAirconditioning(boolean hasAirconditioning) {
        this.hasAirconditioning = hasAirconditioning;
    }

    public boolean isHasHeating() {
        return hasHeating;
    }

    public void setHasHeating(boolean hasHeating) {
        this.hasHeating = hasHeating;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean isHasRefrigerator() {
        return hasRefrigerator;
    }

    public void setHasRefrigerator(boolean hasRefrigerator) {
        this.hasRefrigerator = hasRefrigerator;
    }

    public boolean isHasKitchen() {
        return hasKitchen;
    }

    public void setHasKitchen(boolean hasKitchen) {
        this.hasKitchen = hasKitchen;
    }

    public boolean isHasHairDryer() {
        return hasHairDryer;
    }

    public void setHasHairDryer(boolean hasHairDryer) {
        this.hasHairDryer = hasHairDryer;
    }

    public boolean isHasToilet() {
        return hasToilet;
    }

    public void setHasToilet(boolean hasToilet) {
        this.hasToilet = hasToilet;
    }

    public boolean isHasShower() {
        return hasShower;
    }

    public void setHasShower(boolean hasShower) {
        this.hasShower = hasShower;
    }

    public boolean isHasWashingMachine() {
        return hasWashingMachine;
    }

    public void setHasWashingMachine(boolean hasWashingMachine) {
        this.hasWashingMachine = hasWashingMachine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomFeature that = (RoomFeature) o;

        if (roomId != that.roomId) return false;
        if (hasAirconditioning != that.hasAirconditioning) return false;
        if (hasHeating != that.hasHeating) return false;
        if (hasBalcony != that.hasBalcony) return false;
        if (hasTV != that.hasTV) return false;
        if (hasRefrigerator != that.hasRefrigerator) return false;
        if (hasKitchen != that.hasKitchen) return false;
        if (hasHairDryer != that.hasHairDryer) return false;
        if (hasToilet != that.hasToilet) return false;
        if (hasShower != that.hasShower) return false;
        if (hasWashingMachine != that.hasWashingMachine) return false;
        return description != null ? description.equals(that.description) :
                that.description == null;
    }

    @Override
    public int hashCode() {
        int result = roomId;
        result = 31 * result + (hasAirconditioning ? 1 : 0);
        result = 31 * result + (hasHeating ? 1 : 0);
        result = 31 * result + (hasBalcony ? 1 : 0);
        result = 31 * result + (hasTV ? 1 : 0);
        result = 31 * result + (hasRefrigerator ? 1 : 0);
        result = 31 * result + (hasKitchen ? 1 : 0);
        result = 31 * result + (hasHairDryer ? 1 : 0);
        result = 31 * result + (hasToilet ? 1 : 0);
        result = 31 * result + (hasShower ? 1 : 0);
        result = 31 * result + (hasWashingMachine ? 1 : 0);
        result = 31 * result +
                (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoomFeature{" + "roomId=" + roomId + ", hasAirconditioning=" +
                hasAirconditioning + ", hasHeating=" + hasHeating +
                ", hasBalcony=" + hasBalcony + ", hasTV=" + hasTV +
                ", hasRefrigerator=" + hasRefrigerator + ", hasKitchen=" +
                hasKitchen + ", hasHairDryer=" + hasHairDryer + ", hasToilet=" +
                hasToilet + ", hasShower=" + hasShower +
                ", hasWashingMachine=" + hasWashingMachine + ", description='" +
                description + '\'' + '}';
    }
}
