package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBPool;
import models.Books;
import models.Rooms;

public class RoomsServices {
	
	public ArrayList<Rooms> getAllRooms(){
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
		}
		
		return rooms;
	}
	



}
