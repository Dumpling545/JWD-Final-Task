package by.tc.task05;

import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import by.tc.task05.utils.DatabaseHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DatabaseExtension implements BeforeAllCallback,
		ExtensionContext.Store.CloseableResource
{
	private static final String SQL_CLEAR_RESERVATIONS =
			"DELETE FROM reservations";
	private static final String SQL_CLEAR_REVIEWS = "DELETE FROM reviews";
	private static final String SQL_CLEAR_ARCHIVED_RESERVATIONS =
			"DELETE FROM archived_reservations";
	private static final String SQL_CLEAR_ROOM_FEATURES =
			"DELETE FROM room_features";
	private static final String SQL_CLEAR_ROOMS = "DELETE FROM rooms";
	private static final String SQL_CLEAR_ADMINS =
			"DELETE FROM m2m_administrator_hotel";
	private static final String SQL_CLEAR_HOTELS = "DELETE FROM hotels";
	private static final String SQL_CLEAR_USERS = "DELETE FROM users";
	private static final String TEST_DB_BUNDLE = "by.tc.task05.bundle.test_db";

	private void clearDatabase() {
		Connection connection = null;
		Statement statement = null;
		try{
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.createStatement();
			statement.executeUpdate(SQL_CLEAR_RESERVATIONS);
			statement.executeUpdate(SQL_CLEAR_REVIEWS);
			statement.executeUpdate(SQL_CLEAR_ARCHIVED_RESERVATIONS);
			statement.executeUpdate(SQL_CLEAR_ROOM_FEATURES);
			statement.executeUpdate(SQL_CLEAR_ROOMS);
			statement.executeUpdate(SQL_CLEAR_ADMINS);
			statement.executeUpdate(SQL_CLEAR_HOTELS);
			statement.executeUpdate(SQL_CLEAR_USERS);
		} catch (SQLException e){
			Assertions.fail(e);
		} finally {
			DatabaseHelper.closeResources(statement, connection);
		}
	}

	@Override
	public void beforeAll(ExtensionContext extensionContext) throws Exception{
		String uniqueKey = this.getClass().getName();
		Object value =
				extensionContext.getRoot().getStore(GLOBAL).get(uniqueKey);
		if (value == null) {
			extensionContext.getRoot().getStore(GLOBAL).put(uniqueKey, this);
			ConnectionPool.getInstance().init(TEST_DB_BUNDLE);
			clearDatabase();
		}
	}

	@Override
	public void close() throws Throwable {
		clearDatabase();
		ConnectionPool.getInstance().closeConnections();
	}
}
