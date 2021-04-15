package by.tc.task05.service.helper.impl;

import by.tc.task05.service.exception.ServiceException;

import java.util.Random;

public class PaymentHelperStub implements PaymentHelper {
    @Override
    public String getTokenForPayment(String IBANAccount, double amount)
            throws ServiceException {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 20;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
