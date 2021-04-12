package by.tc.task05.service.helper;

import by.tc.task05.service.helper.impl.LocationHelperImpl;

public class HelperProvider {
    private static final HelperProvider instance = new HelperProvider();

    private HelperProvider() {
    }

    private final LocationHelper locationHelper = new LocationHelperImpl();

    public static HelperProvider getInstance() {
        return instance;
    }

    public LocationHelper getLocationHelper() {
        return locationHelper;
    }
}
