package by.tc.task05.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.dao.DAOProvider;
import by.tc.task05.dao.RoomDAO;
import by.tc.task05.entity.*;
import by.tc.task05.entity.filter.RoomSearchDatabaseFilter;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.NoSuchRoomException;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.service.exception.UnauthorizedActionException;
import by.tc.task05.service.helper.HelperProvider;
import by.tc.task05.service.helper.LocationHelper;
import by.tc.task05.service.validator.*;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.http.Part;

public class RoomServiceImpl implements RoomService {
    private final double LATITUDE_DELTA = 0.1;
    private final double LONGTITUDE_DELTA = 0.1;

    @Override
    public ListPart<RoomShortView> getViewsByFilter(
            RoomSearchServiceFilter filter, PageInformation pageInformation)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        RoomDAO roomDAO = provider.getRoomDAO();
        RoomSearchDatabaseFilter dbFilter = new RoomSearchDatabaseFilter();
        List<RoomShortView> rooms = new ArrayList<>();
        RoomValidator roomValidator =
                ValidatorProvider.getInstance().getRoomValidator();
        roomValidator.validateFilter(filter);
         PageValidator pageValidator =
                ValidatorProvider.getInstance().getPageValidator();
         pageValidator.validatePage(pageInformation);
        try {
            dbFilter.setCheckInDate(filter.getCheckInDate());
            dbFilter.setCheckOutDate(filter.getCheckOutDate());
            dbFilter.setCostLowBound(filter.getCostLowBound());
            dbFilter.setCostHighBound(filter.getCostHighBound());
            dbFilter.setNumberOfBeds(filter.getNumberOfBeds());
            dbFilter.setRatingLowBound(filter.getRatingLowBound());
            dbFilter.setRatingHighBound(filter.getRatingHighBound());
            int skip = (pageInformation.getPage() - 1) * pageInformation.getPageSize();
            dbFilter.setSkipAmount(skip);
            dbFilter.setTakeAmount(pageInformation.getPageSize() + 1);
            if (!filter.getLocation().isBlank()) {
                LocationHelper locationHelper =
                        HelperProvider.getInstance().getLocationHelper();
                Location location = locationHelper.locate(filter.getLocation());
                filter.setLatitude(location.getLatitude());
                filter.setLongtitude(location.getLongtitude());
            }
            dbFilter.setLatitudeLowBound(filter.getLatitude() - LATITUDE_DELTA);
            dbFilter.setLatitudeHighBound(
                    filter.getLatitude() + LATITUDE_DELTA);
            dbFilter.setLongtitudeLowBound(
                    filter.getLongtitude() - LONGTITUDE_DELTA);
            dbFilter.setLongtitudeHighBound(
                    filter.getLongtitude() + LONGTITUDE_DELTA);
            rooms.addAll(roomDAO.getViewsByFilter(dbFilter));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        boolean last = (rooms.size() <= pageInformation.getPageSize());
        if (!last) {
            rooms.remove(rooms.size() - 1);
        }
        return new ListPart<RoomShortView>(rooms, last);
    }

