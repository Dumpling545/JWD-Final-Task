package by.tc.task05.service.exception;

public class CannotCreateReviewException extends ServiceException {
	private static final long serialVersionUID = 1316114134238653920L;
	public CannotCreateReviewException() {
		super("Review creation is not available yet");
	}
}
