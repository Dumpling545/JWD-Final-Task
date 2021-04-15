package by.tc.task05.dao;

import by.tc.task05.dao.impl.SQLHotelDAO;
import by.tc.task05.dao.impl.SQLReservationDAO;
import by.tc.task05.dao.impl.SQLRoomDAO;
import by.tc.task05.dao.impl.SQLUserDAO;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();	
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final RoomDAO roomDAO = new SQLRoomDAO();
	private final HotelDAO hotelDAO = new SQLHotelDAO();
	private final ReservationDAO reservationDAO = new SQLReservationDAO();
	
	private DAOProvider() {}
	
	public static DAOProvider getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public RoomDAO getRoomDAO() {
		return roomDAO;
	}
	public HotelDAO getHotelDAO() {
		return hotelDAO;
	}
	public ReservationDAO getReservationDAO(){return reservationDAO;}
}
