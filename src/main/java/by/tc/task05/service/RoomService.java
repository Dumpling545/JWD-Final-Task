package by.tc.task05.service;

import java.util.List;
import java.util.Optional;

import by.tc.task05.dao.DAOException;
import by.tc.task05.entity.*;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.http.Part;

public interface RoomService {
    ListPart<RoomShortView> getRoomListPage(RoomSearchServiceFilter filter)
            throws ServiceException;


    public boolean isRoomAdministrator(int userId, int roomId)
            throws ServiceException;

    public Room getById(int roomId) throws ServiceException;

    public ListPart<Room> getByHotel(int hotelId, PageInformation page)
            throws ServiceException;

    public void add(int userId, Room room, String password)
            throws ServiceException;

    public void change(int userId, Room room, String password)
            throws ServiceException;

    public void setIcon(int userId, int roomId, Part icon, String password)
            throws ServiceException;

    public void remove(int userId, int roomId, String password)
            throws ServiceException;

    public Optional<RoomFeature> getRoomFeatureById(int roomId)
            throws ServiceException;

    public void addFeature(int userId, RoomFeature feature, String password)
            throws ServiceException;

    public void changeFeature(int userId, RoomFeature feature, String password)
            throws ServiceException;

    public void removeFeature(int userId, int roomId, String password)
            throws ServiceException;

    public List<RoomPhoto> getPhotosByRoom(int roomId) throws ServiceException;

    public void addPhotos(int userId, int roomId, List<Part> photos,
                          String password) throws ServiceException;

    public void removePhotos(int userId, int roomId, List<Integer> photoIds,
                             String password) throws ServiceException;
}
