package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import db.DBPool;
import models.BookReservation;
import models.RoomReservation;
import models.RoomSlot;
import models.User;

public class RoomSlotService {
	public ArrayList<RoomSlot> getRoomSlotByRoom(int idroom){
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
				GregorianCalendar starttime = new GregorianCalendar();
				starttime.setTimeInMillis(rs.getDate(RoomReservation.COLUMN_STARTTIME).getTime());
				rr.setStart_time(starttime);
				
				GregorianCalendar endtime = new GregorianCalendar();
				endtime.setTimeInMillis(rs.getDate(RoomReservation.COLUMN_ENDTIME).getTime());
				rr.setEnd_time(endtime);
				
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
	
	public static void updateStatus(int idroomslot, int status) {
		String sql = "UPDATE " + RoomSlot.TABLE_NAME + " SET " + RoomSlot.COLUMN_STATUS + " =? " + " WHERE "
				+ RoomSlot.COLUMN_IDROOMSLOT + " =" + idroomslot + ";";
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			
		
			if(status==0)
			pstmt.setInt(1, 1);
			else
			pstmt.setInt(1, 0);	

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
