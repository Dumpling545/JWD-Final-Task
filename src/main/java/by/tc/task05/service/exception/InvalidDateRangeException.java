package by.tc.task05.service.exception;

public class InvalidDateRangeException extends ServiceException {
    private static final long serialVersionUID = 1L;

    public InvalidDateRangeException(String message){
        super(message);
    }
}
