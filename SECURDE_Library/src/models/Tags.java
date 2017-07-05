package models;

public class Tags {
	public final static String TABLE_NAME = "tags";
	public final static String COLUMN_TAG = "tag";
	public final static String COLUMN_BOOKID = "idBook";
	
	private String tag;
	private int bookid;
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
}
