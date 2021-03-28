package by.tc.task05.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.RoomDAO;
import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import by.tc.task05.entity.Room;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.filter.RoomSearchDatabaseFilter;

public class SQLRoomDAO implements RoomDAO {
    private final static String SQL_BUNDLE = "by.tc.task05.bundle.sql";

    private final static String GET_MANY ="rooms.getMany";
    private final static String SQL_GET_MANY;

    //columns
    private final static String C_ID = "rooms.id";
    private static final String C_NAME = "rooms.name";
    private static final String C_ADDRESS = "hotels.cached_address";
    private static final String C_RATING = "`rooms.rating`";
    private static final String C_ICON = "rooms.icon";


    static {
        ResourceBundle sb = ResourceBundle.getBundle(SQL_BUNDLE);
        SQL_GET_MANY = sb.getString(GET_MANY);
    }

    private void setParametersForGetManyStatement(
            PreparedStatement preparedStatement,
            RoomSearchDatabaseFilter filter) throws SQLException {
        Date outDate = Date.valueOf(filter.getCheckOutDate());
        preparedStatement.setDate(1, outDate);
        preparedStatement.setDate(2, outDate);
        preparedStatement.setDate(3, Date.valueOf(filter.getCheckInDate()));
        preparedStatement.setDate(4, outDate);
        preparedStatement.setObject(5, filter.getLatitudeLowBound(),
                Types.DECIMAL, 6);
        preparedStatement.setObject(6, filter.getLatitudeHighBound(),
                Types.DECIMAL, 6);
        preparedStatement.setObject(7, filter.getLongtitudeLowBound(),
                Types.DECIMAL, 6);
        preparedStatement.setObject(8, filter.getLongtitudeHighBound(),
                Types.DECIMAL, 6);
        preparedStatement.setObject(9, filter.getCostLowBound(), Types.DECIMAL);
        preparedStatement.setObject(10, filter.getCostHighBound(),
                Types.DECIMAL);
        preparedStatement.setObject(11, filter.getNumberOfBeds(),
                Types.TINYINT);
        preparedStatement.setObject(12, filter.getRatingLowBound(),
                Types.DECIMAL);
        preparedStatement.setObject(13, filter.getRatingHighBound(),
                Types.DECIMAL);
        preparedStatement.setInt(14, filter.getSkipAmount());
        preparedStatement.setInt(15, filter.getTakeAmount());
    }

    @Override
    public List<RoomShortView> getMany(RoomSearchDatabaseFilter filter)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<RoomShortView> rooms = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_MANY);
            setParametersForGetManyStatement(preparedStatement, filter);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RoomShortView room = new RoomShortView();
                room.setId(resultSet.getInt(C_ID));
                room.setName(resultSet.getString(C_NAME));
                room.setAddress(resultSet.getString(C_ADDRESS));
                room.setRating(resultSet.getDouble(C_RATING));
                room.setIcon(resultSet.getString(C_ICON));
                rooms.add(room);
            }
        } catch (SQLException  e) {
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
