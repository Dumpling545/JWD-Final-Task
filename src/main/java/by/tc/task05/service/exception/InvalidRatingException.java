package by.tc.task05.service.exception;

public class InvalidRatingException extends ServiceException {
	private static final long serialVersionUID = -7284493888487307906L;
	public InvalidRatingException(){
		super("Invalid rating");
	}
}
