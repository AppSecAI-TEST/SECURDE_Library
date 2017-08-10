package services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import custom_errors.LockoutException;
import custom_errors.PasswordMismatch;
import db.DBPool;
import models.UnlockedUsers;
import models.User;
import security.EmailUtil;
import security.Security;

public class UserService {

	final static Logger logger = Logger.getLogger(UserService.class);

	public static void addUser(User u) throws SQLException {

		logger.info(u.getUserName() + "attempting to sign up.");

		String sql = "INSERT INTO " + User.TABLE_NAME + " (" + User.COLUMN_IDNUM + ", " + User.COLUMN_EMAIL + ", "
				+ User.COLUMN_FIRSTNAME + ", " + User.COLUMN_MIDDLENAME + ", " + User.COLUMN_LASTNAME + ", "
				+ User.COLUMN_USERNAME + ", " + User.COLUMN_BIRTHDATE + ", " + User.COLUMN_SECRETQUESTION + ", "
				+ User.COLUMN_SECRETANSWER + ", " + User.COLUMN_CREATETIME + ", " + User.COLUMN_LASTLOGIN + ", "
				+ User.COLUMN_ACCESSLEVEL + ", " + User.COLUMN_ATTEMPT + ", " + User.COLUMN_PASS + ") "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		DBPool.getInstance();
		Connection conn = DBPool.getConnection();

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getIdUser());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getFirstName() + "");
			pstmt.setString(4, u.getMiddleName());
			pstmt.setString(5, u.getLastName());
			pstmt.setString(6, u.getUserName());
			java.sql.Date birthdate = new java.sql.Date(u.getBirthdate().getTimeInMillis());
			pstmt.setDate(7, birthdate);
			pstmt.setString(8, u.getSecretQuestion());
			pstmt.setString(9, u.getSecretAnswer());
			java.sql.Date creation = new java.sql.Date(new Date().getTime());
			pstmt.setDate(10, creation);
			java.sql.Date lastlogin = new java.sql.Date(new Date().getTime());
			pstmt.setDate(11, lastlogin);
			pstmt.setInt(12, u.getAccessLevel());
			pstmt.setInt(13, 0);
			pstmt.setString(14, u.getPassword());

			pstmt.executeUpdate();

		} finally {
			try {
				pstmt.close();
				conn.close();
				logger.info(u.getUserName() + "sign up success.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static ArrayList<User> getAllUsers() {
		logger.info("Retrieving all users.");
		ArrayList<User> users = new ArrayList<User>();

		String sql = "Select * from " + User.TABLE_NAME;
		Connection conn = null;
		DBPool.getInstance();
		conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setIdUser(rs.getInt(User.COLUMN_IDNUM));
				u.setEmail(rs.getString(User.COLUMN_EMAIL));
				u.setFirstName(rs.getString(User.COLUMN_FIRSTNAME));
				u.setMiddleName(rs.getString(User.COLUMN_MIDDLENAME));
				u.setLastName(rs.getString(User.COLUMN_LASTNAME));
				u.setAccessLevel(rs.getInt(User.COLUMN_ACCESSLEVEL));

				GregorianCalendar birthdate = new GregorianCalendar();
				birthdate.setTimeInMillis(rs.getDate(User.COLUMN_BIRTHDATE).getTime());
				u.setBirthdate(birthdate);

				GregorianCalendar createtime = new GregorianCalendar();
				createtime.setTimeInMillis(rs.getDate(User.COLUMN_CREATETIME).getTime());
				u.setCreateTime(createtime);

				GregorianCalendar lastlogin = new GregorianCalendar();
				lastlogin.setTimeInMillis(rs.getDate(User.COLUMN_LASTLOGIN).getTime());
				u.setLastLogin(lastlogin);

				u.setSecretQuestion(rs.getString(User.COLUMN_SECRETQUESTION));
				u.setSecretAnswer(rs.getString(User.COLUMN_SECRETANSWER));
				u.setUserName(rs.getString(User.COLUMN_USERNAME));
				u.setPassword(rs.getString(User.COLUMN_PASS));

				users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return users;
	}

	public static ArrayList<User> getAllUsersByStatus(int status) {
		String stat;
		switch (status) {
		case User.STATUS_LOCKED:
			stat = "LOCKED";
			break;
		case User.STATUS_UNLOCKED:
			stat = "UNLOCKED";
			break;
		case User.STATUS_TEMP:
			stat = "TEMP_PASSWORD_SENT";
			break;
		default:
			stat = "UNKNOWN STAT VALUE [ " + status + " ]";

		}

		logger.info("Retrieving users with status : " + stat);
		ArrayList<User> users = new ArrayList<User>();

		String sql = "Select * from " + User.TABLE_NAME + " WHERE " + User.COLUMN_STATUS + " = ?;";
		Connection conn = null;
		DBPool.getInstance();
		conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setIdUser(rs.getInt(User.COLUMN_IDNUM));
				u.setEmail(rs.getString(User.COLUMN_EMAIL));
				u.setFirstName(rs.getString(User.COLUMN_FIRSTNAME));
				u.setMiddleName(rs.getString(User.COLUMN_MIDDLENAME));
				u.setLastName(rs.getString(User.COLUMN_LASTNAME));
				u.setAccessLevel(rs.getInt(User.COLUMN_ACCESSLEVEL));

				GregorianCalendar birthdate = new GregorianCalendar();
				birthdate.setTimeInMillis(rs.getDate(User.COLUMN_BIRTHDATE).getTime());
				u.setBirthdate(birthdate);

				GregorianCalendar createtime = new GregorianCalendar();
				createtime.setTimeInMillis(rs.getDate(User.COLUMN_CREATETIME).getTime());
				u.setCreateTime(createtime);

				GregorianCalendar lastlogin = new GregorianCalendar();
				lastlogin.setTimeInMillis(rs.getDate(User.COLUMN_LASTLOGIN).getTime());
				u.setLastLogin(lastlogin);

				u.setSecretQuestion(rs.getString(User.COLUMN_SECRETQUESTION));
				u.setSecretAnswer(rs.getString(User.COLUMN_SECRETANSWER));
				u.setUserName(rs.getString(User.COLUMN_USERNAME));
				u.setPassword(rs.getString(User.COLUMN_PASS));

				users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public static ArrayList<User> getUsersByID(ArrayList<Integer> idNum) {
		ArrayList<User> users = new ArrayList<User>();

		String sql = "Select * from " + User.TABLE_NAME;

		for (int i = 0; i < idNum.size(); i++) {
			if (i == 0)
				sql += " WHERE ";
			else
				sql += " AND ";

			sql += User.COLUMN_IDNUM + " = " + idNum.get(i);
		}

		Connection conn = null;
		DBPool.getInstance();
		conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setIdUser(rs.getInt(User.COLUMN_IDNUM));
				u.setEmail(rs.getString(User.COLUMN_EMAIL));
				u.setFirstName(rs.getString(User.COLUMN_FIRSTNAME));
				u.setMiddleName(rs.getString(User.COLUMN_MIDDLENAME));
				u.setLastName(rs.getString(User.COLUMN_LASTNAME));
				u.setAccessLevel(rs.getInt(User.COLUMN_ACCESSLEVEL));

				GregorianCalendar birthdate = new GregorianCalendar();
				birthdate.setTimeInMillis(rs.getDate(User.COLUMN_BIRTHDATE).getTime());
				u.setBirthdate(birthdate);

				GregorianCalendar createtime = new GregorianCalendar();
				createtime.setTimeInMillis(rs.getDate(User.COLUMN_CREATETIME).getTime());
				u.setCreateTime(createtime);

				GregorianCalendar lastlogin = new GregorianCalendar();
				lastlogin.setTimeInMillis(rs.getDate(User.COLUMN_LASTLOGIN).getTime());
				u.setLastLogin(lastlogin);

				u.setSecretQuestion(rs.getString(User.COLUMN_SECRETQUESTION));
				u.setSecretAnswer(rs.getString(User.COLUMN_SECRETANSWER));
				u.setUserName(rs.getString(User.COLUMN_USERNAME));
				u.setPassword(rs.getString(User.COLUMN_PASS));

				users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return users;
	}

	public static User getUserByID(int idNum) {

		User u = new User();
		String sql = "Select * from " + User.TABLE_NAME;
		sql += " WHERE ";

		sql += User.COLUMN_IDNUM + " = ?";

		Connection conn = null;
		DBPool.getInstance();
		conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				u.setIdUser(rs.getInt(User.COLUMN_IDNUM));
				u.setEmail(rs.getString(User.COLUMN_EMAIL));
				u.setFirstName(rs.getString(User.COLUMN_FIRSTNAME));
				u.setMiddleName(rs.getString(User.COLUMN_MIDDLENAME));
				u.setLastName(rs.getString(User.COLUMN_LASTNAME));
				u.setAccessLevel(rs.getInt(User.COLUMN_ACCESSLEVEL));

				GregorianCalendar birthdate = new GregorianCalendar();
				birthdate.setTimeInMillis(rs.getDate(User.COLUMN_BIRTHDATE).getTime());
				u.setBirthdate(birthdate);

				GregorianCalendar createtime = new GregorianCalendar();
				createtime.setTimeInMillis(rs.getDate(User.COLUMN_CREATETIME).getTime());
				u.setCreateTime(createtime);

				GregorianCalendar lastlogin = new GregorianCalendar();
				lastlogin.setTimeInMillis(rs.getDate(User.COLUMN_LASTLOGIN).getTime());
				u.setLastLogin(lastlogin);

				u.setSecretQuestion(rs.getString(User.COLUMN_SECRETQUESTION));
				u.setSecretAnswer(rs.getString(User.COLUMN_SECRETANSWER));
				u.setUserName(rs.getString(User.COLUMN_USERNAME));
				u.setPassword(rs.getString(User.COLUMN_PASS));
				u.setStatus(rs.getInt(User.COLUMN_STATUS));
				u.setAttempt(rs.getInt(User.COLUMN_ATTEMPT));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return u;
	}

	public static User getUserByIDandUsername(int id, String username) {
		User u = new User();
		String sql = "Select * from " + User.TABLE_NAME + " WHERE " + User.COLUMN_IDNUM + " = ?" + " AND "
				+ User.COLUMN_USERNAME + " =?;";

		Connection conn = null;
		DBPool.getInstance();
		conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String secret = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, username);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				u.setIdUser(rs.getInt(User.COLUMN_IDNUM));
				u.setEmail(rs.getString(User.COLUMN_EMAIL));
				u.setSecretQuestion(rs.getString(User.COLUMN_SECRETQUESTION));
				u.setSecretAnswer(rs.getString(User.COLUMN_SECRETANSWER));
				u.setUserName(rs.getString(User.COLUMN_USERNAME));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return u;
	}

	public static void updatePassword(String password, String salt, int id) {
		String sql = "UPDATE " + User.TABLE_NAME + " SET " + User.COLUMN_PASS + " =? " + " WHERE " + User.COLUMN_IDNUM
				+ " =?;";
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, password);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void deleteUser(String idNum) {
		User u = getUserByID(Integer.parseInt(idNum));

		if (u == null) {
			u = new User();
		}

		logger.info("Deleting " + u.getAccesString() + " [" + idNum + "] " + u.getUserName());

		String sql = "DELETE FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_IDNUM + " = ?;";

		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idNum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static String getUserNameById(int id) {
		String sql = "SELECT " + User.COLUMN_USERNAME + " FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_IDNUM
				+ " = ?;";
		String name = null;
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				name = rs.getString(User.COLUMN_USERNAME);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return name;

	}

	public static void updateLockedStatus(int id) {
		String sql = "UPDATE " + User.TABLE_NAME + " SET " + User.COLUMN_STATUS + " =? " + " WHERE " + User.COLUMN_IDNUM
				+ " =?;";
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();

		PreparedStatement pstmt = null;

		logger.info("Locking user [" + id + "] " + getUserNameById(id));

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 1);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void updateStatus(int id, int status) {
		String sql = "UPDATE " + User.TABLE_NAME + " SET " + User.COLUMN_STATUS + " =? " + " WHERE " + User.COLUMN_IDNUM
				+ " =" + id + ";";
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		String stat;
		switch (status) {
		case User.STATUS_LOCKED:
			stat = "Locking";
			logger.info(stat + " user [" + id + "] " + getUserNameById(id));
			break;
		case User.STATUS_UNLOCKED:
			stat = "Unlocking";
			logger.info(stat + " user [" + id + "] " + getUserNameById(id));
			break;
		default:
			stat = "UNKNOWN STATUS CHANGE [ " + status + " ] to";
			logger.warn(stat + " user [" + id + "] " + getUserNameById(id));

		}

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			System.out.println("STATUS " + status);
			pstmt.setInt(1, status);

			System.out.println("STATUS2 " + status);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void updateLogIn(int id) {
		String sql = "UPDATE " + User.TABLE_NAME + " SET " + User.COLUMN_LASTLOGIN + " =? " + " , "
				+ User.COLUMN_ATTEMPT + " =? " + " WHERE " + User.COLUMN_IDNUM + " =" + id + ";";
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();

		logger.info("[" + id + "] " + getUserNameById(id) + " successfully logged in.");

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			java.sql.Date lastlogin = new java.sql.Date(new Date().getTime());
			pstmt.setDate(1, lastlogin);
			pstmt.setInt(2, 0);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void updateAttempt(int id) {
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		int attempt = 0;
		PreparedStatement pstmt = null;
		User u = getUserByID(id);
		attempt = u.getAttempt();

		if (attempt == 3) {
			updateStatus(id, User.STATUS_LOCKED);
		}

		try {
			attempt++;

			logger.warn("[" + id + "] " + getUserNameById(id) + " Failed login attempt. Attempt #" + attempt);

			String sel = "UPDATE " + User.TABLE_NAME + " SET " + User.COLUMN_ATTEMPT + " =? " + " WHERE "
					+ User.COLUMN_IDNUM + " =?;";

			pstmt = conn.prepareStatement(sel);
			pstmt.setInt(1, attempt);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static int logInUser(String username, String password)
			throws InvalidKeySpecException, NoSuchAlgorithmException, LockoutException {
		String sql = "SELECT " + User.COLUMN_IDNUM + " FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_USERNAME
				+ " =?";
		int id = -1;

		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				id = rs.getInt(User.COLUMN_IDNUM);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (id != -1) {

			logger.info("[" + id + "] " + username + " attempting to log in.");
			User u = getUserByID(id);
			System.out.println(u.getStatus());
			if (u.getStatus() != User.STATUS_LOCKED) {

				if (Security.validatePassword(password, u.getPassword())) {
					updateLogIn(id);
					return id;
				} else {
					updateAttempt(id);
					return -1;
				}
			} else {

				logger.info("[" + id + "] " + username + " is locked out.");
				throw new LockoutException();

			}
		} else {

			logger.info("Unregistered user attempting to log in.");
		}

		return id;
	}

	public static void changePassword(String oldpass, String newpass, int id) throws PasswordMismatch {

		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		String old;
		PreparedStatement pstmt = null;
		User u = getUserByID(id);
		old = u.getPassword();

		try {
			if (Security.validatePassword(oldpass, old)) {
				String sel = "UPDATE " + User.TABLE_NAME + " SET " + User.COLUMN_PASS + " =? " + " , "+ User.COLUMN_STATUS + " =? "
						+ " WHERE " + User.COLUMN_IDNUM + " =?;";

				newpass = Security.createHash(newpass);

				pstmt = conn.prepareStatement(sel);
				pstmt.setString(1, newpass);
				pstmt.setInt(2, User.STATUS_UNLOCKED);
				pstmt.setInt(3, id);
				pstmt.executeUpdate();
			} else {
				throw new PasswordMismatch();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void unlockUser(int id) throws NoSuchAlgorithmException, InvalidKeySpecException, AddressException,
			UnsupportedEncodingException, MessagingException {
		// some unlocking functions
		String sql = "INSERT INTO " + UnlockedUsers.TABLE_NAME + " (" + UnlockedUsers.COLUMN_IDUSER + ") VALUES (?);";

		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		User u = getUserByID(id);
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			updateStatus(id, User.STATUS_TEMP);
			String sel = "UPDATE " + User.TABLE_NAME + " SET " + User.COLUMN_PASS + " =? " + " WHERE "
					+ User.COLUMN_IDNUM + " =?;";

			char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
			String newP = RandomStringUtils.random( 16, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
			
			String newpass = Security.createHash(newP);

			String body = "You have been given a temporary password " + newP
					+ " please change it immediately upon login or your account will expire.";

			EmailUtil.sendEmail(u.getEmail(), "[SHS Library]", body);

			pstmt = conn.prepareStatement(sel);
			pstmt.setString(1, newpass);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

			logger.info("Unlocking " + "[" + id + "]" + getUserNameById(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static boolean validateQuestionAndAnswer(int id, String username, String answer)
			throws PasswordMismatch, AddressException, UnsupportedEncodingException, MessagingException {

		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		String actual_ans;
		PreparedStatement pstmt = null;
		User u = getUserByIDandUsername(id, username);
		actual_ans = u.getSecretAnswer();
		boolean match = false;

		try {
			if (Security.validatePassword(answer, actual_ans)) {
				unlockUser(id);
				match = true;
			} else {
				throw new PasswordMismatch();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return match;
	}

	public static boolean validateUser(User u) {

		if (u.getFirstName() == "" || u.getLastName() == "" || u.getMiddleName() == "" || u.getUserName() == ""
				|| u.getEmail() == "" || u.getSecretAnswer() == "" || u.getSecretQuestion() == "") {
			return false;
		}

		return true;
	}

}
