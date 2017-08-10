package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import db.DBPool;
import models.UnlockedUsers;
import models.User;

public class UnlockedUserService {

	public static UnlockedUsers getUnlockedUserById(int id) {
		String sql = "Select * from " + UnlockedUsers.TABLE_NAME + " WHERE " 
					+ UnlockedUsers.COLUMN_IDUSER + " =?;";
		
		Connection conn = null;
		DBPool.getInstance();
		conn = DBPool.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UnlockedUsers uu = new UnlockedUsers();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				uu.setIdUser(rs.getInt(UnlockedUsers.COLUMN_IDUSER));
				uu.setPwStatus(rs.getInt(UnlockedUsers.COLUMN_PWSTATUS));
				uu.setIdUnlockedUsers(rs.getInt(UnlockedUsers.COLUMN_IDUNLOCKEDUSERS));
				GregorianCalendar createtime = new GregorianCalendar();
				createtime.setTimeInMillis(rs.getDate(UnlockedUsers.COLUMN_CREATETIME).getTime());
				uu.setCreateTime(createtime);

			}
		} catch (SQLException e) {
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

		return uu;
	}
	
}
