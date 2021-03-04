package by.tc.task05.service.validator;

public interface CredentialsValidator {
    boolean validateEmail(String email);
    boolean validatePassword(String rawPassword);
}
