package by.tc.task05.service.exception;

public class EmptyTitleException extends ServiceException {
	private static final long serialVersionUID = -1222490928716210307L;
	public EmptyTitleException() {
		super("Title is null or empty");
	}
}