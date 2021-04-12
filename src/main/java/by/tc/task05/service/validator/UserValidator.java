package by.tc.task05.service.validator;

import by.tc.task05.entity.User;
import by.tc.task05.entity.UserRegistrationForm;
import by.tc.task05.service.exception.CredentialValidationException;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.http.Part;

public interface UserValidator {
    void validateName(String firstName, String lastName)
            throws ServiceException;

    void validateEmail(String email) throws ServiceException;

    void validatePassword(String rawPassword) throws ServiceException;

    void validateRegistrationForm(UserRegistrationForm form)
            throws ServiceException;
}
