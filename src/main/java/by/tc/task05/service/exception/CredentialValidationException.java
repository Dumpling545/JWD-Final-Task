package by.tc.task05.service.exception;

public class CredentialValidationException extends ServiceException {
    private static final long serialVersionUID = -8313135393026519844L;

    public CredentialValidationException(String msg){
        super(msg);
    }
}
