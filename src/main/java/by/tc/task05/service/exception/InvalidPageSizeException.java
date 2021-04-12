package by.tc.task05.service.exception;

public class InvalidPageSizeException extends ServiceException {
    private static final long serialVersionUID = -2294233450244350517L;
    public InvalidPageSizeException(){
        super("Page size is invalid");
    }
}
