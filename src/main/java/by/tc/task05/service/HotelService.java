package by.tc.task05.service;

import by.tc.task05.dao.DAOException;
import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.HotelForm;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.User;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.http.Part;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    public boolean isHotelAdministrator(int userId, int hotelId)
            throws ServiceException;
    public ListPart<Hotel> getAdministeredBy(int userId, PageInformation page)
            throws ServiceException;
    public Hotel getById(int hotelId) throws ServiceException;
    public void addWithAdministrator(int userId, HotelForm hotelForm)
            throws ServiceException;
    public void change(int userId, HotelForm hotelForm, String password)
            throws ServiceException;
    public void setIcon(int userId, int hotelId, Part icon, String password)
            throws ServiceException;
    public void remove(int userId, int hotelId, String password)
            throws ServiceException;
    public void addAdministratorByEmail(int userId, String email, int hotelId,
                                 String password) throws ServiceException;
    public void removeAdministrator(int userId, int adminId, int hotelId,
                                    String password) throws ServiceException;
    public ListPart<User> getAdministratorsByHotel(int userId, int hotelId,
                                                   PageInformation page)
            throws ServiceException;
}
