package by.tc.task05.service.validator.impl;

import java.time.LocalDate;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.ServiceException;
import by.tc.task05.service.validator.RoomSearchValidator;

public class RoomSearchValidatorImpl implements RoomSearchValidator {
    private static int DEFAULT_PAGE = 1;
    private static int DEFAULT_PAGE_SIZE = 10;

    @Override
    public void validateFilter(RoomSearchServiceFilter filter)
            throws ServiceException {
        if (filter.getCheckInDate() == null && filter.getCheckOutDate() != null
                || filter.getCheckInDate() != null
                        && filter.getCheckOutDate() == null) {
            throw new ServiceException(
                    "Only searches without dates or with two dates are valid");
        }
        if (filter.getCheckInDate().isBefore(LocalDate.now())) {
            throw new ServiceException(
                    "Check in/out date range cannot be started at past");
        }
        if (!filter.getCheckInDate().isBefore(filter.getCheckOutDate())) {
            throw new ServiceException("Invalid date range");
        }
        if (filter.getCheckInDate() == null) {
            filter.setCheckInDate(LocalDate.parse("9999-12-31"));
            filter.setCheckOutDate(filter.getCheckInDate());
        }
        if (filter.getCostLowBound() < 0) {
            throw new ServiceException("Price cannot be negative");
        }
        if (filter.getCostLowBound() > filter.getCostHighBound()) {
            throw new ServiceException("Invalid price range");
        }
        if (filter.getNumberOfBeds() < 1) {
            throw new ServiceException("Number of beds can only be positive");
        }
        if (filter.getRatingLowBound() < 1
                || filter.getRatingHighBound() > 10) {
            throw new ServiceException("Rating can only be in range [1, 10]");
        }
        if (filter.getRatingLowBound() > filter.getRatingHighBound()) {
            throw new ServiceException("Invalid rating range");
        }
        if (filter.getLocation() == null || filter.getLocation().isBlank()) {
            throw new ServiceException("Search without location is invalid");
        }
        if(filter.getPage() < 1){
            filter.setPage(DEFAULT_PAGE);
        }
        if(filter.getPageSize() < 1){
            filter.setPageSize(DEFAULT_PAGE_SIZE);
        }
    }

}
