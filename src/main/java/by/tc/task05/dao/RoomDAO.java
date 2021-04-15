package by.tc.task05.dao;

import java.util.List;
import java.util.Optional;

import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.entity.*;
import by.tc.task05.entity.filter.RoomSearchDatabaseFilter;
import jakarta.servlet.http.Part;

public interface RoomDAO {
    public List<RoomShortView> getViewsByFilter(RoomSearchDatabaseFilter filter)
            throws DAOException;
    public boolean isRoomAdministrator(int userId, int roomId)
            throws DAOException;
    public Optional<Room> getById(int roomId) throws DAOException;
    public List<Room> getByHotel(int hotelId, int skip, int take) throws DAOException;
    public void add(Room room) throws DAOException;
    public void change(Room room) throws DAOException;
    public void setIcon(int roomId, Part icon) throws DAOException;
    public void remove(int roomId) throws DAOException;
    public Optional<RoomFeature> getRoomFeatureById(int roomId) throws DAOException;
    public void addFeature(RoomFeature feature) throws DAOException;
    public void changeFeature(RoomFeature feature) throws DAOException;
    public void removeFeature(int roomId) throws DAOException;
    public List<RoomPhoto> getPhotosByRoom(int roomId) throws DAOException;
    public void addPhotos(int roomId, List<Part> photos) throws DAOException;
    public void removePhotos(int roomId, List<Integer> photoIds) throws DAOException;
}
