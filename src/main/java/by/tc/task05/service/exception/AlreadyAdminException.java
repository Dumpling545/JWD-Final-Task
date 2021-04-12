package by.tc.task05.service.exception;

public class AlreadyAdminException extends ServiceException {
    private static final long serialVersionUID = 2062325541744239716L;
    public AlreadyAdminException() {
        super("User is already administrator");
    }
}
