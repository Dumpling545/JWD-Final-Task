package by.tc.task05.service.exception;

public class InvalidRatingRangeException extends ServiceException {
    private static final long serialVersionUID = -5408111963200984181L;

    public InvalidRatingRangeException(String msg) {
        super(msg);
    }
}
