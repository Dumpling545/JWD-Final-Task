package by.tc.task05.service.exception;

public class InvalidPasswordRepeatingException  extends  ServiceException{
    private static final long serialVersionUID = -5263058309044444248L;
    public InvalidPasswordRepeatingException(){
        super("Invalid password repeating");
    }
}
