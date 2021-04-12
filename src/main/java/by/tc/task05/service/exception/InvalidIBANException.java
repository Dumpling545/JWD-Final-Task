package by.tc.task05.service.exception;

public class InvalidIBANException extends ServiceException {
    private static final long serialVersionUID = 8991163221257688901L;
    public InvalidIBANException() {
        super("Invalid IBAN");
    }
}
