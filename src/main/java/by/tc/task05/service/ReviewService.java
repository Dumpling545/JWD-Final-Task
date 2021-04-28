package by.tc.task05.service;

import by.tc.task05.entity.ExtendedReview;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.Review;
import by.tc.task05.entity.RoomRatingInformation;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;

/**
 * Interface of service responsible for actions with reviews
 */
public interface ReviewService {
	/**
	 * Adds review
	 *
	 * @param userId
	 * 		review's author id
	 * @param review
	 * 		review to be added
	 * @throws ServiceException
	 */
	void add(int userId, Review review) throws ServiceException;

	/**
	 * Gets list of reviews of particular room
	 *
	 * @param roomId
	 * 		room's id
	 * @param pageInformation
	 * 		information about page
	 * @return list of reviews
	 * @throws ServiceException
	 */
	ListPart<ExtendedReview> getByRoom(int roomId,
	                                   PageInformation pageInformation)
			throws ServiceException;

	/**
	 * Get review information (number of reviews and average rating) about room
	 *
	 * @param roomId
	 * 		room's id
	 * @return review information
	 * @throws ServiceException
	 */
	RoomRatingInformation getRoomRatingInformation(int roomId)
			throws ServiceException;

	/**
	 * Determines whether provided user can create review about the reservation,
	 * or not
	 *
	 * @param userId
	 * 		potential review author's id
	 * @param reservationId
	 * 		id of reservation
	 * @return true if user can create review, false otherwise
	 * @throws ServiceException
	 */
	boolean canCreateReview(int userId, int reservationId)
			throws ServiceException;
}
