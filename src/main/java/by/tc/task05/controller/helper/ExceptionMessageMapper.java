package by.tc.task05.controller.helper;

import by.tc.task05.controller.command.Command;
import by.tc.task05.service.exception.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionMessageMapper {
    private static final String CREDENTIALS_ERROR = "credentialsError";
    private static final String EMPTY_LOCATION_ERROR = "emptyLocationError";
    private static final String PRICE_RANGE_ERROR = "priceRangeError";
    private static final String DATE_RANGE_ERROR = "dateRangeError";
    private static final String RATING_RANGE_ERROR = "ratingRangeError";
    private static final String LOCATION_SERVICES_ERROR =
            "locationServicesError";
    private static final String NUMBER_OF_BEDS_ERROR = "numberOfBedsError";
    private static final String INVALID_PAGE_ERROR = "pageError";
    private static final String INVALID_PAGE_SIZE_ERROR = "pageSizeError";
    private static final String NO_SUCH_USER_ERROR = "noSuchUserError";
    private static final String EMPTY_NAME_ERROR = "emptyNameError";
    private static final String UNAUTHORIZED_ACTION_ERROR = "unauthorizedError";
    private static final String INVALID_PASSWORD_REPEAT_ERROR =
            "passwordRepeatError";
    private static final String INVALID_IMAGE_TYPE_ERROR = "imageTypeError";
    private static final String NO_IMAGE_ERROR = "noImageError";
    private static final String NO_SUCH_HOTEL_ERROR = "noSuchHotelError";
    private static final String NO_SUCH_ROOM_ERROR = "noSuchRoomError";
    private static final String INVALID_IBAN_ERROR = "invalidIBANError";
    private static final String INVALID_LOCATION_ERROR = "invalidLocationError";
    private static final String EMPTY_INANIMATE_NAME_ERROR =
            "emptyInanimateNameError";
    private static final String ALREADY_ADMIN_ERROR = "alreadyAdminError";
    private static final String LAST_ADMIN_ERROR = "lastAdminError";
    private static final String SERVER_ERROR = "serverError";
    private static final String OCCUPIED_RANGE_ERROR = "occupiedRangeError";
    private static final String INVALID_ARCHIVE_REASON_ERROR =
            "invalidArchiveReasonError";
    private static final String ADMIN_DELETION_ERROR =
            "administratorDeletionError";
    private static final String ROOM_HOTEL_DELETION_ERROR =
            "roomHotelDeletionError";

    public static String getKey(Command command, ServiceException ex) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Something wrong", ex);
        if (ex instanceof CredentialValidationException) {
            return CREDENTIALS_ERROR;
        } else if (ex instanceof EmptyLocationException) {
            return EMPTY_LOCATION_ERROR;
        } else if (ex instanceof InvalidPriceRangeException) {
            return PRICE_RANGE_ERROR;
        } else if (ex instanceof InvalidDateRangeException) {
            return DATE_RANGE_ERROR;
        } else if (ex instanceof InvalidRatingRangeException) {
            return RATING_RANGE_ERROR;
        } else if (ex instanceof LocationAPIException) {
            return LOCATION_SERVICES_ERROR;
        } else if (ex instanceof NumberOfBedsException) {
            return NUMBER_OF_BEDS_ERROR;
        } else if (ex instanceof InvalidPageException) {
            return INVALID_PAGE_ERROR;
        } else if (ex instanceof InvalidPageSizeException) {
            return INVALID_PAGE_SIZE_ERROR;
        } else if (ex instanceof InvalidUserException) {
            return NO_SUCH_USER_ERROR;
        } else if (ex instanceof EmptyNameException) {
            return EMPTY_NAME_ERROR;
        } else if (ex instanceof UnauthorizedActionException) {
            return UNAUTHORIZED_ACTION_ERROR;
        } else if (ex instanceof InvalidPasswordRepeatingException) {
            return INVALID_PASSWORD_REPEAT_ERROR;
        } else if (ex instanceof InvalidImageTypeException) {
            return INVALID_IMAGE_TYPE_ERROR;
        } else if (ex instanceof NoImageException) {
            return NO_IMAGE_ERROR;
        } else if (ex instanceof NoSuchHotelException) {
            return NO_SUCH_HOTEL_ERROR;
        } else if (ex instanceof InvalidIBANException) {
            return INVALID_IBAN_ERROR;
        } else if (ex instanceof InvalidLocationException) {
            return INVALID_LOCATION_ERROR;
        } else if (ex instanceof EmptyInanimateNameException) {
            return EMPTY_INANIMATE_NAME_ERROR;
        } else if (ex instanceof AlreadyAdminException) {
            return ALREADY_ADMIN_ERROR;
        } else if (ex instanceof LastAdministratorRemovalException) {
            return LAST_ADMIN_ERROR;
        } else if (ex instanceof NoSuchRoomException) {
            return NO_SUCH_ROOM_ERROR;
        } else if (ex instanceof OccupiedDateRangeException) {
            return OCCUPIED_RANGE_ERROR;
        } else if (ex instanceof InvalidArchivationReasonException) {
            return INVALID_ARCHIVE_REASON_ERROR;
        } else if (ex instanceof AdministratorAccountDeletionException) {
            return ADMIN_DELETION_ERROR;
        } else if (ex instanceof RoomOrHotelWithActiveReservationsDeletionException) {
            return ROOM_HOTEL_DELETION_ERROR;
        } else {
            return SERVER_ERROR;
        }
    }

}
