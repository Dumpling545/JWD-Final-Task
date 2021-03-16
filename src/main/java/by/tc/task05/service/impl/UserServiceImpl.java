package by.tc.task05.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.DAOProvider;
import by.tc.task05.dao.UserDAO;
import by.tc.task05.entity.User;
import by.tc.task05.service.ServiceException;
import by.tc.task05.service.UserService;
import by.tc.task05.service.validator.CredentialsValidator;
import by.tc.task05.service.validator.ValidatorProvider;

public class UserServiceImpl implements UserService {

    @Override
    public User authorization(String email, String rawPassword)
            throws ServiceException {
        CredentialsValidator validator = ValidatorProvider.getInstance().getCredentialsValidator();
        if (!validator.validateEmail(email)
                || !validator.validatePassword(rawPassword)) {
            throw new ServiceException("Invalid login or password");
        }

        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();

        User user = null;
        try {
            user = userDAO.getByEmail(email);
            if (user == null
                    || BCrypt.verifyer().verify(rawPassword.toCharArray(),
                            user.getPasswordHash()).verified) {
                user = null;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void registration(User user) throws ServiceException {
        CredentialsValidator validator = ValidatorProvider.getInstance().getCredentialsValidator();
        if (!validator.validateEmail(user.getEmail())
                || !validator.validatePassword(user.getPasswordHash())) {
            throw new ServiceException("Invalid login or password");
        }
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDAO();
        user.setPasswordHash(BCrypt.withDefaults().hashToString(12,
                user.getPasswordHash().toCharArray()));
        try {
            userDAO.add(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
