package models;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Books implements Serializable {

	public final static String TABLE_NAME = "books";
	public final static String COLUMN_IDBOOK = "idBooks";
	public final static String COLUMN_TITLE = "title";
	public final static String COLUMN_AUTHOR = "author";
	public final static String COLUMN_PUBLISHER = "publisher";
	public final static String COLUMN_YEAR = "year";
	public final static String COLUMN_LOCATION = "location";
	public final static String COLUMN_STATUS = "status";
	public final static String COLUMN_CREATETIME = "create_time";
	public final static String COLUMN_TYPE = "type";

	public final static int BOOK = 0;
	public final static int MAGAZINE = 1;
	public final static int THESIS = 2;
	public final static int UNKNOWN = 3;

	public final static int AVAILABLE = 0;
	public final static int RESERVED = 1;
	public final static int OUT = 2;

	private int idBooks;
	private String title;
	private String author;
	private String publisher;
	private int year;
	private double location;
	private int status;
	private GregorianCalendar createTime;
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Books() {

	}

	public int getIdBooks() {
		return idBooks;
	}

	public void setIdBooks(int idBooks) {
		this.idBooks = idBooks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getLocation() {
		return location;
	}

	public void setLocation(double location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusString() {
		switch (status) {
		case AVAILABLE:
			return "Available";
		case OUT:
			return "Out";
		case RESERVED:
			return "Reserved";
		default:
			return "Unknown Status";
		}
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
