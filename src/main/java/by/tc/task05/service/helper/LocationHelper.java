package by.tc.task05.service.helper;

import by.tc.task05.entity.Location;
import by.tc.task05.service.ServiceException;

public interface LocationHelper {
    Location locate(String searchQuery) throws ServiceException;
}
