package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import db.DBPool;
import models.Books;
import models.RoomReservation;

public class RoomReservationService {
	
	public static int addRoomReservation(RoomReservation rr){
		int id=-1;
		String sql ="INSERT INTO " + RoomReservation.TABLE_NAME + " (" 
				+ RoomReservation.COLUMN_IDROOM + ", "
				+ RoomReservation.COLUMN_IDUSER + ", "
				+ RoomReservation.COLUMN_CREATETIME + ", "
				+ RoomReservation.COLUMN_STARTTIME + ", "
				+ RoomReservation.COLUMN_ENDTIME + " ) "
				+ " VALUES (?,?,?,?,?);";
				
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rr.getIdRoom());
			pstmt.setInt(2, rr.getIdUser());
			java.sql.Date creation = new java.sql.Date(new Date().getTime());
			pstmt.setDate(3,creation);
			pstmt.setInt(4, rr.getStartTime());
			pstmt.setInt(5, rr.getEndTime());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			String sel ="SELECT "+RoomReservation.COLUMN_IDROOMRESERVATION+" FROM "+RoomReservation.TABLE_NAME+" WHERE "
					+ RoomReservation.COLUMN_IDROOM + " =? AND "
					+ RoomReservation.COLUMN_IDUSER + " =? AND "
					+ RoomReservation.COLUMN_CREATETIME + " =?";
			
			pstmt = conn.prepareStatement(sel);
			pstmt.setInt(1, rr.getIdRoom());
			pstmt.setInt(2, rr.getIdUser());
		
			pstmt.setDate(3, creation);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				id = rs.getInt(RoomReservation.COLUMN_IDROOMRESERVATION);
				
			}
		
			
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
		
		return id;
		
	}
	
	
	public ArrayList<RoomReservation> getAllRoomReservation(){
		ArrayList<RoomReservation> rreservation = new ArrayList<RoomReservation>();
		
		String sql = "Select * from " + RoomReservation.TABLE_NAME;
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				RoomReservation rr = new RoomReservation();
				rr.setIdRoomReservation(rs.getInt(RoomReservation.COLUMN_IDROOMRESERVATION));
				rr.setIdRoom(rs.getInt(RoomReservation.COLUMN_IDROOM));
				rr.setIdUser(rs.getInt(RoomReservation.COLUMN_IDUSER));
				
				rr.setStartTime(rs.getInt(RoomReservation.COLUMN_STARTTIME));
				rr.setEndTime(rs.getInt(RoomReservation.COLUMN_ENDTIME));
				
				rreservation.add(rr);
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
		
		return rreservation;
	}
	
	
	public ArrayList<RoomReservation> getRoomReservationBySearch(int idRoom, int status, Date startime, Date endtime){
		String sql = "Select * from " + RoomReservation.TABLE_NAME + " WHERE " 
				+ RoomReservation.COLUMN_IDROOM + " =? AND "
				+ RoomReservation.COLUMN_STARTTIME + " =? AND "
				+ RoomReservation.COLUMN_ENDTIME	+ " =?;";
		ArrayList<RoomReservation> rreservation = new ArrayList<RoomReservation>();
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
		
			pstmt.setInt(1, idRoom );
			java.sql.Date startime1 = new java.sql.Date(startime.getTime());
			pstmt.setDate(2, startime1);
			java.sql.Date endtime1 = new java.sql.Date(endtime.getTime());
			pstmt.setDate(3, endtime1);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				RoomReservation rr = new RoomReservation();
				rr.setIdRoomReservation(rs.getInt(RoomReservation.COLUMN_IDROOMRESERVATION));
				rr.setIdRoom(rs.getInt(RoomReservation.COLUMN_IDROOM));
				rr.setIdUser(rs.getInt(RoomReservation.COLUMN_IDUSER));
				
				rr.setStartTime(rs.getInt(RoomReservation.COLUMN_STARTTIME));
				rr.setEndTime(rs.getInt(RoomReservation.COLUMN_ENDTIME));
				
				rreservation.add(rr);
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
		
		return rreservation;
	}	

}
