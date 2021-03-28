package by.tc.task05.service.exception;

public class InvalidPageException extends ServiceException {
    public InvalidPageException(){
        super("Invalid number of page");
    }
}
