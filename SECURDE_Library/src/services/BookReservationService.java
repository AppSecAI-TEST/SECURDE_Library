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
import models.User;

public class BookReservationService {
	public void addBookReservation(BookReservation br){
		String sql ="INSERT INTO " + BookReservation.TABLE_NAME + " (" 
				+ BookReservation.COLUMN_IDBOOKRESERVATION + ", "
				+ BookReservation.COLUMN_IDBOOK + ", "
				+ BookReservation.COLUMN_IDUSER + ", "
				+ BookReservation.COLUMN_DEADLINE + ", "
				+ BookReservation.COLUMN_RETURNTIME + ", "
				+ BookReservation.COLUMN_CREATETIME + ") "
				+ " VALUES (?,?,?,?,?,?);";
				
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, br.getIdBookReservation());
			pstmt.setInt(2, br.getIdBook());
			pstmt.setInt(3, br.getIdUser());
			java.sql.Date deadline = new java.sql.Date(br.getDeadline().getTimeInMillis());
			pstmt.setDate(4, deadline);
			java.sql.Date returntime = new java.sql.Date(br.getReturnTime().getTimeInMillis());
			pstmt.setDate(5, returntime);
		    java.sql.Date creation = new java.sql.Date(new Date().getTime());
			pstmt.setDate(6,creation);
			
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
		
	}
	
	public ArrayList<BookReservation> getBookReservationByUser(int id){
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

	public ArrayList<BookReservation> getAllBookReservation(int id){
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

	public ArrayList<BookReservation> getBookReservationByDeadline(Date deadline){
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
	
	public void deleteBookReservation(int id){
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
