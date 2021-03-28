package by.tc.task05.service.exception;

public class InvalidUserException extends ServiceException {
    public InvalidUserException(){
        super("Invalid user");
    }
}
