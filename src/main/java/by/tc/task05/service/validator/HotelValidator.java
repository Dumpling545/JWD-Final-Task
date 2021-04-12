package by.tc.task05.service.validator;

import by.tc.task05.entity.HotelForm;
import by.tc.task05.service.exception.ServiceException;

public interface HotelValidator {
    public void validateHotelForm(HotelForm form) throws ServiceException;
}
