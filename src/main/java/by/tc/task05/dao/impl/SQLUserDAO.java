package by.tc.task05.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.UserDAO;
import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import by.tc.task05.entity.User;

public class SQLUserDAO implements UserDAO {
    private final String GET_BY_ID =
            "SELECT (id, email, first_name, last_name, password_hash, avatar) "
                    + "FROM users WHERE id=?";
    private final String GET_BY_EMAIL =
            "SELECT (id, email, first_name, last_name, password_hash, avatar) "
                    + "FROM users WHERE email=?";
    private final String ADD_SQL = "INSERT INTO "
            + "users(email, first_name, last_name, password_hash, avatar)"
            + " VALUES(?,?,?,?,?)";


    @Override
    public User getByEmail(String email)
            throws DAOException {
        return get(c -> {
            PreparedStatement pc = c.prepareStatement(GET_BY_EMAIL);
            pc.setString(1, email);
            return pc;
        });
    }

    @Override
    public User getById(int id) throws DAOException {
        return get(c -> {
            PreparedStatement pc = c.prepareStatement(GET_BY_ID);
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

            preparedStatement = connection.prepareStatement(ADD_SQL);
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
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @FunctionalInterface
    public interface ThrowingFunction<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    private User get(
            ThrowingFunction<Connection, PreparedStatement, SQLException> statementCreator)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = statementCreator.apply(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setFirstName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setPasswordHash(resultSet.getString(5));
                user.setAvatar(resultSet.getString(6));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return user;
    }
}
