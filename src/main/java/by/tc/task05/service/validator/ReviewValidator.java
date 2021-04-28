package by.tc.task05.service.validator;

import by.tc.task05.entity.Review;
import by.tc.task05.entity.Room;
import by.tc.task05.service.exception.ServiceException;

/**
 * Interface that contains methods validating input associated with review's
 * actions
 */
public interface ReviewValidator {
	void validateReview(Review review)
			throws ServiceException;
}
