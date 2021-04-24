package by.tc.task05.dao;

import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.entity.ExtendedReview;
import by.tc.task05.entity.Review;
import by.tc.task05.entity.RoomRatingInformation;

import java.util.List;

public interface ReviewDAO {
	void add(Review review) throws DAOException;

	List<ExtendedReview> getByRoom(int roomId, int skip, int take)
			throws DAOException;

	RoomRatingInformation getRoomRatingInformation(int roomId)
			throws DAOException;

	boolean canCreateReview(int userId, int reservationId) throws DAOException;
}
