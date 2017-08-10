package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import db.DBPool;
import models.Books;

public class BooksService {

	final static Logger logger = Logger.getLogger(BooksService.class);
	
	public static int addBook(Books b) throws SQLException{
		int id=-1;
		String sql ="INSERT INTO " + Books.TABLE_NAME + " (" 
				+ Books.COLUMN_TITLE + ", "
				+ Books.COLUMN_AUTHOR + ", "
				+ Books.COLUMN_PUBLISHER + ", "
				+ Books.COLUMN_YEAR + ", "
				+ Books.COLUMN_LOCATION + ", "
				+ Books.COLUMN_TYPE + " ) "
				+ " VALUES (?,?,?,?,?,?);";
				
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setString(3, b.getPublisher());
			pstmt.setInt(4, b.getYear());
			pstmt.setDouble(5, b.getLocation());
			pstmt.setInt(6, b.getType());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			String sel ="SELECT "+Books.COLUMN_IDBOOK+" FROM "+Books.TABLE_NAME+" WHERE "
					+ Books.COLUMN_TITLE + " =? AND "
					+ Books.COLUMN_AUTHOR + " =? AND "
					+ Books.COLUMN_YEAR + " =? AND "
					+ Books.COLUMN_LOCATION	+ " =?";
			
			pstmt = conn.prepareStatement(sel);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setInt(3, b.getYear());
			pstmt.setDouble(4, b.getLocation());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				id = rs.getInt(Books.COLUMN_IDBOOK);
				
			}
		
		} finally{
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
	
	public static void deleteBook(int idBook){
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
	
	public static void updateBook(Books b) throws SQLException{
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

		DBPool.getInstance();
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
	
	public static ArrayList<Books> getAllBooks() throws SQLException{
		ArrayList<Books> books = new ArrayList<Books>();
		
		String sql = "Select * from " + Books.TABLE_NAME;
		
		DBPool.getInstance();
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
			
			logger.info("All books retrieved.");
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return books;
	}

	public static Books getBookById(int id){
		String sql = "Select * from " + Books.TABLE_NAME + " WHERE " + Books.COLUMN_IDBOOK + "=?;";

		Books b = new Books();
		DBPool.getInstance();
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
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return b;
		
	}

	public static ArrayList<Books> getBooksBySearch(String keyword) throws SQLException{
		ArrayList<Books> books = new ArrayList<Books>();
		
		String sql = "Select * from " + Books.TABLE_NAME + " WHERE "
				+ Books.COLUMN_TITLE + " LIKE ? OR "
				+ Books.COLUMN_AUTHOR + " LIKE ? OR "
				+ Books.COLUMN_PUBLISHER + " LIKE ? OR "
				+ Books.COLUMN_YEAR	+ " LIKE ?;";
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setString(4, "%"+keyword+"%");
			System.out.println(pstmt.toString());
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
			
			logger.info("Books searched with keyword/s : "+keyword);
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return books;
	}	

	public static ArrayList<Books> getBooksByAdvancedSearch(String title, String author, String publisher, int year, int type){
		ArrayList<Books> books = new ArrayList<Books>();
		
		String sql = "Select * from " + Books.TABLE_NAME + " WHERE "
				+ Books.COLUMN_TITLE + " LIKE ? AND "
				+ Books.COLUMN_AUTHOR + " LIKE ? AND "
				+ Books.COLUMN_PUBLISHER + " LIKE ? AND "
				+ Books.COLUMN_TYPE + " LIKE ? AND "
				+ Books.COLUMN_YEAR	+ " LIKE ?;";
		
		DBPool.getInstance();
		Connection conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try{
			pstmt= conn.prepareStatement(sql);
			if(!"".equals(title)) {
				title = "%"+title+"%";
			}
			pstmt.setString(1, title);
			
			if(!"".equals(author)) {
				title = "%"+author+"%";
			}
			pstmt.setString(2, author);
			
			if(!"".equals(publisher)) {
				title = "%"+publisher+"%";
			}
			pstmt.setString(3, publisher);
			
			pstmt.setInt(4, type);
			
			pstmt.setInt(5, year);
			
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
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return books;
	}	

}
