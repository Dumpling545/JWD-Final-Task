package by.tc.task05.service.validator;

import by.tc.task05.entity.Room;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.exception.ServiceException;

/**
 * Interface that contains methods validating input associated with room's
 * actions
 */
public interface RoomValidator {
	void validateFilter(RoomSearchServiceFilter filter)
			throws ServiceException;

	void validateRoom(Room room)
			throws ServiceException;
}
