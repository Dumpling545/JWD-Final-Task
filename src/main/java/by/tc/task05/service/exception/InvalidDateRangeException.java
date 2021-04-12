package by.tc.task05.service.exception;

public class InvalidDateRangeException extends ServiceException {
    private static final long serialVersionUID = 5207000118625030661L;

    public InvalidDateRangeException(String message){
        super(message);
    }
}
