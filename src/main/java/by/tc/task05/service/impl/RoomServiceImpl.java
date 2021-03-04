package by.tc.task05.service.impl;

import java.util.ArrayList;
import java.util.List;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.DAOProvider;
import by.tc.task05.dao.RoomDAO;
import by.tc.task05.entity.Room;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceException;

public class RoomServiceImpl implements RoomService {

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
