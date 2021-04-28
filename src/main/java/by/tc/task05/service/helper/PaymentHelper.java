package by.tc.task05.service.helper;

import by.tc.task05.service.exception.ServiceException;

/**
 * Interface that provides methods for assisting payments
 */
public interface PaymentHelper {
	String getTokenForPayment(String IBANAccount, double amount) throws
			ServiceException;
}
