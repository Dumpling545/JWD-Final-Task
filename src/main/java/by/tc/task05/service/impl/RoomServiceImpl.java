package by.tc.task05.service.impl;

import java.util.ArrayList;
import java.util.List;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.DAOProvider;
import by.tc.task05.dao.RoomDAO;
import by.tc.task05.entity.Location;
import by.tc.task05.entity.Room;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.filter.RoomSearchDatabaseFilter;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceException;
import by.tc.task05.service.helper.HelperProvider;
import by.tc.task05.service.helper.LocationHelper;
import by.tc.task05.service.validator.RoomSearchValidator;
import by.tc.task05.service.validator.ValidatorProvider;

public class RoomServiceImpl implements RoomService {
    private final double LATITUDE_DELTA = 0.1;
    private final double LONGTITUDE_DELTA = 0.1;
    @Override
    public List<RoomShortView> getRoomListPage(RoomSearchServiceFilter filter)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        RoomDAO roomDAO = provider.getRoomDAO();
        RoomSearchDatabaseFilter dbFilter = new RoomSearchDatabaseFilter();
        List<RoomShortView> rooms = new ArrayList<>();
        try {
            RoomSearchValidator validator =
                    ValidatorProvider.getInstance().getRoomSearchValidator();
            validator.validateFilter(filter);
            dbFilter.setCheckInDate(filter.getCheckInDate());
            dbFilter.setCheckOutDate(filter.getCheckOutDate());
            dbFilter.setCostLowBound(filter.getCostLowBound());
            dbFilter.setCostHighBound(filter.getCostHighBound());
            dbFilter.setNumberOfBeds(filter.getNumberOfBeds());
            dbFilter.setRatingLowBound(filter.getRatingLowBound());
            dbFilter.setRatingHighBound(filter.getRatingHighBound());
            int skip = (filter.getPage() - 1) * filter.getPageSize();
            dbFilter.setSkipAmount(skip);
            dbFilter.setTakeAmount(filter.getPageSize());
            LocationHelper locationHelper = HelperProvider.getInstance().getLocationHelper();
            if(!filter.getLocation().isBlank()){
                Location location = locationHelper.locate(filter.getLocation());
                filter.setLatitude(location.getLatitude());
                filter.setLongtitude(location.getLongtitude());
            }
            dbFilter.setLatitudeLowBound(filter.getLatitude() - LATITUDE_DELTA);
            dbFilter.setLatitudeHighBound(filter.getLatitude() + LATITUDE_DELTA);
            dbFilter.setLongtitudeLowBound(filter.getLongtitude() - LONGTITUDE_DELTA);
            dbFilter.setLongtitudeHighBound(filter.getLongtitude() + LONGTITUDE_DELTA);
            rooms.addAll(roomDAO.getMany(dbFilter));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rooms;
    }

    @Override
    public List<Room> getRoomListPage(int page, int size)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        RoomDAO roomDAO = provider.getRoomDAO();
        List<Room> rooms = new ArrayList<>();
        try {
            rooms.addAll(roomDAO.skipAndGetMany(size * (page - 1), size));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rooms;
    }

}
