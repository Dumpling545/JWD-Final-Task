package by.tc.task05.dao.exception;

public class OccupiedDateRangeDAOException extends DAOException {
    private static final long serialVersionUID = 91591752170277802L;

    public OccupiedDateRangeDAOException() {
        super();
    }

    public OccupiedDateRangeDAOException(String message) {
        super(message);
    }

    public OccupiedDateRangeDAOException(Exception e) {
        super(e);
    }
}
