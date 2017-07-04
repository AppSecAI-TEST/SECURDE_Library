package models;

import java.io.Serializable;

public class Books implements Serializable {

	public final static String TABLE_NAME			= "books"		 ;
	public final static String	COLUMN_IDBOOK       = "idBooks"       ;
	public final static String	COLUMN_TITLE    	= "title"    	 ;
	public final static String 	COLUMN_AUTHOR       = "author"       ;
	public final static String 	COLUMN_PUBLISHER    = "publisher"    ;
	public final static String 	COLUMN_YEAR   		= "year"   		 ;
	public final static String 	COLUMN_LOCATION  	= "location"     ;
	public final static String 	COLUMN_STATUS       = "status"       ;
	public final static String 	COLUMN_CREATETIME  = "create_time"   ;
	
	private int idBooks;
	private String title;
	
	
}
