package by.tc.task05.service;

import java.util.List;
import by.tc.task05.entity.Room;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.exception.ServiceException;

public interface RoomService {
    List<RoomShortView> getRoomListPage(RoomSearchServiceFilter filter) throws ServiceException;
}
