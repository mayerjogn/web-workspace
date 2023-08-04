package servlet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.util.ServerInfo;

import config.Serverinfo;

public class MemberDAO implements MemberDAOTemplate {

	public static void main(String[] args) {

		MemberDAO dao = new MemberDAO();
	}

	public MemberDAO() {
		try {
			Class.forName(Serverinfo.DRIVER_NAME);
			
			System.out.println("Driver Loading Success...!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(Serverinfo.URL, Serverinfo.USER, Serverinfo.PASSOWRD);
		return conn;
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		
		ps.close();
		conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		
		rs.close();
		closeAll(ps,conn);
	}

	@Override
	public void inserMember(MemberVO vo) throws SQLException {
		Connection conn = getConnection();
		String query = "INSERT INTO MEMBER(ID, PASSWORD, NAME, AGE) VALUES(?,?,?,?) ";
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, vo.getId());
		ps.setString(2,vo.getPassword());
		ps.setString(3,vo.getName());
		ps.setInt(4,vo.getAge());
		
		System.out.println(ps.executeUpdate()+" 명 가입 !");
		
		closeAll(ps,conn);
	}

	@Override
	public ArrayList<MemberVO> showAllMember() throws SQLException {
		Connection conn = getConnection();
		
		String query = "SELECT* FROM MEMBER";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ArrayList<MemberVO> list = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			list.add(new MemberVO(rs.getString("id"),rs.getString("password"),rs.getString("name"),rs.getInt("age")));
		}
		closeAll(rs,ps,conn);
		System.out.println(list);
		return list;
	}

	@Override
	public ArrayList<MemberVO> searchMember() throws SQLException {
		return null;
	}
}
