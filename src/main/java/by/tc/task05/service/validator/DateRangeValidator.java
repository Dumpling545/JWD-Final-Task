package by.tc.task05.service.validator;

import by.tc.task05.service.exception.ServiceException;

import java.time.LocalDate;

/**
 * Interface that contains methods validating input associated with actions with
 * date ranges
 */
public interface DateRangeValidator {
	void validateRange(LocalDate start, LocalDate end, boolean throwOnBothNull)
			throws ServiceException;
}
