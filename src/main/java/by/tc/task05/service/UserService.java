package by.tc.task05.service;

import java.util.Optional;

import by.tc.task05.entity.User;
import by.tc.task05.entity.UserInfo;
import by.tc.task05.entity.UserRegistrationForm;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.http.Part;

/**
 * Interface of service responsible for actions with users
 */
public interface UserService {
	/**
	 * Determines whether provided raw password belongs to user specified by
	 * given id, or not
	 *
	 * @param userId
	 * 		id of user
	 * @param rawPassword
	 * 		unhashed password
	 * @return true if password matched to user, false otherwise
	 * @throws ServiceException
	 */
	boolean isPasswordMatched(int userId, String rawPassword)
			throws ServiceException;

	/**
	 * Returns {@link User} object if user with provided credentials exists
	 *
	 * @param email
	 * 		user's email
	 * @param rawPassword
	 * 		user's unhashed password
	 * @return {@link Optional} that contains user if user with provided
	 * credentials exists
	 * @throws ServiceException
	 */
	Optional<User> authorization(String email, String rawPassword)
			throws ServiceException;

	/**
	 * Creates user using information from provided {@link UserRegistrationForm}
	 * object
	 *
	 * @param form
	 * 		object with information needed to register user
	 * @throws ServiceException
	 */
	void registration(UserRegistrationForm form) throws ServiceException;

	/**
	 * Returns user by provided id
	 *
	 * @param id
	 * 		user's id
	 * @return user object
	 * @throws ServiceException
	 */
	User getById(int id) throws ServiceException;

	/**
	 * Changes 'unimportant' information about user: first and last name
	 *
	 * @param userInfo
	 * 		object that contains changes
	 * @throws ServiceException
	 */
	void changeUserInfo(UserInfo userInfo) throws ServiceException;

	/**
	 * Changes email of user
	 *
	 * @param id
	 * 		user's id
	 * @param email
	 * 		new email
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void changeEmail(int id, String email, String password)
			throws ServiceException;

	/**
	 * Change user's password
	 *
	 * @param id
	 * 		user's id
	 * @param newPassword
	 * 		new password
	 * @param repeatNewPassword
	 * 		confirmation that user does not mistyped new password
	 * @param oldPassword
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void changePassword(int id, String newPassword, String repeatNewPassword,
	                    String oldPassword) throws ServiceException;

	/**
	 * Removes user
	 *
	 * @param id
	 * 		user's id
	 * @param password
	 * 		user password to confirm changes
	 * @throws ServiceException
	 */
	void remove(int id, String password) throws ServiceException;

	/**
	 * Sets user's avatar
	 * @param id user's id
	 * @param imageFile image provided by html form
	 * @throws ServiceException
	 */
	void setAvatar(int id, Part imageFile) throws ServiceException;
}
