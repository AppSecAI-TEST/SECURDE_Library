package models;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class User implements Serializable{
	public final static String TABLE_NAME = "users";
	public final static String COLUMN_IDNUM = "idUser";
	public final static String COLUMN_EMAIL = "email";
	public final static String COLUMN_FIRSTNAME = "first_name";
	public final static String COLUMN_MIDDLENAME = "middle_name";
	public final static String COLUMN_LASTNAME = "last_name";
	public final static String COLUMN_PASS = "password";
	public final static String COLUMN_CREATETIME = "create_time";
	public final static String COLUMN_LASTLOGIN = "last_login";
	public final static String COLUMN_ACCESSLEVEL = "access_level";
	public final static String COLUMN_USERNAME = "username";
	public final static String COLUMN_BIRTHDATE = "birthdate";
	public final static String COLUMN_SECRETQUESTION = "secret_question";
	public final static String COLUMN_SECRETANSWER = "secret_answer";
	public final static String COLUMN_STATUS = "status";
	public final static String COLUMN_ATTEMPT = "attempt";
	
	private String firstName, middleName, lastName;
	private int idUser;
	private String userName;
	private String email;
	private String password;
	private GregorianCalendar createTime;
	private GregorianCalendar lastLogin;
	private GregorianCalendar birthdate;
	private int accessLevel;
	private String secretQuestion, secretAnswer;
	private int status, attempt;
	
	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User(){
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GregorianCalendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(GregorianCalendar createTime) {
		this.createTime = createTime;
	}

	public GregorianCalendar getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(GregorianCalendar lastLogin) {
		this.lastLogin = lastLogin;
	}

	public GregorianCalendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(GregorianCalendar birthdate) {
		this.birthdate = birthdate;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}
	
	public String getSalt(){
		return password.split(":")[1];
	}
	
}
