package by.tc.task05.service;

import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.HotelForm;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.User;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.http.Part;

/**
 * Interface of service responsible for actions with hotels and hotel
 * administrators
 */
public interface HotelService {
	/**
	 * Determines whether specified hotel is administered by user, or not
	 *
	 * @param userId
	 * 		user's id
	 * @param hotelId
	 * 		hotel's id
	 * @return true if user is an administrator, false otherwise
	 * @throws ServiceException
	 */
	boolean isHotelAdministrator(int userId, int hotelId)
			throws ServiceException;

	/**
	 * Get hotels that are administered by specified user
	 *
	 * @param userId
	 * 		user's id
	 * @param page
	 * 		information about page
	 * @return list of hotels
	 * @throws ServiceException
	 */
	ListPart<Hotel> getAdministeredBy(int userId, PageInformation page)
			throws ServiceException;

	/**
	 * Gets hotel by provided id
	 *
	 * @param hotelId
	 * 		hotel's id
	 * @return hotel object
	 * @throws ServiceException
	 */
	Hotel getById(int hotelId) throws ServiceException;

	/**
	 * Creates hotel and adds creator of hotel as administrator
	 *
	 * @param userId
	 * 		creator's id
	 * @param hotelForm
	 * 		form with information needed to create hotel
	 * @throws ServiceException
	 */
	void addWithAdministrator(int userId, HotelForm hotelForm)
			throws ServiceException;

	/**
	 * Changes hotel information (except of hotel's icon)
	 *
	 * @param userId
	 * 		id of user that requested hotel's changes
	 * @param hotelForm
	 * 		hotel's changes
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void change(int userId, HotelForm hotelForm, String password)
			throws ServiceException;

	/**
	 * Sets hotel's icon
	 *
	 * @param userId
	 * 		id of user that requested icon to be set
	 * @param hotelId
	 * 		hotel's id
	 * @param icon
	 * 		image from hotel form
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void setIcon(int userId, int hotelId, Part icon, String password)
			throws ServiceException;

	/**
	 * Removes hotel
	 *
	 * @param userId
	 * 		id of user that requested hotel removal
	 * @param hotelId
	 * 		hotel's id
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void remove(int userId, int hotelId, String password)
			throws ServiceException;

	/**
	 * Adds hotel administrator, specified by email
	 *
	 * @param userId
	 * 		id of user that requested administrator addition
	 * @param email
	 * 		email of user to be added as hotel administrator
	 * @param hotelId
	 * 		hotel's id
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void addAdministratorByEmail(int userId, String email, int hotelId,
	                             String password)
			throws ServiceException;

	/**
	 * Removes administrator from hotel
	 *
	 * @param userId
	 * 		id of user that requested administrator removal
	 * @param adminId
	 * 		id of user that requested to be deprived of administrator rights at the
	 * 		hotel
	 * @param hotelId
	 * 		hotel's id
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void removeAdministrator(int userId, int adminId, int hotelId,
	                         String password) throws ServiceException;

	/**
	 * Gets administrators of the particular hotel
	 *
	 * @param userId
	 * 		id of user that requested list of hotel administrators
	 * @param hotelId
	 * 		hotel's id
	 * @param page
	 * 		information about page
	 * @return list of administrators
	 * @throws ServiceException
	 */
	ListPart<User> getAdministratorsByHotel(int userId, int hotelId,
	                                        PageInformation page)
			throws ServiceException;
}
