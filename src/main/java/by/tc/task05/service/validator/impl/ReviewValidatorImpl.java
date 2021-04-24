package by.tc.task05.service.validator.impl;

import by.tc.task05.entity.Review;
import by.tc.task05.service.exception.EmptyInanimateNameException;
import by.tc.task05.service.exception.EmptyTitleException;
import by.tc.task05.service.exception.InvalidRatingException;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.service.validator.ReviewValidator;

import java.time.LocalDate;

public class ReviewValidatorImpl implements ReviewValidator {
	private final static int MIN_RATING = 1;
	private final static int MAX_RATING = 10;
	@Override
	public void validateReview(Review review) throws ServiceException {
		if (review.getTitle() == null ||
				review.getTitle().isBlank())
		{
			throw new EmptyTitleException();
		}
		if(review.getRating() < MIN_RATING || review.getRating() > MAX_RATING){
			throw new InvalidRatingException();
		}
	}
}
