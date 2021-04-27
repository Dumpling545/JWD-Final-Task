package by.tc.task05.dao.impl;

import by.tc.task05.dao.ReviewDAO;
import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.dao.exception.OccupiedDateRangeDAOException;
import by.tc.task05.entity.*;
import by.tc.task05.utils.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLReviewDAO implements ReviewDAO {
	private final static String SQL_BUNDLE = "by.tc.task05.bundle.sql";

	private final static String ADD = "reviews.add";
	private final static String SQL_ADD;

	private final static String GET_BY_ROOM = "reviews.getByRoom";
	private final static String SQL_GET_BY_ROOM;

	private final static String GET_RATING_INFO_BY_ROOM =
			"reviews.getRatingInfoByRoom";
	private final static String SQL_GET_RATING_INFO_BY_ROOM;

	private final static String GET_CANNOT_CREATE_REVIEW =
			"reviews.cannotCreateReview";
	private final static String SQL_GET_CANNOT_CREATE_REVIEW;


	private static final String C_RESERVATION_ID = "reviews.reservation_id";
	private static final String C_EMAIL = "users.email";
	private static final String C_AVATAR = "users.avatar";
	private static final String C_TITLE = "reviews.title";
	private static final String C_TEXT = "reviews.text";
	private static final String C_RATING = "reviews.rating";
	private static final String C_DATE = "reviews.date";
	private static final String C_AVERAGE = "reviews.average";
	private static final String C_NUMBER_OF_REVIEWS =
			"reviews.number_of_reviews";

	static {
		ResourceBundle sb = ResourceBundle.getBundle(SQL_BUNDLE);
		SQL_ADD = sb.getString(ADD);
		SQL_GET_BY_ROOM = sb.getString(GET_BY_ROOM);
		SQL_GET_RATING_INFO_BY_ROOM = sb.getString(GET_RATING_INFO_BY_ROOM);
		SQL_GET_CANNOT_CREATE_REVIEW = sb.getString(GET_CANNOT_CREATE_REVIEW);
	}

	@Override
	public void add(Review review) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD);
			preparedStatement.setInt(1, review.getReservationId());
			preparedStatement.setString(2, review.getTitle());
			preparedStatement
					.setString(3, review.getText());
			preparedStatement
					.setInt(4, review.getRating());
			preparedStatement.setDate(5, Date.valueOf(review.getDate()));
			preparedStatement.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper
					.closeResources(preparedStatement, connection);
		}
	}

	@Override
	public List<ExtendedReview> getByRoom(int roomId, int skip, int take)
			throws DAOException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ExtendedReview> reviews = new ArrayList<>();
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_BY_ROOM);
			preparedStatement.setInt(1, roomId);
			preparedStatement.setInt(2, skip);
			preparedStatement.setInt(3, take);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ExtendedReview r = new ExtendedReview();
				r.setReservationId(resultSet.getInt(C_RESERVATION_ID));
				r.setUserEmail(resultSet.getString(C_EMAIL));
				r.setUserAvatar(resultSet.getString(C_AVATAR));
				r.setTitle(resultSet.getString(C_TITLE));
				r.setText(resultSet.getString(C_TEXT));
				r.setRating(resultSet.getInt(C_RATING));
				r.setDate(resultSet.getDate(C_DATE).toLocalDate());
				reviews.add(r);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
		return reviews;
	}

	@Override
	public RoomRatingInformation getRoomRatingInformation(int roomId)
			throws DAOException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		RoomRatingInformation roomRatingInformation = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement =
					connection.prepareStatement(SQL_GET_RATING_INFO_BY_ROOM);
			preparedStatement.setInt(1, roomId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				roomRatingInformation = new RoomRatingInformation(
						resultSet.getDouble(C_AVERAGE),
						resultSet.getInt(C_NUMBER_OF_REVIEWS));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
		return roomRatingInformation;
	}

	@Override
	public boolean canCreateReview(int userId, int reservationId)
			throws DAOException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		RoomRatingInformation roomRatingInformation = null;
		boolean canCreate = false;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement =
					connection.prepareStatement(SQL_GET_CANNOT_CREATE_REVIEW);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, reservationId);
			resultSet = preparedStatement.executeQuery();
			canCreate = !resultSet.next();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
		return canCreate;
	}
}
