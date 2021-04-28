package by.tc.task05.utils;

import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.sql.*;

/**
 * Class that helps close resources at DAO level.
 */
public class DatabaseHelper {
	private static Logger logger = LogManager.getLogger();

	/**
	 * Closes resource and suppresses possible exceptions in order to close
	 * resources that might be closed next. However, all thrown exceptions are
	 * logged
	 *
	 * @param resource
	 * 		resource to be closed
	 */
	public static void closeResource(AutoCloseable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (Exception exception) {
				logger.error("Error on closing resource", exception);
			}
		}
	}

	/**
	 * 'Closes' connection by taking it back to the connection pool and
	 * suppresses possible exceptions in order to close resources that might be
	 * closed next. However, all thrown exceptions are logged
	 *
	 * @param connection
	 * 		connection to be 'closed'
	 */
	public static void closeResource(Connection connection) {
		if (connection != null) {
			try {
				if (!connection.getAutoCommit()) {
					connection.setAutoCommit(true);
				}
			} catch (SQLException exception) {
				logger.error("Error on closing resource: enabling auto-commit",
						exception);
			}
			try {
				ConnectionPool.getInstance().releaseConnection(connection);
			} catch (ConnectionPoolException exception) {
				logger.error("Error on closing resource: take connection",
						exception);
			}
		}
	}

	/**
	 * Closes two resources that is usually used in NON-SELECT SQL queries
	 * @param st SQL statement to be closed
	 * @param connection Connection to be 'closed'
	 * @see DatabaseHelper#closeResource(Connection)
	 * @see DatabaseHelper#closeResource(AutoCloseable)
	 */
	public static void closeResources(Statement st, Connection connection) {
		closeResource(st);
		closeResource(connection);
	}

	/**
	 * Closes three resources that is usually used in SELECT SQL queries
	 * @param rs Result set to be closed
	 * @param st SQL statement to be closed
	 * @param connection Connection to be 'closed'
	 * @see DatabaseHelper#closeResources(Statement, Connection)
	 * @see DatabaseHelper#closeResource(AutoCloseable)
	 */
	public static void closeResources(ResultSet rs, Statement st,
	                                  Connection connection)
	{
		closeResource(rs);
		closeResources(st, connection);
	}
}
