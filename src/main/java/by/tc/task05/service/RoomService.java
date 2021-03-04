package by.tc.task05.service;

import java.util.List;
import by.tc.task05.entity.Room;

public interface RoomService {
    List<Room> getRoomListPage(int page, int size) throws ServiceException;
}
