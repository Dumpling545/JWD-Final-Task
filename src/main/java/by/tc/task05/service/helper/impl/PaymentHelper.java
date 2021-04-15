package by.tc.task05.service.helper.impl;

import by.tc.task05.service.exception.ServiceException;

public interface PaymentHelper {
    public String getTokenForPayment(String IBANAccount, double amount) throws
            ServiceException;
}
