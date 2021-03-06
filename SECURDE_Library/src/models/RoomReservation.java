package models;

import java.util.GregorianCalendar;

public class RoomReservation {
	public final static String TABLE_NAME						= "room_reservation"		;
	public final static String	COLUMN_IDROOMRESERVATION       	= "idRoomReservation"   	;
	public final static String	COLUMN_IDROOM    				= "idRoom"    	 			;
	public final static String 	COLUMN_IDUSER       			= "idUser"       			;
	public final static String 	COLUMN_CREATETIME  				= "create_time"  			;
	public final static String 	COLUMN_STARTTIME  				= "start_time"  			;
	public final static String 	COLUMN_ENDTIME  				= "end_time"  			;
	
	private int idRoomReservation;
	private int idRoom;
	private int idUser;
	private GregorianCalendar createTime;
	private int startTime;
	private int endTime;
	
	public RoomReservation() {
		
	}
	
	public int getIdRoomReservation() {
		return idRoomReservation;
	}
	public void setIdRoomReservation(int idRoomReservation) {
		this.idRoomReservation = idRoomReservation;
	}
	public int getIdRoom() {
		return idRoom;
	}
	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public GregorianCalendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(GregorianCalendar createTime) {
		this.createTime = createTime;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	

}
