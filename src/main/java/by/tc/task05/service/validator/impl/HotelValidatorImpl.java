package by.tc.task05.service.validator.impl;

import by.tc.task05.entity.HotelForm;
import by.tc.task05.service.exception.*;
import by.tc.task05.service.validator.HotelValidator;
import fr.marcwrobel.jbanking.iban.Iban;

public class HotelValidatorImpl implements HotelValidator {
    private final static double LATITUDE_ABSOLUTE_BOUND = 90;
    private final static double LONGITUDE_ABSOLUTE_BOUND = 180;

    @Override
    public void validateHotelForm(HotelForm form) throws ServiceException {
        if (!Iban.isValid(form.getBankAccount())) {
            throw new InvalidIBANException();
        }
        if (form.getCachedAddress() == null ||
                form.getCachedAddress().isBlank()) {
            throw new EmptyLocationException();
        }
        if (Math.abs(form.getLatitudeAddress()) > LATITUDE_ABSOLUTE_BOUND ||
                Math.abs(form.getLongitudeAddress()) >
                        LONGITUDE_ABSOLUTE_BOUND) {
            throw new InvalidLocationException();
        }
        if (form.getName() == null || form.getName().isBlank()) {
            throw new EmptyInanimateNameException();
        }
    }
}
