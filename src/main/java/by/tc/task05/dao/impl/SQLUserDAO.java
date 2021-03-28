package by.tc.task05.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.UserDAO;
import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import by.tc.task05.entity.User;
import by.tc.task05.entity.UserInfo;

public class SQLUserDAO implements UserDAO {
    private final static String SQL_BUNDLE = "by.tc.task05.bundle.sql";

    private final static String GET_BY_ID ="users.getById";
    private final static String SQL_GET_BY_ID;

    private final static String GET_BY_EMAIL ="users.getByEmail";
    private final static String SQL_GET_BY_EMAIL;

    private final static String ADD = "users.add";
    private final static String SQL_ADD;

    private final static String CHANGE_INFO = "users.changeInfo";
    private final static String SQL_CHANGE_INFO;

    private final static String CHANGE_EMAIL = "users.changeEmail";
    private final static String SQL_CHANGE_EMAIL;

    private final static String CHANGE_PASSWORD = "users.changePassword";
    private final static String SQL_CHANGE_PASSWORD;

    private final static String REMOVE = "users.remove";
    private final static String SQL_REMOVE;


    static {
        ResourceBundle rb = ResourceBundle.getBundle(SQL_BUNDLE);
        SQL_GET_BY_ID  = rb.getString(GET_BY_ID);
        SQL_GET_BY_EMAIL = rb.getString(GET_BY_EMAIL);
        SQL_ADD = rb.getString(ADD);
        SQL_CHANGE_INFO = rb.getString(CHANGE_INFO);
        SQL_REMOVE = rb.getString(REMOVE);
        SQL_CHANGE_EMAIL = rb.getString(CHANGE_EMAIL);
        SQL_CHANGE_PASSWORD = rb.getString(CHANGE_PASSWORD);
    }
    @Override
    public Optional<User> getByEmail(String email) throws DAOException {
        return get(c -> {
            PreparedStatement pc = c.prepareStatement(SQL_GET_BY_EMAIL);
            pc.setString(1, email);
            return pc;
        });
    }

    @Override
    public Optional<User> getById(int id) throws DAOException {
        return get(c -> {
            PreparedStatement pc = c.prepareStatement(SQL_GET_BY_ID);
            pc.setInt(1, id);
            return pc;
        });
    }

    @Override
    public void add(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_ADD);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPasswordHash());
            preparedStatement.setString(5, user.getAvatar());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void changeUserInfo(UserInfo userInfo) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_INFO);
            preparedStatement.setString(1, userInfo.getFirstName());
            preparedStatement.setString(2, userInfo.getLastName());
            preparedStatement.setInt(3, userInfo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void changePassword(int id, String passwordHash) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_PASSWORD);
            preparedStatement.setString(1, passwordHash);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void changeEmail(int id, String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_EMAIL);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void remove(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_REMOVE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @FunctionalInterface
    public interface ThrowingFunction<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    private Optional<User> get(
            ThrowingFunction<Connection, PreparedStatement, SQLException> statementCreator)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<User> user = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = statementCreator.apply(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User u = new User();
                u.setId(resultSet.getInt(1));
                u.setEmail(resultSet.getString(2));
                u.setFirstName(resultSet.getString(3));
                u.setLastName(resultSet.getString(4));
                u.setPasswordHash(resultSet.getString(5));
                u.setAvatar(resultSet.getString(6));
                user = Optional.of(u);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return user;
    }
}
