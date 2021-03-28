package by.tc.task05.service.exception;

public class EmptyLocationException extends ServiceException {
    private static final long serialVersionUID = -638909486623685956L;
    public EmptyLocationException() {
        super("Location information is null or empty");
    }
    
}
