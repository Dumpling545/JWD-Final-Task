package by.tc.task05.dao.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionPool {
	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;
	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;

	//private static final String DB_BUNDLE = "by.tc.task05.bundle.db";
	private static final String DB_DRIVER = "db.driver";
	private static final String DB_URL = "db.url";
	private static final String DB_USER = "db.user";
	private static final String DB_PASSWORD = "db.password";
	private static final String DB_POOL_SIZE = "db.poolsize";
	private static final int DEFAULT_POOL_SIZE = 5;
	private final AtomicBoolean updateStarted = new AtomicBoolean();
	private final CountDownLatch updateFinished = new CountDownLatch(1);
	private static final ConnectionPool connectionPool = new ConnectionPool();
	private static Logger logger = LogManager.getLogger();
	private ConnectionPool() {
	}

	public void init(String databaseBundleName) throws ConnectionPoolException {
		if (updateStarted.compareAndSet(false, true)) {
			ResourceBundle dbResourceBundle =
					ResourceBundle.getBundle(databaseBundleName);
			this.driverName = dbResourceBundle.getString(DB_DRIVER);
			this.url = dbResourceBundle.getString(DB_URL);
			this.user = dbResourceBundle.getString(DB_USER);
			this.password = dbResourceBundle.getString(DB_PASSWORD);
			try {
				this.poolSize =
						Integer.parseInt(
								dbResourceBundle.getString(DB_POOL_SIZE));
			} catch (NumberFormatException e) {
				logger.error("Cannot parse pool size", e);
				poolSize = DEFAULT_POOL_SIZE;
			}
			Locale.setDefault(Locale.ENGLISH);
			try {
				Class.forName(driverName);
				givenAwayConQueue =
						new ArrayBlockingQueue<Connection>(poolSize);
				connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
				for (int i = 0; i < poolSize; i++) {
					Connection connection =
							DriverManager.getConnection(url, user, password);
					connectionQueue.add(connection);
				}
			} catch (SQLException e) {
				logger.error("SQLException on Connection Pool initialization", e);
				throw new ConnectionPoolException(
						"SQLException in ConnectionPool", e);
			} catch (ClassNotFoundException e) {
				logger.error("ClassNotFoundException on Connection Pool initialization", e);
				throw new ConnectionPoolException(
						"Can't find database driver class", e);
			}
			updateFinished.countDown();
			logger.info("Connection Pool initialized");
		} else {
			try {
				updateFinished.await();
			} catch (InterruptedException e) {
				logger.error("InterruptedException on Connection Pool initialization", e);
				throw new ConnectionPoolException(
						"ConnectionPool initialization error", e);
			}
		}
	}

	public static ConnectionPool getInstance() throws ConnectionPoolException {
		return connectionPool;
	}

	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = connectionQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			logger.error("InterruptedException on taking connection from Connection Pool", e);
			throw new ConnectionPoolException(
					"Error connecting to the data source.", e);
		}
		return connection;
	}

	public void releaseConnection(Connection connection)
			throws ConnectionPoolException
	{
		if (connection == null) {
			throw new ConnectionPoolException("Cannot release null connection");
		}
		if (givenAwayConQueue.remove(connection)) {
			connectionQueue.add(connection);
		} else {
			logger.error("Connection is not taken");
			throw new ConnectionPoolException("Connection isn't taken");
		}
	}

	public void closeConnections() throws ConnectionPoolException {
		Connection connection = null;
		while (!givenAwayConQueue.isEmpty()) {
			try {
				connection = givenAwayConQueue.take();
				connection.close();
			} catch (InterruptedException | SQLException e) {
				logger.error("Exception on taking closing connections in Connection Pool", e);
				throw new ConnectionPoolException(e);
			}
		}
		while (!connectionQueue.isEmpty()) {
			try {
				connection = connectionQueue.take();
				connection.close();
			} catch (InterruptedException | SQLException e) {
				logger.error("Exception on taking closing connections in Connection Pool", e);
				throw new ConnectionPoolException(e);
			}
		}
		logger.info("Connection Pool closed");
	}
}
