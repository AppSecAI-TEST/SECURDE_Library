package models;

import java.util.GregorianCalendar;

public class Reviews {
	public final static String TABLE_NAME = "reviews";
	public final static String COLUMN_REVIEW = "review";
	public final static String COLUMN_REVIEWID = "idReview";
	public final static String COLUMN_BOOKID = "idBook";
	public final static String COLUMN_USERID = "idUser";
	public final static String COLUMN_RATING = "rating";
	public final static String 	COLUMN_CREATETIME = "create_time";
	
	private String review;
	private int idReview;
	private int idBook;
	private int idUser;
	private int rating;
	private GregorianCalendar createTime;
	
	public Reviews() {
		
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getIdReview() {
		return idReview;
	}
	public void setIdReview(int idReview) {
		this.idReview = idReview;
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public GregorianCalendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(GregorianCalendar createTime) {
		this.createTime = createTime;
	}
	
}
