package by.tc.task05.service.validator.impl;

import java.time.LocalDate;

import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.exception.*;
import by.tc.task05.service.validator.RoomSearchValidator;

public class RoomSearchValidatorImpl implements RoomSearchValidator {

    private final static int DEFAULT_PAGE = 1;
    private final static int DEFAULT_PAGE_SIZE = 10;
    private final static int MAX_PRICE = 1000000;
    private final static LocalDate MAXIMUM_DATE = LocalDate.parse("9999-12-31");

    private final static String ONE_DATE_SEARCH_MSG = "Search with one date";
    private final static String DATE_IN_PAST_MSG = "Booking date in past";
    private final static String WRONG_DATE_ORDER_MSG = "Wrong date range bounds order";

    private final static String NEGATIVE_PRICE_MSG = "Price range has negative values";
    private final static String WRONG_PRICE_ORDER_MSG = "Wrong price range bounds order";

    private final static String NON_POSITIVE_NUMBER_OF_BEDS_MSG = "Number of beds is non positive";

    private final static String RATING_OUT_OF_BOUNDS_MAG = "Rating is out of bounds";
    private final static String WRONG_RATING_ORDER_MSG = "Wrong rating range bounds order";

    private final static int MIN_RATING = 1;
    private final static int MAX_RATING = 10;

    @Override
    public void validateFilter(RoomSearchServiceFilter filter)
            throws ServiceException {
        if (filter.getCheckInDate() == null && filter.getCheckOutDate() != null
                || filter.getCheckInDate() != null
                && filter.getCheckOutDate() == null) {
            throw new InvalidDateRangeException(ONE_DATE_SEARCH_MSG);
        }
        if (filter.getCheckInDate() != null &&
                filter.getCheckInDate().isBefore(LocalDate.now())) {
            throw new InvalidDateRangeException(DATE_IN_PAST_MSG);
        }
        if (filter.getCheckInDate() != null &&
                !filter.getCheckInDate().isBefore(filter.getCheckOutDate())) {
            throw new InvalidDateRangeException(WRONG_DATE_ORDER_MSG);
        }
        if (filter.getCheckInDate() == null) {
            filter.setCheckInDate(MAXIMUM_DATE);
            filter.setCheckOutDate(filter.getCheckInDate());
        }
        if (!filter.isCostLowBoundInitialized()) {
            filter.setCostLowBound(0);
        } else if (filter.getCostLowBound() < 0) {
            throw new InvalidPriceRangeException(NEGATIVE_PRICE_MSG);
        }
        if (!filter.isCostHighBoundInitialized()) {
            //TODO write something better
            filter.setCostHighBound(MAX_PRICE);
        }
        if (filter.getCostLowBound() > filter.getCostHighBound()) {
            throw new InvalidPriceRangeException(WRONG_PRICE_ORDER_MSG);
        }
        if (!filter.isNumberOfBedsInitialized()) {
            filter.setNumberOfBeds(1);
        } else if (filter.getNumberOfBeds() < 1) {
            throw new NumberOfBedsException(NON_POSITIVE_NUMBER_OF_BEDS_MSG);
        }
        if (!filter.isRatingLowBoundInitialized()) {
            filter.setRatingLowBound(MIN_RATING);
        }
        if(!filter.isRatingHighBoundInitialized()){
            filter.setRatingHighBound(MAX_RATING);
        }
        if (filter.getRatingLowBound() < MIN_RATING
                || filter.getRatingHighBound() > MAX_RATING) {
            throw new InvalidRatingRangeException(RATING_OUT_OF_BOUNDS_MAG);
        }
        if (filter.getRatingLowBound() > filter.getRatingHighBound()) {
            throw new InvalidRatingRangeException(WRONG_RATING_ORDER_MSG);
        }
        if (filter.getLocation() == null || filter.getLocation().isBlank()) {
            throw new EmptyLocationException();
        }
        if(!filter.isPageInitialized()){
            filter.setPage(DEFAULT_PAGE);
        }else if (filter.getPage() < 1) {
            throw new InvalidPageException();
        }
        if(!filter.isPageSizeInitialized()){
            filter.setPageSize(DEFAULT_PAGE_SIZE);
        }else if (filter.getPageSize() < 1) {
            throw new InvalidPageSizeException();
        }
    }

}
