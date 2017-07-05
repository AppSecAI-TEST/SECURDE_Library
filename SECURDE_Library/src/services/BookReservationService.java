package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import db.DBPool;
import models.BookReservation;

public class BookReservationService {
	public static long addBookReservation(BookReservation br){
		String sql ="INSERT INTO " + BookReservation.TABLE_NAME + " (" 
				+ BookReservation.COLUMN_IDBOOKRESERVATION + ", "
				+ BookReservation.COLUMN_IDBOOK + ", "
				+ BookReservation.COLUMN_IDUSER + ", "
				+ BookReservation.COLUMN_DEADLINE + ", "
				+ BookReservation.COLUMN_CREATETIME + ") "
				+ " VALUES (?,?,?,?,?);";
				
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		
		PreparedStatement pstmt = null;
		java.sql.Date deadline = new java.sql.Date(0);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, br.getIdBookReservation());
			pstmt.setInt(2, br.getIdBook());
			pstmt.setInt(3, br.getIdUser());
			
			long adjustment=0;
			int access = UserService.getUserByID(br.getIdUser()).getAccessLevel();
			switch(access){
			case 0:
				adjustment = Long.parseLong("604800000");
				break;
			case 1:
			case 2:
			case 3:
			case 4:
				adjustment = Long.parseLong("2629746000");
				break;
			}
			
			deadline = new java.sql.Date(new Date().getTime()+adjustment);
			pstmt.setDate(4, deadline);
		    java.sql.Date creation = new java.sql.Date(new Date().getTime());
			pstmt.setDate(5,creation);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return deadline.getTime();
		
	}
	
	public static ArrayList<BookReservation> getBookReservationByUser(int id){
		ArrayList<BookReservation> breservation = new ArrayList<BookReservation>();
		
		String sql = "Select * from " + BookReservation.TABLE_NAME + " WHERE "
				+ BookReservation.COLUMN_IDUSER + " =?;";
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BookReservation br = new BookReservation();
				br.setIdBookReservation(rs.getInt(BookReservation.COLUMN_IDBOOKRESERVATION));
				br.setIdBook(rs.getInt(BookReservation.COLUMN_IDBOOK));
				br.setIdUser(rs.getInt(BookReservation.COLUMN_IDUSER));
				
				GregorianCalendar deadline = new GregorianCalendar();
				deadline.setTimeInMillis(rs.getDate(BookReservation.COLUMN_DEADLINE).getTime());
				br.setDeadline(deadline);
				
				GregorianCalendar returntime = new GregorianCalendar();
				returntime.setTimeInMillis(rs.getDate(BookReservation.COLUMN_RETURNTIME).getTime());
				br.setDeadline(returntime);
				
				breservation.add(br);
				
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return breservation;
	}	

	public static ArrayList<BookReservation> getAllBookReservation(int id){
		ArrayList<BookReservation> breservation = new ArrayList<BookReservation>();
		
		String sql = "Select * from " + BookReservation.TABLE_NAME;
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
		
			rs = pstmt.executeQuery();
			while(rs.next()){
				BookReservation br = new BookReservation();
				br.setIdBookReservation(rs.getInt(BookReservation.COLUMN_IDBOOKRESERVATION));
				br.setIdBook(rs.getInt(BookReservation.COLUMN_IDBOOK));
				br.setIdUser(rs.getInt(BookReservation.COLUMN_IDUSER));
				
				GregorianCalendar deadline = new GregorianCalendar();
				deadline.setTimeInMillis(rs.getDate(BookReservation.COLUMN_DEADLINE).getTime());
				br.setDeadline(deadline);
				
				GregorianCalendar returntime = new GregorianCalendar();
				returntime.setTimeInMillis(rs.getDate(BookReservation.COLUMN_RETURNTIME).getTime());
				br.setDeadline(returntime);
				
				breservation.add(br);
				
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return breservation;
	}	

	public static ArrayList<BookReservation> getBookReservationByDeadline(Date deadline){
		ArrayList<BookReservation> breservation = new ArrayList<BookReservation>();
		
		String sql = "Select * from " + BookReservation.TABLE_NAME + " WHERE "
				+ BookReservation.COLUMN_DEADLINE + " =?;";
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			java.sql.Date deadline1 = new java.sql.Date(deadline.getTime());
			pstmt.setDate(1, deadline1);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BookReservation br = new BookReservation();
				br.setIdBookReservation(rs.getInt(BookReservation.COLUMN_IDBOOKRESERVATION));
				br.setIdBook(rs.getInt(BookReservation.COLUMN_IDBOOK));
				br.setIdUser(rs.getInt(BookReservation.COLUMN_IDUSER));
				
				GregorianCalendar deadline2 = new GregorianCalendar();
				deadline2.setTimeInMillis(rs.getDate(BookReservation.COLUMN_DEADLINE).getTime());
				br.setDeadline(deadline2);
				
				GregorianCalendar returntime = new GregorianCalendar();
				returntime.setTimeInMillis(rs.getDate(BookReservation.COLUMN_RETURNTIME).getTime());
				br.setDeadline(returntime);
				
				breservation.add(br);
				
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return breservation;
	}
	
	public static void deleteBookReservation(int id){
		String sql = "DELETE FROM " + BookReservation.TABLE_NAME + " WHERE "
				 + BookReservation.COLUMN_IDBOOKRESERVATION + " = ?;";
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
