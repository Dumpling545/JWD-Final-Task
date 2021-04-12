package by.tc.task05.service.exception;

public class EmptyNameException extends ServiceException {
    private static final long serialVersionUID = 1741566444773688706L;
    public EmptyNameException() {
        super("First and/or last name is null or empty");
    }
}
