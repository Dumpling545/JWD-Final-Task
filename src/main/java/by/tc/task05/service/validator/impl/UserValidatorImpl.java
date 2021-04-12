package by.tc.task05.service.validator.impl;

import by.tc.task05.entity.UserRegistrationForm;
import by.tc.task05.service.exception.CredentialValidationException;
import by.tc.task05.service.exception.EmptyNameException;
import by.tc.task05.service.exception.InvalidImageTypeException;
import by.tc.task05.service.exception.NoImageException;
import by.tc.task05.service.validator.UserValidator;
import jakarta.servlet.http.Part;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserValidatorImpl implements UserValidator {
    private final static String MISC_BUNDLE = "by.tc.task05.bundle.misc";
    private final static String IMAGE_TYPES_KEY = "imageTypes";
    private final static List<String> IMAGE_TYPES;
    private final static String EMAIL_REGEX_KEY = "emailRegex";
    private final static String EMAIL_REGEX;
    private final static String PASSWORD_REGEX_KEY = "passwordRegex";
    private final static String PASSWORD_REGEX;
    private final static String INVALID_EMAIL_MSG_KEY = "invalidEmailMsg";
    private final static String INVALID_EMAIL_MSG;
    private final static String INVALID_PASSWORD_MSG_KEY = "invalidPasswordMsg";
    private final static String INVALID_PASSWORD_MSG;

    static {
        ResourceBundle rb = ResourceBundle.getBundle(MISC_BUNDLE);
        IMAGE_TYPES = Arrays.asList(rb.getString(IMAGE_TYPES_KEY).split(","));
        EMAIL_REGEX = rb.getString(EMAIL_REGEX_KEY);
        PASSWORD_REGEX = rb.getString(PASSWORD_REGEX_KEY);
        INVALID_EMAIL_MSG = rb.getString(INVALID_EMAIL_MSG_KEY);
        INVALID_PASSWORD_MSG = rb.getString(INVALID_PASSWORD_MSG_KEY);
    }

    @Override
    public void validateEmail(String email)
            throws CredentialValidationException {
        if (!email.matches(EMAIL_REGEX)) {
            throw new CredentialValidationException(INVALID_EMAIL_MSG);
        }
    }

    @Override
    public void validatePassword(String rawPassword)
            throws CredentialValidationException {
        if (!rawPassword.matches(PASSWORD_REGEX)) {
            throw new CredentialValidationException(INVALID_PASSWORD_MSG);
        }
    }

    @Override
    public void validateRegistrationForm(UserRegistrationForm from)
            throws CredentialValidationException, EmptyNameException {
        validateEmail(from.getEmail());
        validatePassword(from.getPassword());
        validateName(from.getFirstName(), from.getLastName());
    }

    @Override
    public void validateName(String firstName, String lastName)
            throws EmptyNameException {
        if (firstName.isBlank() || lastName.isBlank()) {
            throw new EmptyNameException();
        }
    }
}
