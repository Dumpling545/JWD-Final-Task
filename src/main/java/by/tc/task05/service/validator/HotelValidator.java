package by.tc.task05.service.validator;

import by.tc.task05.entity.HotelForm;
import by.tc.task05.service.exception.ServiceException;

/**
 * Interface that contains methods validating input associated with hotel's
 * actions
 */
public interface HotelValidator {
	void validateHotelForm(HotelForm form) throws ServiceException;
}
