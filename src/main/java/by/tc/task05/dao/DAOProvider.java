package by.tc.task05.dao;

import by.tc.task05.dao.impl.SQLRoomDAO;
import by.tc.task05.dao.impl.SQLUserDAO;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();	
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final RoomDAO roomDAO = new SQLRoomDAO();
	
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
}
