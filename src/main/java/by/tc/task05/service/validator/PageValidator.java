package by.tc.task05.service.validator;

import by.tc.task05.entity.PageInformation;
import by.tc.task05.service.exception.ServiceException;

/**
 * Interface that contains methods validating input associated with number and
 * size of page
 */
public interface PageValidator {
	void validatePage(PageInformation pageInfo) throws ServiceException;
}
