package by.tc.task05.dao;

import java.util.Optional;

import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.HotelForm;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.User;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.http.Part;

import java.util.List;

public interface HotelDAO {
    public boolean isHotelAdministrator(int userId, int hotelId)
            throws DAOException;

    public List<Hotel> getAdministeredBy(int userId, int skip, int take) throws DAOException;

    public Optional<Hotel> getById(int hotelId) throws DAOException;

    public void addWithAdministrator(int userId, HotelForm hotelForm)
            throws DAOException;

    public void change(HotelForm hotelForm) throws DAOException;

    public void setIcon(int hotelId, Part icon) throws DAOException;

    public void remove(int hotelId) throws DAOException;

    public void addAdministrator(int userId, int hotelId) throws DAOException;

    public void removeAdministrator(int userId, int hotelId)
            throws DAOException;

    public List<User> getAdministratorsByHotel(int hotelId, int skip, int take) throws DAOException;
}
