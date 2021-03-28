package by.tc.task05.service;

import java.util.Optional;
import by.tc.task05.entity.User;
import by.tc.task05.entity.UserInfo;
import by.tc.task05.service.exception.ServiceException;

public interface UserService {
	Optional<User> authorization(String email, String rawPassword) throws ServiceException;
	void registration(User user) throws ServiceException;
	User getById(int id) throws ServiceException;
	void changeUserInfo(UserInfo userInfo) throws ServiceException;
	void changeEmail(int id, String email, String password) throws ServiceException;
	void changePassword(int id, String newPassword, String oldPassword) throws ServiceException;
	void remove(int id, String password) throws ServiceException;
}
