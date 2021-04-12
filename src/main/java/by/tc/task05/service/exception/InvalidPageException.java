package by.tc.task05.service.exception;

public class InvalidPageException extends ServiceException {
    private static final long serialVersionUID = -662232899445147002L;
    public InvalidPageException(){
        super("Invalid number of page");
    }
}
