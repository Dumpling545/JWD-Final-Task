package by.tc.task05.service.validator.impl;

import java.time.LocalDate;

import by.tc.task05.entity.Room;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.exception.*;
import by.tc.task05.service.validator.DateRangeValidator;
import by.tc.task05.service.validator.RoomValidator;
import by.tc.task05.service.validator.ValidatorProvider;

public class RoomValidatorImpl implements RoomValidator {

	private final static double MAX_PRICE = 9999999999d;
	private final static LocalDate MAXIMUM_DATE = LocalDate.parse("9999-12-31");


	private final static String NEGATIVE_PRICE_MSG =
			"Price range has negative values";
	private final static String WRONG_PRICE_ORDER_MSG =
			"Wrong price range bounds order";

	private final static String NON_POSITIVE_NUMBER_OF_BEDS_MSG =
			"Number of beds is non positive";

	private final static String RATING_OUT_OF_BOUNDS_MAG =
			"Rating is out of bounds";
	private final static String WRONG_RATING_ORDER_MSG =
			"Wrong rating range bounds order";

	private final static int NO_REVIEW_RATING = 0;
	private final static int MIN_RATING = 1;
	private final static int MAX_RATING = 10;

	@Override
	public void validateFilter(RoomSearchServiceFilter filter)
			throws ServiceException
	{
		DateRangeValidator dateRangeValidator = ValidatorProvider.getInstance()
				.getDateRangeValidator();
		dateRangeValidator.validateRange(filter.getCheckInDate(),
				filter.getCheckOutDate(), false);
		if (filter.getCheckInDate() == null &&
				filter.getCheckOutDate() == null)
		{
			filter.setCheckInDate(MAXIMUM_DATE);
			filter.setCheckOutDate(filter.getCheckInDate());
		}
		if (!filter.isCostLowBoundInitialized()) {
			filter.setCostLowBound(0);
		} else if (filter.getCostLowBound() < 0) {
			throw new InvalidPriceRangeException(NEGATIVE_PRICE_MSG);
		}
		if (!filter.isCostHighBoundInitialized()) {
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
			filter.setRatingLowBound(NO_REVIEW_RATING);
		}
		if (!filter.isRatingHighBoundInitialized()) {
			filter.setRatingHighBound(MAX_RATING);
		}
		if ((filter.getRatingLowBound() != NO_REVIEW_RATING &&
				filter.getRatingLowBound() < MIN_RATING) ||
				filter.getRatingHighBound() > MAX_RATING)
		{
			throw new InvalidRatingRangeException(RATING_OUT_OF_BOUNDS_MAG);
		}
		if (filter.getRatingLowBound() > filter.getRatingHighBound()) {
			throw new InvalidRatingRangeException(WRONG_RATING_ORDER_MSG);
		}
		if (filter.getLocation() == null || filter.getLocation().isBlank()) {
			throw new EmptyLocationException();
		}
	}

	@Override
	public void validateRoom(Room room) throws ServiceException {
		if (room.getName() == null || room.getName().isBlank()) {
			throw new EmptyInanimateNameException();
		}
		if (room.getShortDescription() == null ||
				room.getShortDescription().isBlank())
		{
			throw new EmptyInanimateNameException();
		}
		if (room.getCost() < 0) {
			throw new InvalidPriceRangeException(NEGATIVE_PRICE_MSG);
		}
		if (room.getNumberOfBeds() < 1) {
			throw new NumberOfBedsException(NON_POSITIVE_NUMBER_OF_BEDS_MSG);
		}
	}

}
