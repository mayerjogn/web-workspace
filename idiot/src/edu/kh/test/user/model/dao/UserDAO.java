package edu.kh.test.user.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.kh.test.user.model.vo.UserDTO;

public class UserDAO {

	public UserDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		}
		public Connection getConnection() throws SQLException{
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","test1","test1");
			return conn;	
		}
		public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
			rs.close();
			ps.close();
			conn.close();		
	}
		
		public UserDTO selectUser(int userNo)throws SQLException{
			Connection conn = getConnection();
			String query = "SELECT* FROM tb_user WHERE user_no=?";
			PreparedStatement ps =conn.prepareStatement(query);
			
			ps.setInt(1, userNo);
			
			ResultSet rs = ps.executeQuery();
			UserDTO vo = null;
			if(rs.next()) {
				vo = new UserDTO();
				vo.setUserNo(userNo);
				vo.setUserId(rs.getString("user_id"));
				vo.setUserName(rs.getString("user_name"));
				vo.setUserAge(rs.getInt("user_age"));
			}
			closeAll(rs,ps,conn);
			return vo;		
		}
}
