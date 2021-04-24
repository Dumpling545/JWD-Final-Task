package by.tc.task05.service;

import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.entity.ExtendedReview;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.Review;
import by.tc.task05.entity.RoomRatingInformation;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;

import java.util.List;

public interface ReviewService {
	void add(int userId, Review review) throws ServiceException;

	ListPart<ExtendedReview> getByRoom(int roomId, PageInformation pageInformation)
			throws ServiceException;

	RoomRatingInformation getRoomRatingInformation(int roomId)
			throws ServiceException;

	boolean canCreateReview(int userId, int reservationId) throws ServiceException;
}
