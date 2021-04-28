package by.tc.task05.utils;

import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.sql.*;

public class DatabaseHelper {
	private static Logger logger = LogManager.getLogger();
	public static void closeResource(AutoCloseable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (Exception exception) {
				logger.error("Error on closing resource", exception);
			}
		}
	}

	public static void closeResource(Connection connection) {
		if (connection != null) {
			try {
				if (!connection.getAutoCommit()) {
					connection.setAutoCommit(true);
				}
			} catch (SQLException exception) {
				logger.error("Error on closing resource: enabling auto-commit", exception);
			}
			try {
				ConnectionPool.getInstance().releaseConnection(connection);
			} catch (ConnectionPoolException exception) {
				logger.error("Error on closing resource: take connection", exception);
			}
		}
	}

	public static void closeResources(Statement st, Connection connection) {
		closeResource(st);
		closeResource(connection);
	}

	public static void closeResources(ResultSet rs, Statement st,
	                                  Connection connection)
	{
		closeResource(rs);
		closeResources(st, connection);
	}
}
