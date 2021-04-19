package by.tc.task05.service.impl;

import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.dao.DAOProvider;
import by.tc.task05.dao.HotelDAO;
import by.tc.task05.dao.UserDAO;
import by.tc.task05.dao.exception.RoomOrHotelWithActiveReservationsDeletionDAOException;
import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.HotelForm;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.User;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.*;
import by.tc.task05.service.validator.HotelValidator;
import by.tc.task05.service.validator.ImageValidator;
import by.tc.task05.service.validator.PageValidator;
import by.tc.task05.service.validator.ValidatorProvider;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.http.Part;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelServiceImpl implements HotelService {
    private static final int AT_LEAST_TWO_TEST_SKIP = 0;
    private static final int AT_LEAST_TWO_TEST_TAKE = 2;

    @Override
    public boolean isHotelAdministrator(int userId, int hotelId)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        HotelDAO hotelDAO = provider.getHotelDAO();
        boolean result = false;
        try {
            result = hotelDAO.isHotelAdministrator(userId, hotelId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public ListPart<Hotel> getAdministeredBy(int userId, PageInformation page)
            throws ServiceException {
        PageValidator pageValidator =
                ValidatorProvider.getInstance().getPageValidator();
        pageValidator.validatePage(page);
        DAOProvider provider = DAOProvider.getInstance();
        HotelDAO hotelDAO = provider.getHotelDAO();
        List<Hotel> hotels = new ArrayList<>();
        try {
            int skip = (page.getPage() - 1) * page.getPageSize();
            int take = page.getPageSize();
            hotels.addAll(hotelDAO.getAdministeredBy(userId, skip, take + 1));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        boolean last = (hotels.size() <= page.getPageSize());
        if (!last) {
            hotels.remove(hotels.size() - 1);
        }
        return new ListPart<Hotel>(hotels, last);
    }

    @Override
    public Hotel getById(int hotelId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        Optional<Hotel> result = Optional.empty();
        HotelDAO hotelDAO = provider.getHotelDAO();
        try {
            result = hotelDAO.getById(hotelId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (result.isEmpty()) {
            throw new NoSuchHotelException();
        }
        return result.get();
    }

    @Override
    public void addWithAdministrator(int userId, HotelForm hotelForm)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        HotelDAO hotelDAO = provider.getHotelDAO();
        HotelValidator validator =
                ValidatorProvider.getInstance().getHotelValidator();
        validator.validateHotelForm(hotelForm);
        try {
            hotelDAO.addWithAdministrator(userId, hotelForm);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void change(int userId, HotelForm hotelForm, String password)
            throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        HotelDAO hotelDAO = daoProvider.getHotelDAO();
        HotelValidator validator =
                ValidatorProvider.getInstance().getHotelValidator();
        validator.validateHotelForm(hotelForm);
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    hotelDAO.isHotelAdministrator(userId, hotelForm.getId())) {
                hotelDAO.change(hotelForm);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setIcon(int userId, int hotelId, Part icon, String password)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        HotelDAO hotelDAO = provider.getHotelDAO();
        ImageValidator validator =
                ValidatorProvider.getInstance().getImageValidator();
        validator.validateImage(icon);
        boolean isPwMatched = false;
        boolean isAdmin = false;
        try {
            isPwMatched = userService.isPasswordMatched(userId, password);
            isAdmin = hotelDAO.isHotelAdministrator(userId, hotelId);
            if (isPwMatched && isAdmin) {
                hotelDAO.setIcon(hotelId, icon);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void remove(int userId, int hotelId, String password)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        HotelDAO hotelDAO = provider.getHotelDAO();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    hotelDAO.isHotelAdministrator(userId, hotelId)) {
                hotelDAO.remove(hotelId);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (RoomOrHotelWithActiveReservationsDeletionDAOException e) {
            throw new RoomOrHotelWithActiveReservationsDeletionException(e);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addAdministratorByEmail(int userId, String adminEmail,
                                        int hotelId, String password)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        HotelDAO hotelDAO = provider.getHotelDAO();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        UserDAO userDAO = provider.getUserDAO();
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    hotelDAO.isHotelAdministrator(userId, hotelId)) {
                Optional<User> user = userDAO.getByEmail(adminEmail);
                if (user.isPresent()) {
                    boolean isAdmin =
                            hotelDAO.isHotelAdministrator(user.get().getId(),
                                    hotelId);
                    if (!isAdmin) {
                        hotelDAO.addAdministrator(user.get().getId(), hotelId);
                    } else {
                        throw new AlreadyAdminException();
                    }
                } else {
                    throw new InvalidUserException();
                }
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeAdministrator(int userId, int adminId, int hotelId,
                                    String password) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        HotelDAO hotelDAO = provider.getHotelDAO();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    hotelDAO.isHotelAdministrator(userId, hotelId)) {
                List<User> admins = hotelDAO.getAdministratorsByHotel(hotelId,
                        AT_LEAST_TWO_TEST_SKIP, AT_LEAST_TWO_TEST_TAKE);
                if (admins.size() == AT_LEAST_TWO_TEST_TAKE) {
                    hotelDAO.removeAdministrator(adminId, hotelId);
                } else {
                    throw new LastAdministratorRemovalException();
                }
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ListPart<User> getAdministratorsByHotel(int userId, int hotelId,
                                                   PageInformation page)
            throws ServiceException {
        PageValidator pageValidator =
                ValidatorProvider.getInstance().getPageValidator();
        pageValidator.validatePage(page);
        DAOProvider provider = DAOProvider.getInstance();
        HotelDAO hotelDAO = provider.getHotelDAO();
        List<User> admins = new ArrayList<>();
        try {
            if (hotelDAO.isHotelAdministrator(userId, hotelId)) {
                int skip = (page.getPage() - 1) * page.getPageSize();
                int take = page.getPageSize();
                admins.addAll(hotelDAO.getAdministratorsByHotel(hotelId, skip,
                        take + 1));
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        boolean last = (admins.size() <= page.getPageSize());
        if (!last) {
            admins.remove(admins.size() - 1);
        }
        return new ListPart<User>(admins, last);
    }
}
