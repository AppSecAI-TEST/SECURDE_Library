package models;

import java.util.GregorianCalendar;

public class BookReservation {
	public final static String TABLE_NAME						= "book_reservation"		;
	public final static String	COLUMN_IDBOOKRESERVATION       	= "idBookReservation"   	;
	public final static String	COLUMN_IDBOOK    				= "idBook"    	 			;
	public final static String 	COLUMN_IDUSER       			= "idUser"       			;
	public final static String 	COLUMN_CREATETIME  				= "create_time"  			;
	public final static String 	COLUMN_DEADLINE  				= "deadline"  				;
	public final static String 	COLUMN_RETURNTIME  				= "return_time"  			;
	
	private int idBookReservation;
	private int idBook;
	private int idUser;
	private GregorianCalendar createTime;
	private GregorianCalendar deadline;
	private GregorianCalendar returnTime;
	
	public BookReservation() {
		
	}

	public int getIdBookReservation() {
		return idBookReservation;
	}

	public void setIdBookReservation(int idBookReservation) {
		this.idBookReservation = idBookReservation;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
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

	public GregorianCalendar getDeadline() {
		return deadline;
	}

	public void setDeadline(GregorianCalendar deadline) {
		this.deadline = deadline;
	}

	public GregorianCalendar getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(GregorianCalendar returnTime) {
		this.returnTime = returnTime;
	}
	
	
	
}
