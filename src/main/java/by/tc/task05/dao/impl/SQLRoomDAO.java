package by.tc.task05.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.RoomDAO;
import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import by.tc.task05.entity.Room;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.filter.RoomSearchDatabaseFilter;

public class SQLRoomDAO implements RoomDAO {
    private final String SKIP_AND_GET_MANY =
            "SELECT (id, hotel_id, name, number_of_beds, description, cost, icon) "
                    + " FROM rooms LIMIT ? OFFSET ?";
    private final String GET_MANY =
            "SELECT rooms.id, rooms.name, hotels.cached_address, rooms.rating, rooms.cost, rooms.icon "
                    + "FROM (rooms LEFT JOIN hotels ON rooms.hotelId=hotels.id) "
                    + "WHERE NOT EXISTS(SELECT id FROM reservations WHERE reservations.roomId=rooms.id "
                    + "AND ((?/*1*/ > reservations.check_in_date AND ?/*2*/ <= reservations.check_out_date) "
                    + "OR (reservations.check_out_date > ?/*3*/ AND reservations.check_out_date <= ?/*4*/))) "
                    + "AND (hotels.latitude_address BETWEEN ?/*5*/ AND ?/*6*/) AND "
                    + "(hotels.longtitude_address BETWEEN ?/*7*/ AND ?/*8*/) AND "
                    + "(rooms.cost BETWEEN ?/*9*/ AND ?/*10*/) AND "
                    + "(rooms.rating BETWEEN ?/*11*/ AND ?/*12*/) "
                    + "AND rooms.number_of_beds >= ?/*13*/ LIMIT ?/*14*/, ?/*15*/";

    private void setParametersForGetManyStatement(
            PreparedStatement preparedStatement,
            RoomSearchDatabaseFilter filter) throws SQLException {
        Date outDate = Date.valueOf(filter.getCheckOutDate());
        preparedStatement.setDate(1, outDate);
        preparedStatement.setDate(2, outDate);
        preparedStatement.setDate(3,
                Date.valueOf(filter.getCheckInDate()));
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
        preparedStatement.setObject(11, filter.getRatingLowBound(),
                Types.DECIMAL);
        preparedStatement.setObject(12, filter.getRatingHighBound(),
                Types.DECIMAL);
        preparedStatement.setObject(13, filter.getNumberOfBeds(),
                Types.TINYINT);
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
            preparedStatement = connection.prepareStatement(GET_MANY);
            setParametersForGetManyStatement(preparedStatement, filter);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RoomShortView room = new RoomShortView();
                room.setId(resultSet.getInt("rooms.id"));
                room.setName(resultSet.getString("rooms.name"));
                room.setAddress(resultSet.getString("hotels.cached_address"));
                room.setRating(resultSet.getDouble("rooms.rating"));
                room.setIcon(resultSet.getString("rooms.icon"));
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

    @Override
    public List<Room> skipAndGetMany(int skip, int number) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Room> rooms = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(SKIP_AND_GET_MANY);
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
