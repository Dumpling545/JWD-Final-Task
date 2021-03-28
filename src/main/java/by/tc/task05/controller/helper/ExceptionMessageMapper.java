package by.tc.task05.controller.helper;

import by.tc.task05.controller.command.Command;
import by.tc.task05.service.exception.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionMessageMapper {

    public static String getKey(Command command, ServiceException ex) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "Something wrong", ex);
        if (ex instanceof CredentialValidationException) {
            return "credentialsError";
        } else if (ex instanceof EmptyLocationException) {
            return "emptyLocationError";
        } else if (ex instanceof InvalidPriceRangeException) {
            return "priceRangeError";
        } else if (ex instanceof InvalidDateRangeException) {
            return "dateRangeError";
        } else if (ex instanceof InvalidRatingRangeException) {
            return "ratingRangeError";
        } else if (ex instanceof LocationAPIException) {
            return "locationServicesError";
        } else if (ex instanceof NumberOfBedsException) {
            return "numberOfBedsError";
        } else if (ex instanceof InvalidPageException) {
            return "pageError";
        }else if (ex instanceof InvalidPageSizeException) {
            return "pageSizeError";
        }else if (ex instanceof InvalidUserException) {
            return "noSuchUserError";
        }else if (ex instanceof EmptyNameException) {
            return "emptyNameError";
        }else if(ex instanceof  UnauthorizedActionException){
            return "unauthorizedError";
        }else {
            return "serverError";
        }
    }

}
