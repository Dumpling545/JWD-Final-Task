package by.tc.task05.entity;

import java.io.Serializable;

public class RoomPhoto implements Serializable {
    private static final long serialVersionUID = -862696526263282505L;
    private int id;
    private int roomId;
    private String extension;
    public final static String RELATIVE_ROOM_PHOTO_PATH = "rooms/photos/";
    public RoomPhoto() {}


    public RoomPhoto(int id, int roomId, String extension) {
        this.id = id;
        this.roomId = roomId;
        this.extension = extension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomPhoto photo = (RoomPhoto) o;

        if (id != photo.id) return false;
        if (roomId != photo.roomId) return false;
        return extension != null ? extension.equals(photo.extension) :
                photo.extension == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + roomId;
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoomPhoto{" + "id=" + id + ", roomId=" + roomId +
                ", extension='" + extension + '\'' + '}';
    }
}
