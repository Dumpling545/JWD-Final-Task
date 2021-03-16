package by.tc.task05.service;

import java.util.List;
import by.tc.task05.entity.Room;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;

public interface RoomService {
    List<Room> getRoomListPage(int page, int size) throws ServiceException;
    List<RoomShortView> getRoomListPage(RoomSearchServiceFilter filter) throws ServiceException;
}
