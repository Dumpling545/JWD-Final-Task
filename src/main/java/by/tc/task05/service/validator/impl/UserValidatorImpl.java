package by.tc.task05.service.validator.impl;

import by.tc.task05.entity.UserRegistrationForm;
import by.tc.task05.service.exception.CredentialValidationException;
import by.tc.task05.service.exception.EmptyNameException;
import by.tc.task05.service.validator.UserValidator;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UserValidatorImpl implements UserValidator {
    private final static String MISC_BUNDLE = "by.tc.task05.bundle.misc";
    private final static String IMAGE_TYPES_KEY = "imageTypes";
    private final static List<String> IMAGE_TYPES;
    private final static String EMAIL_REGEX_KEY = "emailRegex";
    private final static String EMAIL_REGEX;
    private final static String PASSWORD_REGEX_KEY = "passwordRegex";
    private final static String PASSWORD_REGEX;
    public final static String INVALID_EMAIL_MESSAGE = "Email does not match regular expression";
    public final static String INVALID_PASSWORD_MESSAGE = "Password does not match regular expression";

    static {
        ResourceBundle rb = ResourceBundle.getBundle(MISC_BUNDLE);
        IMAGE_TYPES = Arrays.asList(rb.getString(IMAGE_TYPES_KEY).split(","));
        EMAIL_REGEX = rb.getString(EMAIL_REGEX_KEY);
        PASSWORD_REGEX = rb.getString(PASSWORD_REGEX_KEY);
    }

    @Override
    public void validateEmail(String email)
            throws CredentialValidationException {
        if (!email.matches(EMAIL_REGEX)) {
            throw new CredentialValidationException(INVALID_EMAIL_MESSAGE);
        }
    }

    @Override
    public void validatePassword(String rawPassword)
            throws CredentialValidationException {
        if (!rawPassword.matches(PASSWORD_REGEX)) {
            throw new CredentialValidationException(INVALID_PASSWORD_MESSAGE);
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
