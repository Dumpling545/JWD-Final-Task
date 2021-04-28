package by.tc.task05.service.validator;

import by.tc.task05.entity.UserRegistrationForm;
import by.tc.task05.service.exception.ServiceException;

/**
 * Interface that contains methods validating input associated with user's
 * actions
 */
public interface UserValidator {
	void validateName(String firstName, String lastName)
			throws ServiceException;

	void validateEmail(String email) throws ServiceException;

	void validatePassword(String rawPassword) throws ServiceException;

	void validateRegistrationForm(UserRegistrationForm form)
			throws ServiceException;
}
