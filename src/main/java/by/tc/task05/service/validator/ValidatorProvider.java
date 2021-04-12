package by.tc.task05.service.validator;

import by.tc.task05.service.validator.impl.*;

public final class ValidatorProvider {
    private static final ValidatorProvider instance = new ValidatorProvider();

    private ValidatorProvider() {
    }

    private final UserValidator credValidator = new UserValidatorImpl();
    private final RoomValidator rsValidator =
            new RoomValidatorImpl();
    private final ImageValidator imageValidator = new ImageValidatorImpl();
    private final HotelValidator hotelValidator = new HotelValidatorImpl();
    private final PageValidator pageValidator = new PageValidatorImpl();
    public static ValidatorProvider getInstance() {
        return instance;
    }

    public RoomValidator getRoomValidator() {
        return rsValidator;
    }

    public UserValidator getCredentialsValidator() {
        return credValidator;
    }

    public ImageValidator getImageValidator() {
        return imageValidator;
    }

    public HotelValidator getHotelValidator() {
        return hotelValidator;
    }
    public PageValidator getPageValidator() {
        return pageValidator;
    }
}
