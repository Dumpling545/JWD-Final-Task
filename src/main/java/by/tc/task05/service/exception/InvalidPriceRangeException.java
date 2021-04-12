package by.tc.task05.service.exception;

public class InvalidPriceRangeException extends ServiceException {
    private static final long serialVersionUID = 3057059797820869316L;
    
    public InvalidPriceRangeException(String msg){
        super(msg);
    }
}
