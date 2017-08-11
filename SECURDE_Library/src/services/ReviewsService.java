package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import db.DBPool;
import models.Books;
import models.Reviews;

public class ReviewsService {
	
	public static int addReview(Reviews r) throws SQLException{
		int id= -1;
		String sql ="INSERT INTO " + Reviews.TABLE_NAME + " (" 
				+ Reviews.COLUMN_REVIEW + ", "
				+ Reviews.COLUMN_REVIEWID + ", "
				+ Reviews.COLUMN_BOOKID + ", "
				+ Reviews.COLUMN_USERID + ", "
				+ Reviews.COLUMN_RATING + ", "
				+ Reviews.COLUMN_CREATETIME + ") "
				+ " VALUES (?,?,?,?,?,?);";
				
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getReview());
			pstmt.setInt(2, r.getIdReview());
			pstmt.setInt(3, r.getIdBook());
			pstmt.setInt(4, r.getIdUser());
			pstmt.setInt(5, r.getRating());
		    java.sql.Date creation = new java.sql.Date(new Date().getTime());
			pstmt.setDate(6,creation);
			
			pstmt.executeUpdate();

			String sel ="SELECT "+Reviews.COLUMN_REVIEWID+" FROM "+ Reviews.TABLE_NAME+" WHERE "
					+ Reviews.COLUMN_REVIEW + " =? AND "
					+ Reviews.COLUMN_BOOKID + " =? AND "
					+ Reviews.COLUMN_USERID + " =? AND "
					+ Reviews.COLUMN_RATING	+ " =?";
			
			pstmt = conn.prepareStatement(sel);
			pstmt.setInt(1, r.getIdBook());
			pstmt.setInt(2, r.getIdUser());
			pstmt.setString(3, r.getReview());
			pstmt.setInt(4, r.getRating());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				id = rs.getInt(Reviews.COLUMN_REVIEWID);
				
			}
		
		} finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return id; 
	}
	
	public ArrayList<Reviews> getReviewsByBook(int id){
		ArrayList<Reviews> reviews = new ArrayList<Reviews>();
		
		String sql = "Select * from " + Reviews.TABLE_NAME + " WHERE "
				+ Reviews.COLUMN_BOOKID + " =?;";
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Reviews r = new Reviews();
				r.setReview(rs.getString(Reviews.COLUMN_REVIEW));
				r.setIdReview(rs.getInt(Reviews.COLUMN_REVIEWID));
				r.setIdBook(rs.getInt(Reviews.COLUMN_BOOKID));
				r.setIdUser(rs.getInt(Reviews.COLUMN_USERID));
				r.setRating(rs.getInt(Reviews.COLUMN_RATING));
				reviews.add(r);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return reviews;
	}	
}
