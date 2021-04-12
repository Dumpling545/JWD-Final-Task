package by.tc.task05.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.DAOProvider;
import by.tc.task05.dao.UserDAO;
import by.tc.task05.entity.User;
import by.tc.task05.entity.UserInfo;
import by.tc.task05.entity.UserRegistrationForm;
import by.tc.task05.service.exception.InvalidPasswordRepeatingException;
import by.tc.task05.service.exception.InvalidUserException;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.service.UserService;
import by.tc.task05.service.exception.UnauthorizedActionException;
import by.tc.task05.service.validator.ImageValidator;
import by.tc.task05.service.validator.UserValidator;
import by.tc.task05.service.validator.ValidatorProvider;
import jakarta.servlet.http.Part;

public class UserServiceImpl implements UserService {

    private final static int BCRYPT_COST = 12;

    @Override
    public boolean isPasswordMatched(int userId, String rawPassword)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        Optional<User> result = Optional.empty();
        UserDAO userDAO = provider.getUserDAO();
        try {
            result = userDAO.getById(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (result.isEmpty()) {
            return false;
        }
        return isPasswordMatched(result.get(), rawPassword);
    }
    private boolean isPasswordMatched(User user, String rawPassword) {
        return BCrypt.verifyer().verify(rawPassword.toCharArray(),
                user.getPasswordHash()).verified;
    }

    @Override
    public Optional<User> authorization(String email, String rawPassword)
            throws ServiceException {
        UserValidator validator =
                ValidatorProvider.getInstance().getCredentialsValidator();
        validator.validateEmail(email);
        validator.validatePassword(rawPassword);
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        Optional<User> user = Optional.empty();
        try {
            user = userDAO.getByEmail(email);
            if (user.isPresent() &&
                    !isPasswordMatched(user.get(), rawPassword)) {
                user = Optional.empty();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void registration(UserRegistrationForm form)
            throws ServiceException {
        UserValidator validator =
                ValidatorProvider.getInstance().getCredentialsValidator();
        if (!form.getPassword().equals(form.getRepeatPassword())) {
            throw new InvalidPasswordRepeatingException();
        }
        validator.validateRegistrationForm(form);
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        String pswHash = BCrypt.withDefaults()
                .hashToString(BCRYPT_COST, form.getPassword().toCharArray());
        User user = new User(0, form.getEmail(), form.getFirstName(),
                form.getLastName(), pswHash, null);
        try {
            userDAO.add(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getById(int id) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        Optional<User> result = Optional.empty();
        UserDAO userDAO = provider.getUserDAO();
        try {
            result = userDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (result.isEmpty()) {
            throw new InvalidUserException();
        }
        return result.get();
    }

    @Override
    public void changeUserInfo(UserInfo userInfo) throws ServiceException {
        UserValidator validator =
                ValidatorProvider.getInstance().getCredentialsValidator();
        validator.validateName(userInfo.getFirstName(), userInfo.getLastName());
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        try {
            userDAO.changeUserInfo(userInfo);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeEmail(int id, String email, String oldPassword)
            throws ServiceException {
        UserValidator validator =
                ValidatorProvider.getInstance().getCredentialsValidator();
        validator.validateEmail(email);
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        Optional<User> user = Optional.empty();
        try {
            user = userDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (user.isPresent() && isPasswordMatched(user.get(), oldPassword)) {
            try {
                userDAO.changeEmail(id, email);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new UnauthorizedActionException();
        }
    }

    @Override
    public void changePassword(int id, String newPassword,
                               String repeatNewPassword, String oldPassword)
            throws ServiceException {
        UserValidator validator =
                ValidatorProvider.getInstance().getCredentialsValidator();
        validator.validatePassword(newPassword);
        if (!newPassword.equals(repeatNewPassword)) {
            throw new InvalidPasswordRepeatingException();
        }
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        Optional<User> user = Optional.empty();
        try {
            user = userDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        newPassword = BCrypt.withDefaults()
                .hashToString(BCRYPT_COST, newPassword.toCharArray());
        if (user.isPresent() && isPasswordMatched(user.get(), oldPassword)) {
            try {
                userDAO.changePassword(id, newPassword);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new UnauthorizedActionException();
        }
    }

    @Override
    public void remove(int id, String password) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        Optional<User> user = Optional.empty();
        try {
            user = userDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (user.isPresent() && isPasswordMatched(user.get(), password)) {
            try {
                userDAO.remove(id);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new UnauthorizedActionException();
        }
    }

    @Override
    public void setAvatar(int id, Part imageFile) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        ImageValidator validator =
                ValidatorProvider.getInstance().getImageValidator();
        validator.validateImage(imageFile);
        try {
            userDAO.setAvatar(id, imageFile);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
