package by.tc.task05.service.exception;

public class InvalidArchivationReasonException extends ServiceException {
    private static final long serialVersionUID = 7327798881548224621L;
    public InvalidArchivationReasonException() {
        super("Invalid archivation reason");
    }
}
