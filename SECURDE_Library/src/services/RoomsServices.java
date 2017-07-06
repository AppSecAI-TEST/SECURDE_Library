package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBPool;
import models.Rooms;

public class RoomsServices {
	
	public static ArrayList<Rooms> getAllRooms(){
		ArrayList<Rooms> rooms = new ArrayList<Rooms>();
		
		String sql = "Select * from " + Rooms.TABLE_NAME;
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Rooms r = new Rooms();
				r.setIdRooms(rs.getInt(Rooms.COLUMN_IDROOMS));
				r.setName(rs.getString(Rooms.COLUMN_NAME));
				r.setFloor(rs.getString(Rooms.COLUMN_FLOOR));
			
				rooms.add(r);
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
		
		return rooms;
	}

	public static List<Rooms> getRoomByTime(int start, int end){
		List<Rooms> rooms = new ArrayList<Rooms>();
		
		
		
		
		return rooms;
	}



}
