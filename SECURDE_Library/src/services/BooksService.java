package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import db.DBPool;
import models.Books;

public class BooksService {
	
	public void addBook(Books b){
		String sql ="INSERT INTO " + Books.TABLE_NAME + " (" 
				+ Books.COLUMN_IDBOOK + ", "
				+ Books.COLUMN_TITLE + ", "
				+ Books.COLUMN_AUTHOR + ", "
				+ Books.COLUMN_PUBLISHER + ", "
				+ Books.COLUMN_YEAR + ", "
				+ Books.COLUMN_STATUS + ", "
				+ Books.COLUMN_LOCATION + ", "
				+ Books.COLUMN_TYPE + ", "
				+ Books.COLUMN_CREATETIME + ") "
				+ " VALUES (?,?,?,?,?,?,?,?,?);";
				
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getIdBooks());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getAuthor());
			pstmt.setString(4, b.getPublisher());
			pstmt.setInt(5, b.getYear());
			pstmt.setInt(6, b.getStatus());
			pstmt.setDouble(7, b.getLocation());
			pstmt.setInt(8, b.getType());
		    java.sql.Date creation = new java.sql.Date(new Date().getTime());
			pstmt.setDate(9,creation);
			
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
	
	public void deleteBook(int idBook){
		String sql = "DELETE FROM " + Books.TABLE_NAME + " WHERE "
				 + Books.COLUMN_IDBOOK + " = ?;";
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  idBook);
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
	
	public void updateBook(Books b){
		// UPDATE person SET name=?, gender=?, age=? where idNum=?
		
		String sql = "UPDATE " + Books.TABLE_NAME + " SET "
				+ Books.COLUMN_TITLE + "=?, "
				+ Books.COLUMN_AUTHOR + "=?, "
				+ Books.COLUMN_PUBLISHER + "=?, "
				+ Books.COLUMN_YEAR + "=?, "
				+ Books.COLUMN_STATUS + "=?, "
				+ Books.COLUMN_LOCATION + "=?, "
				+ Books.COLUMN_TYPE + "=? "
				+ " WHERE " + Books.COLUMN_IDBOOK + " = ?;";

		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setString(3, b.getPublisher());
			pstmt.setInt(4, b.getYear());
			pstmt.setInt(5, b.getStatus());
			pstmt.setDouble(6, b.getLocation());
			pstmt.setDouble(7, b.getType());
			pstmt.setInt(8, b.getIdBooks());
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
	
	public ArrayList<Books> getAllBooks(){
		ArrayList<Books> books = new ArrayList<Books>();
		
		String sql = "Select * from " + Books.TABLE_NAME;
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Books b = new Books();
				b.setIdBooks(rs.getInt(Books.COLUMN_IDBOOK));
				b.setTitle(rs.getString(Books.COLUMN_TITLE));
				b.setAuthor(rs.getString(Books.COLUMN_AUTHOR));
				b.setPublisher(rs.getString(Books.COLUMN_PUBLISHER));
				b.setYear(rs.getInt(Books.COLUMN_YEAR));
				b.setStatus(rs.getInt(Books.COLUMN_STATUS));
				b.setLocation(rs.getDouble(Books.COLUMN_LOCATION));
				b.setType(rs.getInt(Books.COLUMN_TYPE));
				
				books.add(b);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return books;
	}

	public Books getBookById(int id){
		String sql = "Select * from " + Books.TABLE_NAME + " WHERE " + Books.COLUMN_IDBOOK + "=?;";

		Books b = new Books();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				b.setIdBooks(rs.getInt(Books.COLUMN_IDBOOK));
				b.setTitle(rs.getString(Books.COLUMN_TITLE));
				b.setAuthor(rs.getString(Books.COLUMN_AUTHOR));
				b.setPublisher(rs.getString(Books.COLUMN_PUBLISHER));
				b.setYear(rs.getInt(Books.COLUMN_YEAR));
				b.setStatus(rs.getInt(Books.COLUMN_STATUS));
				b.setLocation(rs.getDouble(Books.COLUMN_LOCATION));
				b.setType(rs.getInt(Books.COLUMN_TYPE));
				
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return b;
		
	}

	public ArrayList<Books> getAllBooksBySearch(String keyword){
		ArrayList<Books> books = new ArrayList<Books>();
		
		String sql = "Select * from " + Books.TABLE_NAME + " WHERE "
				+ Books.COLUMN_TITLE + " =? OR "
				+ Books.COLUMN_AUTHOR + " =? OR "
				+ Books.COLUMN_PUBLISHER + " =? OR "
				+ Books.COLUMN_YEAR	+ " =?;";
		
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			pstmt.setString(4, keyword);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				Books b = new Books();
				b.setIdBooks(rs.getInt(Books.COLUMN_IDBOOK));
				b.setTitle(rs.getString(Books.COLUMN_TITLE));
				b.setAuthor(rs.getString(Books.COLUMN_AUTHOR));
				b.setPublisher(rs.getString(Books.COLUMN_PUBLISHER));
				b.setYear(rs.getInt(Books.COLUMN_YEAR));
				b.setStatus(rs.getInt(Books.COLUMN_STATUS));
				b.setLocation(rs.getDouble(Books.COLUMN_LOCATION));
				b.setType(rs.getInt(Books.COLUMN_TYPE));
				
				books.add(b);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return books;
	}	

	public ArrayList<Books> getAllBooksByAdvancedSearch(String title, String author, String publisher, String year, int type){
		ArrayList<Books> books = new ArrayList<Books>();
		
		String sql = "Select * from " + Books.TABLE_NAME + " WHERE "
				+ Books.COLUMN_TITLE + " =? AND "
				+ Books.COLUMN_AUTHOR + " =? AND "
				+ Books.COLUMN_PUBLISHER + " =? AND "
				+ Books.COLUMN_TYPE + " =? AND "
				+ Books.COLUMN_YEAR	+ " =?;";
		
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setString(3, publisher);
			pstmt.setInt(4, type);
			pstmt.setString(5, year);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				Books b = new Books();
				b.setIdBooks(rs.getInt(Books.COLUMN_IDBOOK));
				b.setTitle(rs.getString(Books.COLUMN_TITLE));
				b.setAuthor(rs.getString(Books.COLUMN_AUTHOR));
				b.setPublisher(rs.getString(Books.COLUMN_PUBLISHER));
				b.setYear(rs.getInt(Books.COLUMN_YEAR));
				b.setStatus(rs.getInt(Books.COLUMN_STATUS));
				b.setLocation(rs.getDouble(Books.COLUMN_LOCATION));
				b.setType(rs.getInt(Books.COLUMN_TYPE));
				
				books.add(b);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return books;
	}	

}
