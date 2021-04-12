package by.tc.task05.dao.connectionpool;

public class ConnectionPoolException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }

    public ConnectionPoolException(Exception e) {
        super(e);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }
}
