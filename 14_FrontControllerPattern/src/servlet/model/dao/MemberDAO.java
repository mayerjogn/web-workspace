package servlet.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ServerInfo;
import servlet.model.vo.MemberVO;

public class MemberDAO implements MemberDAOTemplate {
	
	// 싱글톤 패턴 - 클래스의 객체가 항상 하나만 존재하도록 할 경우
	/*
	 * DAO에서 사용하는 이유는 DAO를 반복적으로 생성하고 해제하는 것은 비효율적이기 때문
	 * 
	 * 객체지향적 설계를 위해 싱글톤 패턴은 객체지향적 설계 원칙을 준수함 -> 딱한번 존재해서 중앙에서 처리한다.
	 * 
	 * 주의할 점은 싱글톤은 전역 상태를 가질 수 있으므로 오남용하면 애플리케이션의 복잡성이 증가한다.
	 * */
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {}
	}
	public static MemberDAO getInstance() {
		return dao;
	}

	@Override
	public Connection getConnection() throws SQLException {
		
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		System.out.println("DB Connection..!!");
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
	public void registerMember(MemberVO vo) throws SQLException {
		Connection conn = getConnection();
//		String query =;
		
		System.out.println("id : " + vo.getId());
		System.out.println("password : " + vo.getPassword());
		System.out.println("name : " + vo.getName());
		System.out.println("address : " + vo.getAddress());
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO member(id, password, name, address) VALUES(?, ?, ?, ?)");
		
		ps.setString(1, vo.getId());
		ps.setString(2, vo.getPassword());
		ps.setString(3, vo.getName());
		ps.setString(4, vo.getAddress());
		

		System.out.println(ps.executeUpdate());
		
		closeAll(ps,conn);
		
	}

	@Override
	public MemberVO login(String id, String password) throws SQLException {
		Connection conn = getConnection();
		String query = "SELECT * FROM member WHERE id=? and password=?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, id);
		ps.setString(2, password);
		
		System.out.println("login :: " + password);
		
		ResultSet rs =ps.executeQuery();
		MemberVO vo = null;
		if(rs.next()) {
			vo = new MemberVO();
			vo.setId(rs.getString("id"));
			vo.setPassword(rs.getString("password"));
			vo.setName(rs.getString("name"));
			vo.setAddress(rs.getString("address"));
		}
		closeAll(rs, ps, conn);
		return vo;
	}

	@Override
	public MemberVO findByIdMember(String id) throws SQLException {
		Connection conn = getConnection();
		String query = "SELECT * FROM member WHERE id=?";
		PreparedStatement ps =conn.prepareStatement(query);
		
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery(); // SELECT문으로 받는 정보니까 rs 쓰고 execute사용
		MemberVO vo = null;
		if(rs.next()) {
			vo = new MemberVO();
			vo.setId(rs.getString("id"));
			vo.setPassword(rs.getString("password"));
			vo.setName(rs.getString("name"));
			vo.setAddress(rs.getString("address"));
		}
		closeAll(rs, ps, conn);
		return vo;
	}

	@Override
	public ArrayList<MemberVO> showAllMember() throws SQLException {
		
		Connection conn = getConnection();
		String query = "SELECT* FROM MEMBER";
		PreparedStatement ps = conn.prepareStatement(query);
				
		ResultSet rs = ps.executeQuery();
		ArrayList<MemberVO> list = new ArrayList<>();
		
		while(rs.next()) {
			MemberVO vo = new MemberVO();
			vo.setId(rs.getString("id"));
			vo.setPassword(rs.getString("password"));
			vo.setName(rs.getString("name"));
			vo.setAddress(rs.getString("address"));
			list.add(vo);
		}
		closeAll(rs, ps, conn);
		return list;		
	}
	
	public void updateMember(MemberVO vo) throws SQLException{
		
			Connection conn =  getConnection();
			String query = "UPDATE member SET password = ?, name = ?, address = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, vo.getPassword());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getAddress());
			ps.setString(4, vo.getId());
			
			ps.executeUpdate();	
			
			closeAll(ps,conn);
			}
		
public static void main(String[] args) {
	// 메인에서 register login 확인
//	MemberDAO dao = new MemberDAO();
//	
//	MemberVO vo = new MemberVO();
//	
//	vo.setId("user1");
//	vo.setPassword("user1");
//	vo.setName("꼬랑");
//	vo.setAddress("인천");
//		
//	try {
//		dao.registerMember(vo);
//		vo=dao.login("user1", "user1");
//		System.out.println("name : "+ vo.getName());
//		System.out.println("address : "+ vo.getAddress());
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
}
}