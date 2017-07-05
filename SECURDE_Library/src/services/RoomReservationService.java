package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import db.DBPool;
import models.RoomReservation;

public class RoomReservationService {
	
	public void updateRoomReservation(int id){
		
		String sql = "UPDATE " + RoomReservation.TABLE_NAME + " SET "
				+ RoomReservation.COLUMN_STATUS + "=? "
				+ " WHERE " + RoomReservation.COLUMN_IDROOMRESERVATION + " = ?;";

		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2,id);
			
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

	public ArrayList<RoomReservation> getRoomReservationByStatus(int status){
		String sql = "Select * from " + RoomReservation.TABLE_NAME + " WHERE " + RoomReservation.COLUMN_STATUS + "=?;";

		ArrayList<RoomReservation> roomreservation = new ArrayList<RoomReservation>();
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			rs = pstmt.executeQuery();
			while(rs.next()){
				RoomReservation rr = new RoomReservation();
				rr.setIdRoomReservation(rs.getInt(RoomReservation.COLUMN_IDROOMRESERVATION));
				rr.setIdRoom(rs.getInt(RoomReservation.COLUMN_IDROOM));
				rr.setIdUser(rs.getInt(RoomReservation.COLUMN_IDUSER));
				rr.setStatus(rs.getInt(RoomReservation.COLUMN_STATUS));
				
				GregorianCalendar starttime = new GregorianCalendar();
				starttime.setTimeInMillis(rs.getDate(RoomReservation.COLUMN_STARTTIME).getTime());
				rr.setStartTime(starttime);
				
				GregorianCalendar endtime = new GregorianCalendar();
				endtime.setTimeInMillis(rs.getDate(RoomReservation.COLUMN_ENDTIME).getTime());
				rr.setEndTime(endtime);
				
		
				roomreservation.add(rr);
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
		
		return roomreservation;
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
				rr.setStatus(rs.getInt(RoomReservation.COLUMN_STATUS));
				
				GregorianCalendar starttime = new GregorianCalendar();
				starttime.setTimeInMillis(rs.getDate(RoomReservation.COLUMN_STARTTIME).getTime());
				rr.setStartTime(starttime);
				
				GregorianCalendar endtime = new GregorianCalendar();
				endtime.setTimeInMillis(rs.getDate(RoomReservation.COLUMN_ENDTIME).getTime());
				rr.setEndTime(endtime);
				
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
				+ RoomReservation.COLUMN_STATUS + " =? AND "
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
			pstmt.setInt(2, status);
			java.sql.Date startime1 = new java.sql.Date(startime.getTime());
			pstmt.setDate(3, startime1);
			java.sql.Date endtime1 = new java.sql.Date(endtime.getTime());
			pstmt.setDate(4, endtime1);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				RoomReservation rr = new RoomReservation();
				rr.setIdRoomReservation(rs.getInt(RoomReservation.COLUMN_IDROOMRESERVATION));
				rr.setIdRoom(rs.getInt(RoomReservation.COLUMN_IDROOM));
				rr.setIdUser(rs.getInt(RoomReservation.COLUMN_IDUSER));
				rr.setStatus(rs.getInt(RoomReservation.COLUMN_STATUS));
				
				GregorianCalendar starttime = new GregorianCalendar();
				starttime.setTimeInMillis(rs.getDate(RoomReservation.COLUMN_STARTTIME).getTime());
				rr.setStartTime(starttime);
				
				GregorianCalendar endtime2 = new GregorianCalendar();
				endtime2.setTimeInMillis(rs.getDate(RoomReservation.COLUMN_ENDTIME).getTime());
				rr.setEndTime(endtime2);
				
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
