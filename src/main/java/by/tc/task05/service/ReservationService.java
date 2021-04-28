package by.tc.task05.service;

import by.tc.task05.entity.ExtendedReservation;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.Reservation;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;

import java.time.LocalDate;

/**
 * Interface of service responsible for actions with reservations
 */
public interface ReservationService {
	/**
	 * Adds reservation
	 *
	 * @param reservation
	 * 		reservation object
	 * @throws ServiceException
	 */
	void add(Reservation reservation) throws ServiceException;

	/**
	 * Changes reservation status to ACCEPTED
	 *
	 * @param userId
	 * 		id of administrator of hotel that contains room which have been
	 * 		reserved in reservation
	 * @param reservationId
	 * 		id of reservation
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void acceptReservation(int userId, int reservationId,
	                       String password) throws ServiceException;

	/**
	 * Deletes reservation from active reservations and moves it to archive
	 *
	 * @param userId
	 * 		id of user requested to archive reservation (depending on
	 * 		archivationReason, could be both client or hotel administrator)
	 * @param reservationId
	 * 		id of reservation
	 * @param archivationReason
	 * 		integer value representing reason why reservation need to be archived
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void archiveReservation(int userId, int reservationId,
	                        int archivationReason, String password)
			throws ServiceException;

	/**
	 * Gets reservations made by user
	 *
	 * @param userId
	 * 		user's id
	 * @param page
	 * 		information about page
	 * @param archived
	 * 		flag that indicates whether archived or active reservations should be
	 * 		returned
	 * @return list of reservations
	 * @throws ServiceException
	 */
	ListPart<ExtendedReservation> getByUser(int userId,
	                                        PageInformation page,
	                                        boolean archived)
			throws ServiceException;

	/**
	 * Gets reservations that contained rooms assigned to particular hotel
	 *
	 * @param userId
	 * 		user's id
	 * @param hotelId
	 * 		hotel's id
	 * @param page
	 * 		information about page
	 * @param archived
	 * 		flag that indicates whether archived or active reservations should be
	 * 		returned
	 * @return list of reservations
	 * @throws ServiceException
	 */
	ListPart<ExtendedReservation> getByHotel(int userId, int hotelId,
	                                         PageInformation page,
	                                         boolean archived)
			throws ServiceException;

	/**
	 * Gets reservations that contained particular room
	 *
	 * @param userId
	 * 		user's id
	 * @param roomId
	 * 		room's id
	 * @param page
	 * 		information about page
	 * @param archived
	 * 		flag that indicates whether archived or active reservations should be
	 * 		returned
	 * @return list of reservations
	 * @throws ServiceException
	 */
	ListPart<ExtendedReservation> getByRoom(int userId, int roomId,
	                                        PageInformation page,
	                                        boolean archived)
			throws ServiceException;

	/**
	 * Determines whether provided date range is intersected by any other
	 * reservation of this room, or not
	 *
	 * @param roomId
	 * 		room's id
	 * @param checkIn
	 * 		date range start
	 * @param checkOut
	 * 		date range end
	 * @return true if intersected, false otherwise
	 * @throws ServiceException
	 */
	boolean areDatesOccupied(int roomId, LocalDate checkIn,
	                         LocalDate checkOut) throws ServiceException;
}
