package models;

import java.util.GregorianCalendar;

public class UnlockedUsers {
	public final static String TABLE_NAME						= "unlocked_users"		;
	public final static String	COLUMN_IDUSER			      	= "idUser"   			;
	public final static String	COLUMN_PWSTATUS    				= "pw_status"    	 	;
	public final static String 	COLUMN_CREATETIME  				= "create_time"  		;
	public final static String COLUMN_IDUNLOCKEDUSERS			= "idUnlockedUsers"		;
	
	private int idUser;
	private int pwStatus;
	private GregorianCalendar createTime;
	private int idUnlockedUsers;
	
	public int getIdUnlockedUsers() {
		return idUnlockedUsers;
	}
	public void setIdUnlockedUsers(int idUnlockedUsers) {
		this.idUnlockedUsers = idUnlockedUsers;
	}
	public UnlockedUsers() {
		
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getPwStatus() {
		return pwStatus;
	}
	public void setPwStatus(int pwStatus) {
		this.pwStatus = pwStatus;
	}
	public GregorianCalendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(GregorianCalendar createTime) {
		this.createTime = createTime;
	}
	
	
}
