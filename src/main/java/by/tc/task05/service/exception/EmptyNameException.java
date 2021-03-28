package by.tc.task05.service.exception;

public class EmptyNameException extends ServiceException {
    public EmptyNameException() {
        super("First and/or last name is null or empty");
    }
}
