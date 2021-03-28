package by.tc.task05.service.exception;

public class UnauthorizedActionException extends ServiceException {
    private static final long serialVersionUID = 2971534008818786927L;
    public UnauthorizedActionException(){
        super("Unathorized action");
    }

}
