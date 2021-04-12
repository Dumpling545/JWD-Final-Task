package by.tc.task05.service.exception;

public class InvalidUserException extends ServiceException {
    private static final long serialVersionUID = -726640531785015038L;
    public InvalidUserException(){
        super("Invalid user");
    }
}
