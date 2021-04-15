package by.tc.task05.dao.impl;

import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.dao.ReservationDAO;
import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import by.tc.task05.dao.exception.OccupiedDateRangeDAOException;
import by.tc.task05.entity.ExtendedReservation;
import by.tc.task05.entity.Reservation;
import by.tc.task05.entity.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SQLReservationDAO implements ReservationDAO {
    private final static String SQL_BUNDLE = "by.tc.task05.bundle.sql";

    private final static String GET = "reservations.get";
    private final static String SQL_GET;

    private final static String GET_AS_ARCHIVED = "reservations.getAsArchived";
    private final static String SQL_GET_AS_ARCHIVED;


    private final static String ADD = "reservations.add";
    private final static String SQL_ADD;

    private final static String ACCEPT = "reservations.accept";
    private final static String SQL_ACCEPT;

    private final static String REMOVE = "reservations.remove";
    private final static String SQL_REMOVE;

    private final static String GET_BY_USER = "reservations.getByUser";
    private final static String SQL_GET_BY_USER;

    private final static String GET_BY_HOTEL = "reservations.getByHotel";
    private final static String SQL_GET_BY_HOTEL;

    private final static String GET_BY_ROOM = "reservations.getByRoom";
    private final static String SQL_GET_BY_ROOM;

    private final static String IS_DATE_OCCUPIED =
            "reservations.isDateOccupied";
    private final static String SQL_IS_DATE_OCCUPIED;

    private final static String ARCHIVED_ADD = "archivedReservations.add";
    private final static String SQL_ARCHIVED_ADD;

    private final static String ARCHIVED_GET_BY_USER =
            "archivedReservations.getByUser";
    private final static String SQL_ARCHIVED_GET_BY_USER;

    private final static String ARCHIVED_GET_BY_HOTEL =
            "archivedReservations.getByHotel";
    private final static String SQL_ARCHIVED_GET_BY_HOTEL;

    private final static String ARCHIVED_GET_BY_ROOM =
            "archivedReservations.getByRoom";
    private final static String SQL_ARCHIVED_GET_BY_ROOM;

    private final static String DATE_RANGE_OCCUPIED_SQL_MESSAGE =
            "Date range has been already occupied by previous reservations";

    private final static String C_ID = "reservations.id";
    private final static String C_USER_ID = "reservations.user_id";
    private final static String C_ROOM_ID = "reservations.room_id";
    private final static String C_CHECK_IN_DATE = "reservations.check_in_date";
    private final static String C_CHECK_OUT_DATE =
            "reservations.check_out_date";
    private final static String C_PAYMENT_TOKEN = "reservations.payment_token";
    private final static String C_STATUS = "reservations.status";
    private final static String C_PAYMENT_AMOUNT =
            "reservations.payment_amount";

    private final static String C_ARCHIVED_ID = "archived_reservations.id";
    private final static String C_ARCHIVED_USER_ID =
            "archived_reservations.user_id";
    private final static String C_ARCHIVED_EMAIL =
            "archived_reservations.email";
    private final static String C_ARCHIVED_ROOM_ID =
            "archived_reservations.room_id";
    private final static String C_ARCHIVED_ROOM_NAME =
            "archived_reservations.room_name";
    private final static String C_ARCHIVED_HOTEL_ID =
            "archived_reservations.hotel_id";
    private final static String C_ARCHIVED_HOTEL_NAME =
            "archived_reservations.hotel_name";
    private final static String C_ARCHIVED_CHECK_IN_DATE =
            "archived_reservations.check_in_date";
    private final static String C_ARCHIVED_CHECK_OUT_DATE =
            "archived_reservations.check_out_date";
    private final static String C_ARCHIVED_PAYMENT_TOKEN =
            "archived_reservations.payment_token";
    private final static String C_ARCHIVED_ARCHIVATION_REASON =
            "archived_reservations.archivation_reason";
    private final static String C_ARCHIVED_PAYMENT_AMOUNT =
            "archived_reservations.payment_amount";

    private final static String USERS_C_EMAIL = "users.email";
    private final static String HOTELS_C_NAME = "hotels.name";
    private final static String HOTELS_C_ID = "hotels.id";
    private final static String ROOMS_C_NAME = "rooms.name";

    static {
        ResourceBundle sb = ResourceBundle.getBundle(SQL_BUNDLE);
        SQL_GET = sb.getString(GET);
        SQL_GET_AS_ARCHIVED = sb.getString(GET_AS_ARCHIVED);
        SQL_ADD = sb.getString(ADD);
        SQL_ACCEPT = sb.getString(ACCEPT);
        SQL_REMOVE = sb.getString(REMOVE);
        SQL_GET_BY_USER = sb.getString(GET_BY_USER);
        SQL_GET_BY_HOTEL = sb.getString(GET_BY_HOTEL);
        SQL_GET_BY_ROOM = sb.getString(GET_BY_ROOM);
        SQL_IS_DATE_OCCUPIED = sb.getString(IS_DATE_OCCUPIED);
        SQL_ARCHIVED_ADD = sb.getString(ARCHIVED_ADD);
        SQL_ARCHIVED_GET_BY_USER = sb.getString(ARCHIVED_GET_BY_USER);
        SQL_ARCHIVED_GET_BY_HOTEL = sb.getString(ARCHIVED_GET_BY_HOTEL);
        SQL_ARCHIVED_GET_BY_ROOM = sb.getString(ARCHIVED_GET_BY_ROOM);
    }

    @Override
    public void add(Reservation reservation) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_ADD);
            preparedStatement.setInt(1, reservation.getUserId());
            preparedStatement.setInt(2, reservation.getRoomId());
            preparedStatement
                    .setDate(3, Date.valueOf(reservation.getCheckInDate()));
            preparedStatement
                    .setDate(4, Date.valueOf(reservation.getCheckOutDate()));
            preparedStatement.setString(5, reservation.getPaymentToken());
            preparedStatement.setInt(6, Reservation.PROCESSING);
            preparedStatement.setDouble(7, reservation.getPaymentAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().equals(DATE_RANGE_OCCUPIED_SQL_MESSAGE)) {
                throw new OccupiedDateRangeDAOException(e);
            } else {
                throw new DAOException(e);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void acceptReservation(int reservationId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_ACCEPT);
            preparedStatement.setInt(1, reservationId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void archiveReservation(int reservationId, int archivationReason)
            throws DAOException {
        Connection connection = null;
        PreparedStatement getStatement = null;
        PreparedStatement removeStatement = null;
        PreparedStatement archiveStatement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            getStatement = connection.prepareStatement(SQL_GET_AS_ARCHIVED);
            getStatement.setInt(1, reservationId);
            rs = getStatement.executeQuery();
            ExtendedReservation archived = new ExtendedReservation();
            if (rs.next()) {
                archived.setId(reservationId);
                archived.setUserId(rs.getInt(C_USER_ID));
                archived.setUserEmail(rs.getString(USERS_C_EMAIL));
                archived.setRoomId(rs.getInt(C_ROOM_ID));
                archived.setRoomName(rs.getString(ROOMS_C_NAME));
                archived.setHotelId(rs.getInt(HOTELS_C_ID));
                archived.setHotelName(rs.getString(HOTELS_C_NAME));
                archived.setCheckInDate(
                        rs.getDate(C_CHECK_IN_DATE).toLocalDate());
                archived.setCheckOutDate(
                        rs.getDate(C_CHECK_OUT_DATE).toLocalDate());
                archived.setPaymentAmount(rs.getDouble(C_PAYMENT_AMOUNT));
                archived.setPaymentToken(rs.getString(C_PAYMENT_TOKEN));
            }
            removeStatement = connection.prepareStatement(SQL_REMOVE);
            getStatement.setInt(1, reservationId);
            getStatement.executeUpdate();
            archiveStatement = connection.prepareStatement(SQL_ARCHIVED_ADD);
            archiveStatement.setInt(1, archived.getId());
            archiveStatement.setInt(2, archived.getUserId());
            archiveStatement.setString(3, archived.getUserEmail());
            archiveStatement.setInt(4, archived.getRoomId());
            archiveStatement.setString(5, archived.getRoomName());
            archiveStatement.setInt(6, archived.getHotelId());
            archiveStatement.setString(7, archived.getHotelName());
            archiveStatement
                    .setDate(8, Date.valueOf(archived.getCheckInDate()));
            archiveStatement
                    .setDate(9, Date.valueOf(archived.getCheckOutDate()));
            archiveStatement.setString(10, archived.getPaymentToken());
            archiveStatement.setInt(11, archivationReason);
            archiveStatement.setDouble(12, archived.getPaymentAmount());
            archiveStatement.executeUpdate();
            connection.commit();
        } catch (SQLException | ConnectionPoolException e) {
            try {
                connection.rollback();
            } catch (SQLException innerE) {
                throw new DAOException(innerE);
            }
            throw new DAOException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                if (rs != null) rs.close();
                if (getStatement != null) getStatement.close();
                if (removeStatement != null) removeStatement.close();
                if (archiveStatement != null) archiveStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

    }

    @Override
    public Optional<Reservation> get(int reservationId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<Reservation> reservation = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET);
            preparedStatement.setInt(1, reservationId);
            preparedStatement.setInt(1, reservationId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Reservation r = new Reservation();
                r.setId(reservationId);
                r.setUserId(resultSet.getInt(C_USER_ID));
                r.setRoomId(resultSet.getInt(C_ROOM_ID));
                r.setCheckInDate(
                        resultSet.getDate(C_CHECK_IN_DATE).toLocalDate());
                r.setCheckOutDate(
                        resultSet.getDate(C_CHECK_OUT_DATE).toLocalDate());
                r.setPaymentToken(resultSet.getString(C_PAYMENT_TOKEN));
                r.setPaymentAmount(resultSet.getDouble(C_PAYMENT_AMOUNT));
                r.setStatus(resultSet.getInt(C_STATUS));
                reservation = Optional.of(r);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return reservation;
    }

    @Override
    public List<ExtendedReservation> getByUser(int userId, int skip, int take)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ExtendedReservation> reservations = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, skip);
            preparedStatement.setInt(3, take);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ExtendedReservation r = new ExtendedReservation();
                r.setId(resultSet.getInt(C_ID));
                r.setUserId(userId);
                r.setRoomId(resultSet.getInt(C_ROOM_ID));
                r.setCheckInDate(
                        resultSet.getDate(C_CHECK_IN_DATE).toLocalDate());
                r.setCheckOutDate(
                        resultSet.getDate(C_CHECK_OUT_DATE).toLocalDate());
                r.setPaymentToken(resultSet.getString(C_PAYMENT_TOKEN));
                r.setPaymentAmount(resultSet.getDouble(C_PAYMENT_AMOUNT));
                r.setStatus(resultSet.getInt(C_STATUS));
                r.setHotelId(resultSet.getInt(HOTELS_C_ID));
                r.setHotelName(resultSet.getString(HOTELS_C_NAME));
                r.setRoomName(resultSet.getString(ROOMS_C_NAME));
                r.setUserEmail(resultSet.getString(USERS_C_EMAIL));
                reservations.add(r);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return reservations;
    }

    @Override
    public List<ExtendedReservation> getByHotel(int hotelId, int skip, int take)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ExtendedReservation> reservations = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_HOTEL);
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setInt(2, skip);
            preparedStatement.setInt(3, take);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ExtendedReservation r = new ExtendedReservation();
                r.setId(resultSet.getInt(C_ID));
                r.setUserId(resultSet.getInt(C_USER_ID));
                r.setRoomId(resultSet.getInt(C_ROOM_ID));
                r.setRoomName(resultSet.getString(ROOMS_C_NAME));
                r.setCheckInDate(
                        resultSet.getDate(C_CHECK_IN_DATE).toLocalDate());
                r.setCheckOutDate(
                        resultSet.getDate(C_CHECK_OUT_DATE).toLocalDate());
                r.setPaymentToken(resultSet.getString(C_PAYMENT_TOKEN));
                r.setPaymentAmount(resultSet.getDouble(C_PAYMENT_AMOUNT));
                r.setStatus(resultSet.getInt(C_STATUS));
                r.setHotelId(resultSet.getInt(HOTELS_C_ID));
                r.setHotelName(resultSet.getString(HOTELS_C_NAME));
                r.setUserEmail(resultSet.getString(USERS_C_EMAIL));
                reservations.add(r);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return reservations;
    }

    @Override
    public List<ExtendedReservation> getByRoom(int roomId, int skip, int take)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ExtendedReservation> reservations = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_ROOM);
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, skip);
            preparedStatement.setInt(3, take);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ExtendedReservation r = new ExtendedReservation();
                r.setId(resultSet.getInt(C_ID));
                r.setUserId(resultSet.getInt(C_USER_ID));
                r.setRoomId(roomId);
                r.setCheckInDate(
                        resultSet.getDate(C_CHECK_IN_DATE).toLocalDate());
                r.setCheckOutDate(
                        resultSet.getDate(C_CHECK_OUT_DATE).toLocalDate());
                r.setPaymentToken(resultSet.getString(C_PAYMENT_TOKEN));
                r.setPaymentAmount(resultSet.getDouble(C_PAYMENT_AMOUNT));
                r.setStatus(resultSet.getInt(C_STATUS));
                r.setHotelId(resultSet.getInt(HOTELS_C_ID));
                r.setHotelName(resultSet.getString(HOTELS_C_NAME));
                r.setRoomName(resultSet.getString(ROOMS_C_NAME));
                r.setUserEmail(resultSet.getString(USERS_C_EMAIL));
                reservations.add(r);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return reservations;
    }

    @Override
    public List<ExtendedReservation> getArchivedByUser(int userId, int skip,
                                                       int take)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<ExtendedReservation> reservations = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, skip);
            preparedStatement.setInt(3, take);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ExtendedReservation archived = new ExtendedReservation();
                archived.setId(rs.getInt(C_ARCHIVED_ID));
                archived.setUserId(userId);
                archived.setUserEmail(rs.getString(C_ARCHIVED_EMAIL));
                archived.setRoomId(rs.getInt(C_ARCHIVED_ROOM_ID));
                archived.setRoomName(rs.getString(C_ARCHIVED_ROOM_NAME));
                archived.setHotelId(rs.getInt(C_ARCHIVED_HOTEL_ID));
                archived.setHotelName(rs.getString(C_ARCHIVED_HOTEL_NAME));
                archived.setCheckInDate(
                        rs.getDate(C_ARCHIVED_CHECK_IN_DATE).toLocalDate());
                archived.setCheckOutDate(
                        rs.getDate(C_ARCHIVED_CHECK_OUT_DATE).toLocalDate());
                archived.setPaymentAmount(
                        rs.getDouble(C_ARCHIVED_PAYMENT_AMOUNT));
                archived.setPaymentToken(
                        rs.getString(C_ARCHIVED_PAYMENT_TOKEN));
                archived.setStatus(rs.getInt(C_ARCHIVED_ARCHIVATION_REASON));
                reservations.add(archived);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return reservations;
    }

    @Override
    public List<ExtendedReservation> getArchivedByHotel(int hotelId, int skip,
                                                        int take)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<ExtendedReservation> reservations = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_HOTEL);
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setInt(2, skip);
            preparedStatement.setInt(3, take);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ExtendedReservation archived = new ExtendedReservation();
                archived.setId(rs.getInt(C_ARCHIVED_ID));
                archived.setUserId(rs.getInt(C_ARCHIVED_USER_ID));
                archived.setUserEmail(rs.getString(C_ARCHIVED_EMAIL));
                archived.setRoomId(rs.getInt(C_ARCHIVED_ROOM_ID));
                archived.setRoomName(rs.getString(C_ARCHIVED_ROOM_NAME));
                archived.setHotelId(hotelId);
                archived.setHotelName(rs.getString(C_ARCHIVED_HOTEL_NAME));
                archived.setCheckInDate(
                        rs.getDate(C_ARCHIVED_CHECK_IN_DATE).toLocalDate());
                archived.setCheckOutDate(
                        rs.getDate(C_ARCHIVED_CHECK_OUT_DATE).toLocalDate());
                archived.setPaymentAmount(
                        rs.getDouble(C_ARCHIVED_PAYMENT_AMOUNT));
                archived.setPaymentToken(
                        rs.getString(C_ARCHIVED_PAYMENT_TOKEN));
                archived.setStatus(rs.getInt(C_ARCHIVED_ARCHIVATION_REASON));
                reservations.add(archived);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return reservations;
    }

    @Override
    public List<ExtendedReservation> getArchivedByRoom(int roomId, int skip,
                                                       int take)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<ExtendedReservation> reservations = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_ROOM);
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, skip);
            preparedStatement.setInt(3, take);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ExtendedReservation archived = new ExtendedReservation();
                archived.setId(rs.getInt(C_ARCHIVED_ID));
                archived.setUserId(rs.getInt(C_ARCHIVED_USER_ID));
                archived.setUserEmail(rs.getString(C_ARCHIVED_EMAIL));
                archived.setRoomId(roomId);
                archived.setRoomName(rs.getString(C_ARCHIVED_ROOM_NAME));
                archived.setHotelId(rs.getInt(C_ARCHIVED_HOTEL_ID));
                archived.setHotelName(rs.getString(C_ARCHIVED_HOTEL_NAME));
                archived.setCheckInDate(
                        rs.getDate(C_ARCHIVED_CHECK_IN_DATE).toLocalDate());
                archived.setCheckOutDate(
                        rs.getDate(C_ARCHIVED_CHECK_OUT_DATE).toLocalDate());
                archived.setPaymentAmount(
                        rs.getDouble(C_ARCHIVED_PAYMENT_AMOUNT));
                archived.setPaymentToken(
                        rs.getString(C_ARCHIVED_PAYMENT_TOKEN));
                archived.setStatus(rs.getInt(C_ARCHIVED_ARCHIVATION_REASON));
                reservations.add(archived);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return reservations;
    }

    @Override
    public boolean areDatesOccupied(int roomId, LocalDate checkIn,
                                    LocalDate checkOut) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement =
                    connection.prepareStatement(SQL_IS_DATE_OCCUPIED);
            Date checkOutSql = Date.valueOf(checkOut);
            preparedStatement.setDate(1, checkOutSql);
            preparedStatement.setDate(2, checkOutSql);
            preparedStatement.setDate(3, Date.valueOf(checkIn));
            preparedStatement.setDate(4, checkOutSql);
            preparedStatement.setInt(5, roomId);
            resultSet = preparedStatement.executeQuery();
            result = resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return result;
    }
}
