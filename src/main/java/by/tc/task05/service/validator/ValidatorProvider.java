package by.tc.task05.service.validator;

import by.tc.task05.service.validator.impl.CredentialsValidatorImpl;

public final class ValidatorProvider {
    private static final ValidatorProvider instance = new ValidatorProvider(); 

	private ValidatorProvider() {}
	
	private final CredentialsValidator validator = new CredentialsValidatorImpl();
	
	public static ValidatorProvider getInstance() {
		return instance;
	}

	public CredentialsValidator getValidator() {
		return validator;
	}

}
