package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import db.DBPool;
import models.RoomSlot;
import models.Rooms;
import models.User;

public class RoomSlotService {
	
	public static ArrayList<RoomSlot> getAllRoomSlot(){
		ArrayList<RoomSlot> rslot = new ArrayList<RoomSlot>();
		
		String sql = "Select * from " + RoomSlot.TABLE_NAME;
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				RoomSlot rr = new RoomSlot();
				
				rr.setIdRoomSlot(rs.getInt(RoomSlot.COLUMN_IDROOMSLOT));
				rr.setIdRoom(rs.getInt(RoomSlot.COLUMN_IDROOM));
				rr.setStart_time(rs.getInt(RoomSlot.COLUMN_STARTTIME));
				rr.setEnd_time(rs.getInt(RoomSlot.COLUMN_ENDTIME));
				
				rr.setStatus(rs.getInt(RoomSlot.COLUMN_STATUS));
				rslot.add(rr);
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
		
		return rslot;
	}
	
	public static ArrayList<RoomSlot> getRoomSlotByRoom(int idroom){
		ArrayList<RoomSlot> rslot = new ArrayList<RoomSlot>();
		
		String sql = "Select * from " + RoomSlot.TABLE_NAME + " WHERE "
				+ RoomSlot.COLUMN_IDROOM + " =?;";;
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, idroom);
			rs = pstmt.executeQuery();
			while(rs.next()){
				RoomSlot rr = new RoomSlot();
				rr.setIdRoomSlot(rs.getInt(RoomSlot.COLUMN_IDROOMSLOT));
				rr.setIdRoom(rs.getInt(RoomSlot.COLUMN_IDROOM));
				rr.setStart_time(rs.getInt(RoomSlot.COLUMN_STARTTIME));
				rr.setEnd_time(rs.getInt(RoomSlot.COLUMN_ENDTIME));
				
				rr.setStatus(rs.getInt(RoomSlot.COLUMN_STATUS));
				
			
				rslot.add(rr);
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
		
		return rslot;
	}

	
	public static RoomSlot getRoomSlotById(int id){
		String sql = "Select * from " + RoomSlot.TABLE_NAME + " WHERE " + RoomSlot.COLUMN_IDROOMSLOT + "=?;";

		RoomSlot rslot = new RoomSlot();
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				rslot.setIdRoomSlot(rs.getInt(RoomSlot.COLUMN_IDROOMSLOT));
				rslot.setIdRoom(rs.getInt(RoomSlot.COLUMN_IDROOM));

				rslot.setIdRoom(rs.getInt(RoomSlot.COLUMN_IDROOM));
				rslot.setStart_time(rs.getInt(RoomSlot.COLUMN_STARTTIME));
				rslot.setEnd_time(rs.getInt(RoomSlot.COLUMN_ENDTIME));
				rslot.setStatus(rs.getInt(RoomSlot.COLUMN_STATUS));
			
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
		
		return rslot;
		
	}
	public static void updateStatus(int idroomslot, int status) {
		String sql = "UPDATE " + RoomSlot.TABLE_NAME + " SET " + RoomSlot.COLUMN_STATUS + " =? " + " WHERE "
				+ RoomSlot.COLUMN_IDROOMSLOT + " =? ;";
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, status);
			pstmt.setInt(2, idroomslot);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
