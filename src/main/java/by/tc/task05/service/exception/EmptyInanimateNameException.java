package by.tc.task05.service.exception;

public class EmptyInanimateNameException extends ServiceException {
    private static final long serialVersionUID = -7379523784230254231L;
    public EmptyInanimateNameException() {
        super("Object's name is null or empty");
    }
}
