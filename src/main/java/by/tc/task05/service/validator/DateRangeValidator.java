package by.tc.task05.service.validator;

import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.exception.ServiceException;

import java.time.LocalDate;

public interface DateRangeValidator {
    public void validateRange(LocalDate start, LocalDate end, boolean throwOnBothNull)
            throws ServiceException;
}
