package by.tc.task05.service.exception;

public class NoSuchHotelException extends ServiceException {
    private static final long serialVersionUID = 1959089942391072609L;
    public NoSuchHotelException(){
        super("Hotel does not exist");
    }
}
