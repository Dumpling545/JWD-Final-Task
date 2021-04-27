package by.tc.task05.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.tc.task05.DatabaseExtension;
import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.entity.User;
import by.tc.task05.entity.UserInfo;
import by.tc.task05.entity.UserRegistrationForm;
import by.tc.task05.service.exception.*;
import by.tc.task05.utils.DatabaseHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.tc.task05.service.validator.impl.UserValidatorImpl.INVALID_EMAIL_MESSAGE;
import static by.tc.task05.service.validator.impl.UserValidatorImpl.INVALID_PASSWORD_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({DatabaseExtension.class})
public class UserServiceTest {
	private static final String CORRECT_EMAIL = "ivan1@gmail.com";
	private static final String CORRECT_EMAIL_2 = "ivan2@gmail.com";
	private static final String CORRECT_EMAIL_3 = "ivan3@gmail.com";
	private static final String CORRECT_FIRST_NAME = "Ivan";
	private static final String CORRECT_LAST_NAME = "Ivanov";
	private static final String CORRECT_FIRST_NAME_2 = "Sergei";
	private static final String CORRECT_LAST_NAME_2 = "Sergeev";
	//correct password contains >=2 ucase latters, >=2 lcase letters, >=2 digits, >=2 special symbols from !,@,#,$,%,^
	private static final String CORRECT_PASSWORD = "12qw!@QW";
	private static final String CORRECT_PASSWORD_2 = "12qw!@QWa";
	private static final String INCORRECT_EMAIL = "ivan1@";
	private static final String TOO_SHORT_PASSWORD = "12qw!@Q";
	private static final String INCORRECT_PASSWORD = "12qw!@Qw";
	private static final String SQL_GET_BY_EMAIL =
			"SELECT * FROM users WHERE email=?";
	private static final String SQL_FIRST_NAME_COLUMN = "first_name";
	private static final String SQL_LAST_NAME_COLUMN = "last_name";
	//value of BIG_OFFSET should be greater than any possible existing id in `users` table in test database
	private static final int BIG_OFFSET = 100000000;
	private static final String SQL_PASSWORD_COLUMN = "password_hash";
	private int userId = -1;
	private User user = new User();
	private UserInfo userInfo = new UserInfo();
	private UserRegistrationForm registrationForm = new UserRegistrationForm();
	private UserService userService;
	private Connection connection = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	void assertPassword(String raw, String hash) {
		assertTrue(BCrypt.verifyer().verify(raw.toCharArray(),
				hash).verified);
	}

	void assertCorrectUser(User user) {
		if (userId != -1) {
			assertEquals(user.getId(), userId);
		}
		assertEquals(user.getEmail(), CORRECT_EMAIL);
		assertEquals(user.getFirstName(), CORRECT_FIRST_NAME);
		assertEquals(user.getLastName(), CORRECT_LAST_NAME);
		assertPassword(CORRECT_PASSWORD, user.getPasswordHash());
	}

	@BeforeAll
	void init() {
		userService = ServiceProvider.getInstance().getUserService();
	}

