package by.tc.task05.service;

import by.tc.task05.entity.User;

public interface UserService {
	User authorization(String email, String rawPassword) throws ServiceException;
	void registration(User user) throws ServiceException;
}
