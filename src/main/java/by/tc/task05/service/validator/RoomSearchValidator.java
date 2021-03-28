package by.tc.task05.service.validator;

import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.exception.ServiceException;

public interface RoomSearchValidator {
    public void validateFilter(RoomSearchServiceFilter filter) throws ServiceException;
}
