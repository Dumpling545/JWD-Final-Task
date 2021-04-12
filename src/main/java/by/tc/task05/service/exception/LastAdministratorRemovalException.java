package by.tc.task05.service.exception;

public class LastAdministratorRemovalException extends ServiceException {
    private static final long serialVersionUID = -2523938203297415347L;
    public LastAdministratorRemovalException(){
        super("Attempt to remove last administrator from hotel in question");
    }
}
