package by.tc.task05.utils;

import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;

import java.io.Closeable;
import java.sql.*;

public class DatabaseHelper {
	public static void closeResource(AutoCloseable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (Exception exception) {
				//TODO logging
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
				//TODO logging
			}
			try {
				ConnectionPool.getInstance().releaseConnection(connection);
			} catch (ConnectionPoolException exception) {
				//TODO logging
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