    @Override
    public boolean isRoomAdministrator(int userId, int roomId)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        RoomDAO roomDAO = provider.getRoomDAO();
        boolean result = false;
        try {
            result = roomDAO.isRoomAdministrator(userId, roomId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Room getById(int roomId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        Optional<Room> result = Optional.empty();
        RoomDAO roomDAO = provider.getRoomDAO();
        try {
            result = roomDAO.getById(roomId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (result.isEmpty()) {
            throw new NoSuchRoomException();
        }
        return result.get();
    }

    @Override
    public ListPart<Room> getByHotel(int hotelId, PageInformation page)
            throws ServiceException {
        PageValidator pageValidator =
                ValidatorProvider.getInstance().getPageValidator();
        pageValidator.validatePage(page);
        DAOProvider provider = DAOProvider.getInstance();
        RoomDAO roomDAO = provider.getRoomDAO();
        List<Room> rooms = new ArrayList<>();
        try {
            int skip = (page.getPage() - 1) * page.getPageSize();
            int take = page.getPageSize();
            rooms.addAll(roomDAO.getByHotel(hotelId, skip, take + 1));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        boolean last = (rooms.size() <= page.getPageSize());
        if (!last) {
            rooms.remove(rooms.size() - 1);
        }
        return new ListPart<Room>(rooms, last);
    }

    @Override
    public void add(int userId, Room room, String password)
            throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RoomDAO roomDAO = daoProvider.getRoomDAO();
        UserService userService = serviceProvider.getUserService();
        RoomValidator validator =
                ValidatorProvider.getInstance().getRoomValidator();
        validator.validateRoom(room);
        HotelService hotelService = serviceProvider.getHotelService();
        try {
            if (userService.isPasswordMatched(userId, password) && hotelService
                    .isHotelAdministrator(userId, room.getHotelId())) {
                roomDAO.add(room);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void change(int userId, Room room, String password)
            throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        RoomDAO roomDAO = daoProvider.getRoomDAO();
        RoomValidator validator =
                ValidatorProvider.getInstance().getRoomValidator();
        validator.validateRoom(room);
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    roomDAO.isRoomAdministrator(userId, room.getId())) {
                roomDAO.change(room);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setIcon(int userId, int roomId, Part icon, String password)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        RoomDAO roomDAO = provider.getRoomDAO();
        ImageValidator validator =
                ValidatorProvider.getInstance().getImageValidator();
        validator.validateImage(icon);
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    roomDAO.isRoomAdministrator(userId, roomId)) {
                roomDAO.setIcon(roomId, icon);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void remove(int userId, int roomId, String password)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        RoomDAO roomDAO = provider.getRoomDAO();
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    roomDAO.isRoomAdministrator(userId, roomId)) {
                roomDAO.remove(roomId);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<RoomFeature> getRoomFeatureById(int roomId)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        Optional<RoomFeature> result = Optional.empty();
        RoomDAO roomDAO = provider.getRoomDAO();
        try {
            result = roomDAO.getRoomFeatureById(roomId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public void addFeature(int userId, RoomFeature feature, String password)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        RoomDAO roomDAO = provider.getRoomDAO();
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    roomDAO.isRoomAdministrator(userId, feature.getRoomId())) {
                roomDAO.addFeature(feature);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeFeature(int userId, RoomFeature feature, String password)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        RoomDAO roomDAO = provider.getRoomDAO();
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    roomDAO.isRoomAdministrator(userId, feature.getRoomId())) {
                roomDAO.changeFeature(feature);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeFeature(int userId, int roomId, String password)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        RoomDAO roomDAO = provider.getRoomDAO();
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    roomDAO.isRoomAdministrator(userId, roomId)) {
                roomDAO.removeFeature(roomId);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RoomPhoto> getPhotosByRoom(int roomId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        RoomDAO roomDAO = provider.getRoomDAO();
        List<RoomPhoto> roomPhotos = new ArrayList<>();
        try {
            roomPhotos.addAll(roomDAO.getPhotosByRoom(roomId));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return roomPhotos;
    }

    @Override
    public void addPhotos(int userId, int roomId, List<Part> photos,
                          String password) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        RoomDAO roomDAO = provider.getRoomDAO();
        ImageValidator validator =
                ValidatorProvider.getInstance().getImageValidator();
        for (Part photo : photos) {
            validator.validateImage(photo);
        }
        try {
            if (userService.isPasswordMatched(userId, password) &&
                    roomDAO.isRoomAdministrator(userId, roomId)) {
                roomDAO.addPhotos(roomId, photos);
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removePhotos(int userId, int roomId, List<Integer> photoIds,
                             String password) throws ServiceException {
        //TODO ...
    }

}
