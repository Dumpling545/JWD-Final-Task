package by.tc.task05.service.helper;

import by.tc.task05.service.helper.impl.LocationHelperImpl;
import by.tc.task05.service.helper.impl.PaymentHelper;
import by.tc.task05.service.helper.impl.PaymentHelperStub;

public class HelperProvider {
    private static final HelperProvider instance = new HelperProvider();

    private HelperProvider() {
    }

    private final LocationHelper locationHelper = new LocationHelperImpl();
    private final PaymentHelper paymentHelper = new PaymentHelperStub();
    public static HelperProvider getInstance() {
        return instance;
    }

    public LocationHelper getLocationHelper() {
        return locationHelper;
    }
    public PaymentHelper getPaymentHelper(){return  paymentHelper; }
}
