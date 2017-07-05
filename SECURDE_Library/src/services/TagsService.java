package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Tags;
import db.DBPool;

public class TagsService {
	
		public void addTag(Tags t){
			String sql ="INSERT INTO " + Tags.TABLE_NAME + " (" 
					+ Tags.COLUMN_TAG + ", "
					+ Tags.COLUMN_BOOKID + ") "
					+ " VALUES (?,?);";
			
			DBPool.getInstance();
			Connection conn = DBPool.getConnection();
			
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getTag());
				pstmt.setInt(2, t.getBookid());
		
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
		
		public ArrayList<Integer> getBookIDUsingTagFilters(String tag){
			// UPDATE person SET name=?, gender=?, age=? where idNum=?
			
			ArrayList <Integer> idNum = new ArrayList<Integer>();
			
			String sql = "SELECT "+Tags.COLUMN_BOOKID+" FROM " + Tags.TABLE_NAME;
			
			String[] taglist = tag.split(",");
			
			for(int i=0; i<taglist.length; i++){
				if(i == 0) 	sql += " WHERE ";
				else		sql += " AND ";
				
				sql+= Tags.COLUMN_BOOKID + " IN (SELECT " + Tags.COLUMN_BOOKID + " FROM "+Tags.TABLE_NAME
						+" WHERE "+Tags.COLUMN_TAG+" =? )";
			}
			
			sql += " GROUP BY "+Tags.COLUMN_BOOKID;
			DBPool.getInstance();
			Connection conn = DBPool.getConnection();
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);

				for(int i=1; i<=taglist.length; i++){
					pstmt.setString(i, taglist[i-1]);
				}
				System.out.println(sql);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()){
					idNum.add(rs.getInt(Tags.COLUMN_BOOKID));
					idNum.get(idNum.size()-1);
				}
				
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
			
			return idNum;
		}
		
		public ArrayList<Tags> getTagsPerBook(int bookid){
			ArrayList<Tags> tags = new ArrayList<Tags>();
			
			String sql = "Select * from " + Tags.TABLE_NAME + " WHERE " + Tags.COLUMN_BOOKID + " =?;";
			
			DBPool.getInstance();
			Connection conn = DBPool.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			
			try{
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, bookid);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Tags t = new Tags();
					t.setTag(rs.getString(Tags.COLUMN_TAG));
					t.setBookid(rs.getInt(Tags.COLUMN_BOOKID));
					
					tags.add(t);
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
			
			return tags;
		}		
		
}
