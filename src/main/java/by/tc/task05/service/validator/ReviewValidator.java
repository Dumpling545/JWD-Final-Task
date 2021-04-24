package by.tc.task05.service.validator;

import by.tc.task05.entity.Review;
import by.tc.task05.entity.Room;
import by.tc.task05.service.exception.ServiceException;

public interface ReviewValidator {
	public void validateReview(Review review)
			throws ServiceException;
}
