package by.tc.task05.service.exception;

public class AdministratorAccountDeletionException extends ServiceException {
    private static final long serialVersionUID = -3004628654947282750L;
    public AdministratorAccountDeletionException(Exception e){
        super("Attempt to delete hotel administrator", e);
    }
}
