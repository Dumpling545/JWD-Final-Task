package by.tc.task05.service.validator;

import by.tc.task05.entity.User;
import by.tc.task05.service.exception.CredentialValidationException;
import by.tc.task05.service.exception.ServiceException;

public interface CredentialsValidator {
    void validateUser(User user) throws ServiceException;
    void validateName(String firstName, String lastName) throws ServiceException;
    void validateEmail(String email) throws CredentialValidationException;
    void validatePassword(String rawPassword) throws CredentialValidationException;
}
