package by.tc.task05.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.RoomDAO;
import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import by.tc.task05.entity.Room;

public class SQLRoomDAO implements RoomDAO {
    private final String SKIP_AND_GET_MANY =
            "SELECT (id, hotel_id, name, number_of_beds, description, cost, icon) "
                    + " FROM rooms LIMIT ? OFFSET ?";

    @Override
    public List<Room> skipAndGetMany(int skip, int number) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Room> rooms = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement =
                    connection.prepareStatement(SKIP_AND_GET_MANY);
            preparedStatement.setInt(1, number);
            preparedStatement.setInt(2, skip);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt(1));
                room.setHotelId(resultSet.getInt(2));
                room.setName(resultSet.getString(3));
                room.setNumberOfBeds(resultSet.getInt(4));
                room.setDescription(resultSet.getString(5));
                room.setCost(resultSet.getInt(6));
                room.setIcon(resultSet.getString(7));
                rooms.add(room);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return rooms;
    }

}
