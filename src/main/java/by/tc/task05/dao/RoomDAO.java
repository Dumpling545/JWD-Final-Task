package by.tc.task05.dao;

import java.util.List;
import by.tc.task05.entity.Room;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.filter.RoomSearchDatabaseFilter;

public interface RoomDAO {
    public List<RoomShortView> getMany(RoomSearchDatabaseFilter filter)
            throws DAOException;
}
