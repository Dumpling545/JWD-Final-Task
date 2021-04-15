package by.tc.task05.service.validator.impl;

import by.tc.task05.service.exception.InvalidDateRangeException;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.service.validator.DateRangeValidator;

import java.time.LocalDate;

public class DateRangeValidatorImpl implements DateRangeValidator {
    private final static String ONE_DATE_SEARCH_MSG = "Search with one date";
    private final static String DATE_IN_PAST_MSG = "Booking date in past";
    private final static String WRONG_DATE_ORDER_MSG =
            "Wrong date range bounds order";
    private static final String BOTH_DATES_NULL_MSG = "Both dates are null";

    @Override
    public void validateRange(LocalDate start, LocalDate end, boolean throwOnBothNull)
            throws ServiceException {
        if (start == null &&
                end != null ||
                start != null &&
                        end == null) {
            throw new InvalidDateRangeException(ONE_DATE_SEARCH_MSG);
        }
        if (start != null &&
                start.isBefore(LocalDate.now())) {
            throw new InvalidDateRangeException(DATE_IN_PAST_MSG);
        }
        if (start != null &&
                !start.isBefore(end)) {
            throw new InvalidDateRangeException(WRONG_DATE_ORDER_MSG);
        }
        if (throwOnBothNull && start == null){
            throw new InvalidDateRangeException(BOTH_DATES_NULL_MSG);
        }
    }
}
