package by.tc.task05.service.exception;

public class InvalidPageSizeException extends ServiceException {
    public InvalidPageSizeException(){
        super("Page size is invalid");
    }
}
