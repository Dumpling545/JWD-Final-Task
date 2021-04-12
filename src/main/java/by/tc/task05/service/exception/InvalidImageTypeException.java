package by.tc.task05.service.exception;

public class InvalidImageTypeException extends ServiceException {
    private static final long serialVersionUID = -4123079574230776578L;
    public InvalidImageTypeException() {
        super("Invalid image type");
    }
}
