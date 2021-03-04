package by.tc.task05.dao;

import by.tc.task05.entity.User;

public interface UserDAO {
    User getByEmail(String email)
            throws DAOException;
    User getById(int id) throws DAOException;
    void add(User user) throws DAOException;
}
