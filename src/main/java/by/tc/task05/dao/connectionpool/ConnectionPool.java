package by.tc.task05.dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {
    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_POLL_SIZE = "db.poolsize";
    private final AtomicBoolean updateStarted = new AtomicBoolean();
    private final CountDownLatch updateFinished = new CountDownLatch(1);
    private static final ConnectionPool connectionPool = new ConnectionPool();

    private ConnectionPool() {
        ResourceBundle dbResourceBundle = ResourceBundle.getBundle(
                "by.tc.task05.bundle.db");
        this.driverName = dbResourceBundle.getString(DB_DRIVER);
        this.url = dbResourceBundle.getString(DB_URL);
        this.user = dbResourceBundle.getString(DB_USER);
        ;
        this.password = dbResourceBundle.getString(DB_PASSWORD);
        try {
            this.poolSize =
                    Integer.parseInt(dbResourceBundle.getString(DB_POLL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }
    }

    public void init() throws ConnectionPoolException {
        if (updateStarted.compareAndSet(false, true)) {
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
                throw new ConnectionPoolException(
                        "SQLException in ConnectionPool", e);
            } catch (ClassNotFoundException e) {
                throw new ConnectionPoolException(
                        "Can't find database driver class", e);
            }
            updateFinished.countDown();
        } else {
            try {
                updateFinished.await();
            } catch (InterruptedException e) {
                throw new ConnectionPoolException(
                        "ConnectionPool initialization error", e);
            }
        }
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        connectionPool.init();
        return connectionPool;
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(
                    "Error connecting to the data source.", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection)
            throws ConnectionPoolException {
        if (connection == null) {
            throw new ConnectionPoolException("Cannot release null connection");
        }
        if (givenAwayConQueue.remove(connection)) {
            connectionQueue.add(connection);
        } else {
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
                throw new ConnectionPoolException(e);
            }
        }
        while (!connectionQueue.isEmpty()) {
            try {
                connection = connectionQueue.take();
                connection.close();
            } catch (InterruptedException | SQLException e) {
                throw new ConnectionPoolException(e);
            }
        }
    }
}
