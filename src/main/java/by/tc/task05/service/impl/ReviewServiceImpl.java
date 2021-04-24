package by.tc.task05.service.impl;

import by.tc.task05.dao.DAOProvider;
import by.tc.task05.dao.ReviewDAO;
import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.entity.ExtendedReview;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.Review;
import by.tc.task05.entity.RoomRatingInformation;
import by.tc.task05.service.ReviewService;
import by.tc.task05.service.exception.CannotCreateReviewException;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.service.validator.PageValidator;
import by.tc.task05.service.validator.ReviewValidator;
import by.tc.task05.service.validator.ValidatorProvider;
import by.tc.task05.utils.ListPart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {
	@Override
	public void add(int userId, Review review) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		ReviewDAO reviewDAO = daoProvider.getReviewDAO();
		ReviewValidator reviewValidator = ValidatorProvider.getInstance()
				.getReviewValidator();
		reviewValidator.validateReview(review);
		review.setDate(LocalDate.now());
		try {
			if (reviewDAO.canCreateReview(userId, review.getReservationId())) {
				reviewDAO.add(review);
			} else {
				throw new CannotCreateReviewException();
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ListPart<ExtendedReview> getByRoom(int roomId,
	                                          PageInformation pageInformation)
			throws ServiceException
	{
		PageValidator pageValidator =
				ValidatorProvider.getInstance().getPageValidator();
		pageValidator.validatePage(pageInformation);
		DAOProvider provider = DAOProvider.getInstance();
		ReviewDAO reviewDAO = provider.getReviewDAO();
		List<ExtendedReview> reviews = new ArrayList<>();
		try {
			int skip = (pageInformation.getPage() - 1) *
					pageInformation.getPageSize();
			int take = pageInformation.getPageSize();
			reviews.addAll(reviewDAO.getByRoom(roomId, skip, take + 1));
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		boolean last = (reviews.size() <= pageInformation.getPageSize());
		if (!last) {
			reviews.remove(reviews.size() - 1);
		}
		return new ListPart<ExtendedReview>(reviews, last);
	}

	@Override
	public RoomRatingInformation getRoomRatingInformation(int roomId)
			throws ServiceException
	{
		DAOProvider provider = DAOProvider.getInstance();
		RoomRatingInformation roomRatingInformation = null;
		ReviewDAO reviewDAO = provider.getReviewDAO();
		try {
			roomRatingInformation = reviewDAO.getRoomRatingInformation(roomId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return roomRatingInformation;
	}

	@Override
	public boolean canCreateReview(int userId, int reservationId)
			throws ServiceException
	{
		DAOProvider provider = DAOProvider.getInstance();
		ReviewDAO reviewDAO = provider.getReviewDAO();
		boolean result = false;
		try {
			result = reviewDAO.canCreateReview(userId, reservationId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}
}
