package models;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Rooms implements Serializable{
	public final static String TABLE_NAME			= "rooms"		 	;
	public final static String	COLUMN_IDROOMS       = "idRooms"       	;
	public final static String	COLUMN_NAME    		= "name"    	 	;
	public final static String 	COLUMN_FLOOR       	= "floor"       	;
	public final static String 	COLUMN_STATUS    	= "status"    		;
	public final static String 	COLUMN_CREATETIME  	= "create_time"  	;
	
	private int idRooms;
	private String name;
	private String floor;
	private int status;
	private GregorianCalendar createTime;
	
	public Rooms() {
		
	}
	public int getIdRooms() {
		return idRooms;
	}
	public void setIdRooms(int idRooms) {
		this.idRooms = idRooms;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public GregorianCalendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(GregorianCalendar createTime) {
		this.createTime = createTime;
	}
	
}
