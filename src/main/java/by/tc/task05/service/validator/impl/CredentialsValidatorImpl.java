package by.tc.task05.service.validator.impl;

import by.tc.task05.entity.User;
import by.tc.task05.service.exception.CredentialValidationException;
import by.tc.task05.service.exception.EmptyNameException;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.service.validator.CredentialsValidator;

public class CredentialsValidatorImpl implements CredentialsValidator {

   private final static String EMAIL_REGEX =
         "^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";
   private final static String PASSWORD_REGEX =
         "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*](?=\\S+$).{8,50}$";
   private final static String INVALID_EMAIL_MSG = "Email is invalid";
   private final static String INVALID_PASSWORD_MSG = "Password is invalid";
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
   public void validateUser(User user) throws ServiceException {
         validateEmail(user.getEmail());
         validatePassword(user.getPasswordHash());
         validateName(user.getFirstName(), user.getLastName());
   }

   @Override
   public void validateName(String firstName, String lastName) throws ServiceException {
      if(firstName.isBlank() || lastName.isBlank()){
         throw new EmptyNameException();
      }
   }
}
