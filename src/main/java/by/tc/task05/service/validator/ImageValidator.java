package by.tc.task05.service.validator;

import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.http.Part;

/**
 * Interface that contains methods validating image input
 */
public interface ImageValidator {
	void validateImage(Part imageFile) throws ServiceException;
}
