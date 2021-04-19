package by.tc.task05.dao.exception;

public class RoomOrHotelWithActiveReservationsDeletionDAOException
        extends DAOException {
    private static final long serialVersionUID = 6386897255518874828L;

    public RoomOrHotelWithActiveReservationsDeletionDAOException() {
        super();
    }

    public RoomOrHotelWithActiveReservationsDeletionDAOException(
            String message) {
        super(message);
    }

    public RoomOrHotelWithActiveReservationsDeletionDAOException(Exception e) {
        super(e);
    }
}
