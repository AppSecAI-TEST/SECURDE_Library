package models;

import java.util.GregorianCalendar;

public class RoomSlot {
	public final static String TABLE_NAME			= "room_slot"		 	;
	public final static String	COLUMN_IDROOMSLOT       = "idRoomSlot"       	;
	public final static String	COLUMN_IDROOM       = "idRoom"       	;
	public final static String	COLUMN_STARTTIME    		= "start_time"    	 	;
	public final static String 	COLUMN_ENDTIME       	= "end_time"       	;
	public final static String 	COLUMN_STATUS  	= "status"  	;
	
	private int idRoomSlot;
	private int status;
	private int idRoom;
	public int getIdRoom() {
		return idRoom;
	}
	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}
	private GregorianCalendar start_time, end_time;
	
	public RoomSlot() {
		
	}
	public int getIdRoomSlot() {
		return idRoomSlot;
	}
	public void setIdRoomSlot(int idRoomSlot) {
		this.idRoomSlot = idRoomSlot;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public GregorianCalendar getStart_time() {
		return start_time;
	}
	public void setStart_time(GregorianCalendar start_time) {
		this.start_time = start_time;
	}
	public GregorianCalendar getEnd_time() {
		return end_time;
	}
	public void setEnd_time(GregorianCalendar end_time) {
		this.end_time = end_time;
	}

}
