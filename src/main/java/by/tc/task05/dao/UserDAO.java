package by.tc.task05.dao;

import java.util.Optional;

import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.entity.User;
import by.tc.task05.entity.UserInfo;
import jakarta.servlet.http.Part;

public interface UserDAO {
    Optional<User> getByEmail(String email) throws DAOException;

    Optional<User> getById(int id) throws DAOException;

    void add(User user) throws DAOException;

    void changeUserInfo(UserInfo userInfo) throws DAOException;

    void changePassword(int id, String password) throws DAOException;

    void changeEmail(int id, String email) throws DAOException;

    void setAvatar(int id, Part imageFile) throws DAOException;

    void remove(int id) throws DAOException;
}
