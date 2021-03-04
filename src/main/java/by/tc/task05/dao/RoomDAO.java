package by.tc.task05.dao;

import java.util.List;
import by.tc.task05.entity.Room;

public interface RoomDAO {
    public List<Room> skipAndGetMany(int skip, int number) throws DAOException;
}
