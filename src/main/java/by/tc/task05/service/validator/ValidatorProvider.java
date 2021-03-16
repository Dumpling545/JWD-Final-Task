package by.tc.task05.service.validator;

import by.tc.task05.service.validator.impl.CredentialsValidatorImpl;
import by.tc.task05.service.validator.impl.RoomSearchValidatorImpl;

public final class ValidatorProvider {
    private static final ValidatorProvider instance = new ValidatorProvider(); 

	private ValidatorProvider() {}
	
	private final CredentialsValidator credValidator = new CredentialsValidatorImpl();
	private final RoomSearchValidator rsValidator = new RoomSearchValidatorImpl();
	public static ValidatorProvider getInstance() {
		return instance;
	}
	public RoomSearchValidator getRoomSearchValidator(){
		return rsValidator;
	}
	public CredentialsValidator getCredentialsValidator() {
		return credValidator;
	}

}
