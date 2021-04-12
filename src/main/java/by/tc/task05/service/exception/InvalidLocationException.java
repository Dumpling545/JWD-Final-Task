package by.tc.task05.service.exception;

public class InvalidLocationException extends ServiceException {
    private static final long serialVersionUID = 7640614508942987547L;
    public InvalidLocationException() {
        super("Invalid location");
    }
}
