package by.tc.task05.service.exception;

public class NoSuchReservationException extends ServiceException {
    private static final long serialVersionUID = 3822406673496368001L;
    public NoSuchReservationException(){
        super("Reservation does not exist");
    }
}
