package by.tc.task05.service.exception;

public class EmailAlreadyRegisteredException extends ServiceException{
	private static final long serialVersionUID = -8965168930360425340L;
	public EmailAlreadyRegisteredException(Exception e) {
		super("Attempt to set user's email to the one already existed in database", e);
	}
}
