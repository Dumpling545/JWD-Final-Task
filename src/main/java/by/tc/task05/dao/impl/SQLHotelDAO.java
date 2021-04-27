package by.tc.task05.dao.impl;

import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.dao.HotelDAO;
import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import by.tc.task05.dao.exception.RoomOrHotelWithActiveReservationsDeletionDAOException;
import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.HotelForm;
import by.tc.task05.entity.User;
import by.tc.task05.utils.DatabaseHelper;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SQLHotelDAO implements HotelDAO {
	private final static String SQL_BUNDLE = "by.tc.task05.bundle.sql";

	private final static String IS_ADMIN = "hotels.isAdmin";
	private final static String SQL_IS_ADMIN;

	private final static String GET_BY_ID = "hotels.getById";
	private final static String SQL_GET_BY_ID;

	private final static String GET_ADMINISTERED_BY =
			"hotels.getAdministeredBy";
	private final static String SQL_GET_ADMINISTERED_BY;

	private final static String ADD = "hotels.add";
	private final static String SQL_ADD;

	private final static String CHANGE = "hotels.change";
	private final static String SQL_CHANGE;

	private final static String REMOVE = "hotels.remove";
	private final static String SQL_REMOVE;

	private final static String ADD_ADMIN = "hotels.addAdmin";
	private final static String SQL_ADD_ADMIN;

	private final static String REMOVE_ADMIN = "hotels.removeAdmin";
	private final static String SQL_REMOVE_ADMIN;

	private final static String GET_ADMINS_BY_HOTEL = "hotels.getAdminsByHotel";
	private final static String SQL_GET_ADMINS_BY_HOTEL;

	private final static String SET_ICON = "hotels.setIcon";
	private final static String SQL_SET_ICON;

	private final static String C_ID = "hotels.id";
	private final static String C_NAME = "hotels.name";
	private final static String C_CACHED_ADDRESS = "hotels.cached_address";
	private static final String C_LONGTITUDE = "hotels.longtitude_address";
	private static final String C_LATITUDE = "hotels.latitude_address";
	private static final String C_BANK_ACCOUNT = "hotels.bank_account";
	private static final String C_ICON = "hotels.icon";

	private static final String RESERVATIONS_FOREIGN_KEY =
			"fk_reservations_rooms1";
	private final static String RELATIVE_HOTEL_IMAGE_PATH = "hotels/icons/";


	static {
		ResourceBundle rb = ResourceBundle.getBundle(SQL_BUNDLE);
		SQL_IS_ADMIN = rb.getString(IS_ADMIN);
		SQL_GET_BY_ID = rb.getString(GET_BY_ID);
		SQL_ADD = rb.getString(ADD);
		SQL_CHANGE = rb.getString(CHANGE);
		SQL_REMOVE = rb.getString(REMOVE);
		SQL_GET_ADMINISTERED_BY = rb.getString(GET_ADMINISTERED_BY);
		SQL_ADD_ADMIN = rb.getString(ADD_ADMIN);
		SQL_GET_ADMINS_BY_HOTEL = rb.getString(GET_ADMINS_BY_HOTEL);
		SQL_REMOVE_ADMIN = rb.getString(REMOVE_ADMIN);
		SQL_SET_ICON = rb.getString(SET_ICON);
	}

	@Override
	public boolean isHotelAdministrator(int userId, int hotelId)
			throws DAOException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean result = false;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_IS_ADMIN);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, hotelId);
			resultSet = preparedStatement.executeQuery();
			result = resultSet.next();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
		return result;
	}

	@Override
	public List<Hotel> getAdministeredBy(int userId, int skip, int take)
			throws DAOException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Hotel> hotels = new ArrayList<>();
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement =
					connection.prepareStatement(SQL_GET_ADMINISTERED_BY);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, skip);
			preparedStatement.setInt(3, take);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Hotel hotel = new Hotel();
				hotel.setId(resultSet.getInt(C_ID));
				hotel.setName(resultSet.getString(C_NAME));
				hotel.setCachedAddress(resultSet.getString(C_CACHED_ADDRESS));
				hotel.setLongtitudeAddress(resultSet.getDouble(C_LONGTITUDE));
				hotel.setLatitudeAddress(resultSet.getDouble(C_LATITUDE));
				hotel.setBankAccount(resultSet.getString(C_BANK_ACCOUNT));
				hotel.setIcon(resultSet.getString(C_ICON));
				hotels.add(hotel);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
		return hotels;
	}

	@Override
	public Optional<Hotel> getById(int hotelId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Optional<Hotel> hotel = Optional.empty();
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_BY_ID);
			preparedStatement.setInt(1, hotelId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Hotel h = new Hotel();
				h.setId(resultSet.getInt(C_ID));
				h.setName(resultSet.getString(C_NAME));
				h.setCachedAddress(resultSet.getString(C_CACHED_ADDRESS));
				h.setLongtitudeAddress(resultSet.getDouble(C_LONGTITUDE));
				h.setLatitudeAddress(resultSet.getDouble(C_LATITUDE));
				h.setBankAccount(resultSet.getString(C_BANK_ACCOUNT));
				h.setIcon(resultSet.getString(C_ICON));
				hotel = Optional.of(h);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
		return hotel;
	}

	@Override
	public void addWithAdministrator(int userId, HotelForm hotelForm)
			throws DAOException
	{
		Connection connection = null;
		PreparedStatement addHotelStatement = null;
		PreparedStatement addAdminStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			addHotelStatement = connection
					.prepareStatement(SQL_ADD, Statement.RETURN_GENERATED_KEYS);
			addHotelStatement.setString(1, hotelForm.getName());
			addHotelStatement.setString(2, hotelForm.getCachedAddress());
			addHotelStatement.setDouble(3, hotelForm.getLongtitudeAddress());
			addHotelStatement.setDouble(4, hotelForm.getLatitudeAddress());
			addHotelStatement.setString(5, hotelForm.getBankAccount());
			addHotelStatement.executeUpdate();
			rs = addHotelStatement.getGeneratedKeys();
			int id = -1;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			addAdminStatement = connection.prepareStatement(SQL_ADD_ADMIN);
			addAdminStatement.setInt(1, userId);
			addAdminStatement.setInt(2, id);
			addAdminStatement.executeUpdate();
			connection.commit();
		} catch (SQLException | ConnectionPoolException e) {
			try {
				connection.rollback();
			} catch (SQLException innerE) {
				throw new DAOException(innerE);
			}
			throw new DAOException(e);
		} finally {
			DatabaseHelper.closeResource(rs);
			DatabaseHelper.closeResource(addHotelStatement);
			DatabaseHelper.closeResource(addAdminStatement);
			DatabaseHelper.closeResource(connection);
		}
	}

	@Override
	public void change(HotelForm hotelForm) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_CHANGE);
			preparedStatement.setString(1, hotelForm.getName());
			preparedStatement.setString(2, hotelForm.getCachedAddress());
			preparedStatement.setDouble(3, hotelForm.getLongtitudeAddress());
			preparedStatement.setDouble(4, hotelForm.getLatitudeAddress());
			preparedStatement.setString(5, hotelForm.getBankAccount());
			preparedStatement.setInt(6, hotelForm.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper.closeResources(preparedStatement, connection);
		}
	}

	@Override
	public void setIcon(int hotelId, Part icon) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_SET_ICON);
			int index = icon.getSubmittedFileName().lastIndexOf('.');
			String ext = icon.getSubmittedFileName().substring(index);
			StringBuilder pathBuilder =
					new StringBuilder(RELATIVE_HOTEL_IMAGE_PATH);
			pathBuilder.append(hotelId).append(ext);
			String path = pathBuilder.toString();
			icon.write(path);
			preparedStatement.setString(1, path);
			preparedStatement.setInt(2, hotelId);
			preparedStatement.executeUpdate();
		} catch (IOException | SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper.closeResources(preparedStatement, connection);
		}
	}

	@Override
	public void remove(int hotelId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_REMOVE);
			preparedStatement.setInt(1, hotelId);
			preparedStatement.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			if (e.getMessage().contains(RESERVATIONS_FOREIGN_KEY)) {
				throw new RoomOrHotelWithActiveReservationsDeletionDAOException(
						e);
			} else {
				throw new DAOException(e);
			}
		} finally {
			DatabaseHelper.closeResources(preparedStatement, connection);
		}
	}

	@Override
	public void addAdministrator(int userId, int hotelId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_ADMIN);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, hotelId);
			preparedStatement.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper.closeResources(preparedStatement, connection);
		}
	}

	@Override
	public void removeAdministrator(int userId, int hotelId)
			throws DAOException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_REMOVE_ADMIN);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, hotelId);
			preparedStatement.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper.closeResources(preparedStatement, connection);
		}
	}

	@Override
	public List<User> getAdministratorsByHotel(int hotelId, int skip, int take)
			throws DAOException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> admins = new ArrayList<>();
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement =
					connection.prepareStatement(SQL_GET_ADMINS_BY_HOTEL);
			preparedStatement.setInt(1, hotelId);
			preparedStatement.setInt(2, skip);
			preparedStatement.setInt(3, take);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setFirstName(resultSet.getString(3));
				user.setLastName(resultSet.getString(4));
				user.setPasswordHash(resultSet.getString(5));
				user.setAvatar(resultSet.getString(6));
				admins.add(user);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper.closeResources(resultSet, preparedStatement, connection);
		}
		return admins;
	}
}
