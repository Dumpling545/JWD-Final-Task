package by.tc.task05.dao.exception;

public class EmailAlreadyRegisteredDAOException extends DAOException{
	private static final long serialVersionUID = 5473764869719877422L;

	public EmailAlreadyRegisteredDAOException() {
		super();
	}

	public EmailAlreadyRegisteredDAOException(String message) {
		super(message);
	}

	public EmailAlreadyRegisteredDAOException(Exception e) {
		super(e);
	}
}