	@Test
	@Order(1)
	void registerUserPositiveTest() throws ServiceException {
		registrationForm.setEmail(CORRECT_EMAIL);
		registrationForm.setFirstName(CORRECT_FIRST_NAME);
		registrationForm.setLastName(CORRECT_LAST_NAME);
		registrationForm.setPassword(CORRECT_PASSWORD);
		registrationForm.setRepeatPassword(CORRECT_PASSWORD);
		userService.registration(registrationForm);
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_BY_EMAIL);
			preparedStatement.setString(1, CORRECT_EMAIL);
			resultSet = preparedStatement.executeQuery();
			assertTrue(resultSet.next());
		} catch (SQLException ex) {
			Assertions.fail(ex);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
	}

	@Test
	@Order(2)
	void registerUserIncorrectEmailTest() {
		registrationForm.setEmail(INCORRECT_EMAIL);
		registrationForm.setFirstName(CORRECT_FIRST_NAME);
		registrationForm.setLastName(CORRECT_LAST_NAME);
		registrationForm.setPassword(CORRECT_PASSWORD);
		registrationForm.setRepeatPassword(CORRECT_PASSWORD);
		Exception exception = assertThrows(CredentialValidationException.class,
				() -> userService.registration(registrationForm));
		assertEquals(exception.getMessage(), INVALID_EMAIL_MESSAGE);
	}

	@Test
	@Order(3)
	void registerUserEmailAlreadyRegisteredTest() {
		registrationForm.setEmail(CORRECT_EMAIL);
		registrationForm.setFirstName(CORRECT_FIRST_NAME);
		registrationForm.setLastName(CORRECT_LAST_NAME);
		registrationForm.setPassword(CORRECT_PASSWORD);
		registrationForm.setRepeatPassword(CORRECT_PASSWORD);
		assertThrows(EmailAlreadyRegisteredException.class,
				() -> userService.registration(registrationForm));
	}

	@Test
	@Order(4)
	void registerUserEmptyFirstNameTest() {
		registrationForm.setEmail(CORRECT_EMAIL_2);
		registrationForm.setFirstName("");
		registrationForm.setLastName(CORRECT_LAST_NAME);
		registrationForm.setPassword(CORRECT_PASSWORD);
		registrationForm.setRepeatPassword(CORRECT_PASSWORD);
		assertThrows(EmptyNameException.class,
				() -> userService.registration(registrationForm));
	}

	@Test
	@Order(5)
	void registerUserEmptyLastNameTest() {
		registrationForm.setEmail(CORRECT_EMAIL_2);
		registrationForm.setFirstName(CORRECT_FIRST_NAME);
		registrationForm.setLastName("");
		registrationForm.setPassword(CORRECT_PASSWORD);
		registrationForm.setRepeatPassword(CORRECT_PASSWORD);
		assertThrows(EmptyNameException.class,
				() -> userService.registration(registrationForm));
	}

	@Test
	@Order(6)
	void registerUserTooShortPasswordTest() {
		registrationForm.setEmail(CORRECT_EMAIL_2);
		registrationForm.setFirstName(CORRECT_FIRST_NAME);
		registrationForm.setLastName(CORRECT_LAST_NAME);
		registrationForm.setPassword(TOO_SHORT_PASSWORD);
		registrationForm.setRepeatPassword(TOO_SHORT_PASSWORD);
		Exception exception = assertThrows(CredentialValidationException.class,
				() -> userService.registration(registrationForm));
		assertEquals(exception.getMessage(), INVALID_PASSWORD_MESSAGE);
	}

	@Test
	@Order(7)
	void registerUserIncorrectPasswordTest() {
		registrationForm.setEmail(CORRECT_EMAIL_2);
		registrationForm.setFirstName(CORRECT_FIRST_NAME);
		registrationForm.setLastName(CORRECT_LAST_NAME);
		registrationForm.setPassword(INCORRECT_PASSWORD);
		registrationForm.setRepeatPassword(INCORRECT_PASSWORD);
		Exception exception = assertThrows(CredentialValidationException.class,
				() -> userService.registration(registrationForm));
		assertEquals(exception.getMessage(), INVALID_PASSWORD_MESSAGE);
	}

	@Test
	@Order(8)
	void registerUserInvalidPasswordRepetitionTest() {
		registrationForm.setEmail(CORRECT_EMAIL_2);
		registrationForm.setFirstName(CORRECT_FIRST_NAME);
		registrationForm.setLastName(CORRECT_LAST_NAME);
		registrationForm.setPassword(CORRECT_PASSWORD_2);
		registrationForm.setRepeatPassword(CORRECT_PASSWORD);
		Exception exception =
				assertThrows(InvalidPasswordRepeatingException.class,
						() -> userService.registration(registrationForm));
	}

	@Test
	@Order(9)
	void authorizationPositiveTest() throws ServiceException {
		Optional<User> optionalUser =
				userService.authorization(CORRECT_EMAIL, CORRECT_PASSWORD);
		assertTrue(optionalUser.isPresent());
		User user = optionalUser.get();
		assertEquals(user.getEmail(), CORRECT_EMAIL);
		assertEquals(user.getFirstName(), CORRECT_FIRST_NAME);
		assertEquals(user.getLastName(), CORRECT_LAST_NAME);
		assertPassword(CORRECT_PASSWORD, user.getPasswordHash());
		userId = user.getId();
	}

	@Test
	@Order(10)
	void authorizationNegativeTest() throws ServiceException {
		Optional<User> optionalUser =
				userService.authorization(CORRECT_EMAIL_2, CORRECT_PASSWORD);
		assertFalse(optionalUser.isPresent());
	}

	@Test
	@Order(11)
	void isPasswordMatchedPositiveTest() throws ServiceException {
		assertTrue(userService.isPasswordMatched(userId, CORRECT_PASSWORD));
	}

	@Test
	@Order(12)
	void isPasswordMatchedNegativeTest() throws ServiceException {
		assertFalse(userService.isPasswordMatched(userId, CORRECT_PASSWORD_2));
	}

	@Test
	@Order(13)
	void getByIdPositiveTest() throws ServiceException {
		User user = userService.getById(userId);
		assertCorrectUser(user);
	}

	@Test
	@Order(14)
	void getByIdNegativeTest() {
		assertThrows(InvalidUserException.class,
				() -> userService.getById(userId + BIG_OFFSET));
	}

	@Test
	@Order(15)
	void changeUserInfoPositiveTest() throws ServiceException {
		userInfo.setId(userId);
		userInfo.setFirstName(CORRECT_FIRST_NAME_2);
		userInfo.setLastName(CORRECT_LAST_NAME_2);
		userService.changeUserInfo(userInfo);
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_BY_EMAIL);
			preparedStatement.setString(1, CORRECT_EMAIL);
			resultSet = preparedStatement.executeQuery();
			assertTrue(resultSet.next());
			assertEquals(resultSet.getString(SQL_FIRST_NAME_COLUMN),
					CORRECT_FIRST_NAME_2);
			assertEquals(resultSet.getString(SQL_LAST_NAME_COLUMN),
					CORRECT_LAST_NAME_2);
		} catch (SQLException ex) {
			Assertions.fail(ex);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
	}

	@Test
	@Order(16)
	void changeUserInfoEmptyFirstNameTest() throws ServiceException {
		userInfo.setId(userId);
		userInfo.setFirstName("");
		userInfo.setLastName(CORRECT_LAST_NAME_2);
		assertThrows(EmptyNameException.class,
				() -> userService.changeUserInfo(userInfo));
	}

	@Test
	@Order(17)
	void changeUserInfoEmptyLastNameTest() throws ServiceException {
		userInfo.setId(userId);
		userInfo.setFirstName(CORRECT_FIRST_NAME_2);
		userInfo.setLastName("");
		assertThrows(EmptyNameException.class,
				() -> userService.changeUserInfo(userInfo));
	}

	@Test
	@Order(18)
	void changeEmailPositiveTest() throws ServiceException {
		userService.changeEmail(userId, CORRECT_EMAIL_2, CORRECT_PASSWORD);
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_BY_EMAIL);
			preparedStatement.setString(1, CORRECT_EMAIL_2);
			resultSet = preparedStatement.executeQuery();
			assertTrue(resultSet.next());
		} catch (SQLException ex) {
			Assertions.fail(ex);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
	}

	@Test
	@Order(19)
	void changeEmailNonMatchingPasswordTest() {
		assertThrows(UnauthorizedActionException.class, () -> userService
				.changeEmail(userId, CORRECT_EMAIL, CORRECT_PASSWORD_2));
	}

	@Test
	@Order(20)
	void registerAnotherUserPositiveTest()
			throws ServiceException
	{
		registrationForm.setEmail(CORRECT_EMAIL_3);
		registrationForm.setFirstName(CORRECT_FIRST_NAME);
		registrationForm.setLastName(CORRECT_LAST_NAME);
		registrationForm.setPassword(CORRECT_PASSWORD);
		registrationForm.setRepeatPassword(CORRECT_PASSWORD);
		userService.registration(registrationForm);
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_BY_EMAIL);
			preparedStatement.setString(1, CORRECT_EMAIL_3);
			resultSet = preparedStatement.executeQuery();
			assertTrue(resultSet.next());
		} catch (SQLException ex) {
			Assertions.fail(ex);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
	}

	@Test
	@Order(21)
	void changeEmailAlreadyExistingTest() {
		assertThrows(EmailAlreadyRegisteredException.class, () -> userService
				.changeEmail(userId, CORRECT_EMAIL_3, CORRECT_PASSWORD));
	}

	@Test
	@Order(22)
	void changePasswordPositiveTest() throws ServiceException {
		userService.changePassword(userId, CORRECT_PASSWORD_2, CORRECT_PASSWORD_2,
				CORRECT_PASSWORD);
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_BY_EMAIL);
			preparedStatement.setString(1, CORRECT_EMAIL_2);
			resultSet = preparedStatement.executeQuery();
			assertTrue(resultSet.next());
			assertPassword(CORRECT_PASSWORD_2,
					resultSet.getString(SQL_PASSWORD_COLUMN));
		} catch (SQLException ex) {
			Assertions.fail(ex);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}
	}

	@Test
	@Order(23)
	void changePasswordInvalidRepetitionTest() {
		assertThrows(InvalidPasswordRepeatingException.class, () ->
				userService.changePassword(userId, CORRECT_PASSWORD_2,
						CORRECT_PASSWORD,
						CORRECT_PASSWORD_2));
	}

	@Test
	@Order(24)
	void changePasswordIncorrectPasswordTest() {
		Exception exception = assertThrows(CredentialValidationException.class,
				() -> userService.changePassword(userId, INCORRECT_PASSWORD,
						INCORRECT_PASSWORD,
						CORRECT_PASSWORD_2));
		assertEquals(exception.getMessage(), INVALID_PASSWORD_MESSAGE);

	}

	@Test
	@Order(25)
	void changePasswordInvalidUserIdTest() {
		assertThrows(UnauthorizedActionException.class,
				() -> userService
						.changePassword(userId + BIG_OFFSET, CORRECT_PASSWORD,
								CORRECT_PASSWORD,
								CORRECT_PASSWORD_2));

	}

	@Test
	@Order(26)
	void removeUserWrongPasswordTest() {
		assertThrows(UnauthorizedActionException.class,
				() -> userService
						.remove(userId,
								CORRECT_PASSWORD));

	}

	@Test
	@Order(27)
	void removeUserInvalidIdTest() {
		assertThrows(UnauthorizedActionException.class,
				() -> userService
						.remove(userId + BIG_OFFSET,
								CORRECT_PASSWORD_2));

	}

	@Test
	@Order(28)
	void removeUserPositiveTest() throws ServiceException {
		userService.remove(userId, CORRECT_PASSWORD_2);
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_BY_EMAIL);
			preparedStatement.setString(1, CORRECT_EMAIL_2);
			resultSet = preparedStatement.executeQuery();
			assertFalse(resultSet.next());
		} catch (SQLException ex) {
			Assertions.fail(ex);
		} finally {
			DatabaseHelper
					.closeResources(resultSet, preparedStatement, connection);
		}

	}

}
