package by.tc.task05.service;

import java.util.Optional;

import by.tc.task05.entity.*;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.http.Part;

/**
 * Interface of service responsible for actions with rooms
 */
public interface RoomService {
	/**
	 * Gets filtered list of {@link ExtendedRoom} from provided page
	 *
	 * @param filter
	 * 		room filter
	 * @param pageInfo
	 * 		information about page
	 * @return list of {@link ExtendedRoom}
	 * @throws ServiceException
	 * @see ListPart
	 */
	ListPart<ExtendedRoom> getViewsByFilter(RoomSearchServiceFilter filter,
	                                        PageInformation pageInfo)
			throws ServiceException;

	/**
	 * Determines whether hotel that contains specified room is administered  by
	 * user, or not
	 *
	 * @param userId
	 * 		user's id
	 * @param roomId
	 * 		room's id
	 * @return true if user is an administrator, false otherwise
	 * @throws ServiceException
	 */
	boolean isRoomAdministrator(int userId, int roomId)
			throws ServiceException;

	/**
	 * Gets room by id
	 *
	 * @param roomId
	 * 		room's id
	 * @return room object
	 * @throws ServiceException
	 */
	Room getById(int roomId) throws ServiceException;

	/**
	 * Gets list of {@link ExtendedRoom} filtered by hotel from provided page
	 *
	 * @param hotelId
	 * 		hotel's id
	 * @param page
	 * 		information about page
	 * @return list of {@link ExtendedRoom}
	 * @throws ServiceException
	 */
	ListPart<Room> getByHotel(int hotelId, PageInformation page)
			throws ServiceException;

	/**
	 * Adds room
	 *
	 * @param userId
	 * 		id of user that requests room addition
	 * @param room
	 * 		room object
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void add(int userId, Room room, String password)
			throws ServiceException;

	/**
	 * Changes information about room (except room icon)
	 *
	 * @param userId
	 * 		id of user that requests room changes
	 * @param room
	 * 		room changes
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void change(int userId, Room room, String password)
			throws ServiceException;

	/**
	 * Sets icon of room
	 *
	 * @param userId
	 * 		id of user that requests room changes
	 * @param roomId
	 * 		id of room which icon is requested to be set
	 * @param icon
	 * 		image from html form
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void setIcon(int userId, int roomId, Part icon, String password)
			throws ServiceException;

	/**
	 * Removes room
	 *
	 * @param userId
	 * 		id of user that requests room changes
	 * @param roomId
	 * 		id of room which is requested to be removed
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void remove(int userId, int roomId, String password)
			throws ServiceException;

	/**
	 * Gets additional features of room, if they're exist
	 *
	 * @param roomId
	 * 		room's id
	 * @return {@link Optional} that contains room features, if they're exist
	 * @throws ServiceException
	 */
	Optional<RoomFeature> getRoomFeatureById(int roomId)
			throws ServiceException;

	/**
	 * Adds additional features to room
	 *
	 * @param userId
	 * 		id of user that requests features' additions
	 * @param feature
	 * 		additional features
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void addFeature(int userId, RoomFeature feature, String password)
			throws ServiceException;

	/**
	 * Changes additional features of room
	 *
	 * @param userId
	 * 		id of user that requests features' changes
	 * @param feature
	 * 		features' changes
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void changeFeature(int userId, RoomFeature feature, String password)
			throws ServiceException;

	/**
	 * Removes additional features of room
	 *
	 * @param userId
	 * 		id of user that requests features' removal
	 * @param roomId
	 * 		room's id
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void removeFeature(int userId, int roomId, String password)
			throws ServiceException;
}
