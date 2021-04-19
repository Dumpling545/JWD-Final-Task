package by.tc.task05.service.exception;

public class RoomOrHotelWithActiveReservationsDeletionException
        extends ServiceException {
    private static final long serialVersionUID = -9178953070640026360L;

    public RoomOrHotelWithActiveReservationsDeletionException(Exception e) {
        super("Attempt to delete room/hotel with active reservation", e);
    }
}
