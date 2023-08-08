package servlet.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ServerInfo;
import servlet.model.vo.MemberDTO;

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
//	public MemberDAO() {
//		try {
//			Class.forName(ServerInfo.DRIVER_NAME);
//			
//			System.out.println("Driver Loading Success...!!");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

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
	public void registerMember(MemberDTO dto) throws SQLException {
		Connection conn = getConnection();
//		String query =;
		
		System.out.println("id : " + dto.getId());
		System.out.println("password : " + dto.getPassword());
		System.out.println("name : " + dto.getName());
		System.out.println("address : " + dto.getAddress());
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO member(id, password, name, address) VALUES(?, ?, ?, ?)");
		
		ps.setString(1, dto.getId());
		ps.setString(2, dto.getPassword());
		ps.setString(3, dto.getName());
		ps.setString(4, dto.getAddress());
		

		System.out.println(ps.executeUpdate());
		
		closeAll(ps,conn);
		
	}

	@Override
	public MemberDTO login(String id, String password) throws SQLException {
		Connection conn = getConnection();
		String query = "SELECT * FROM member WHERE id=? and password=?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, id);
		ps.setString(2, password);
		
		ResultSet rs =ps.executeQuery();
		MemberDTO dto = null;
		if(rs.next()) {
			dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setPassword(rs.getString("password"));
			dto.setName(rs.getString("name"));
			dto.setAddress(rs.getString("address"));
		}
		closeAll(rs, ps, conn);
		return dto;
	}

	@Override
	public MemberDTO findByIdMember(String id) throws SQLException {
		Connection conn = getConnection();
		String query = "SELECT * FROM member WHERE id=?";
		PreparedStatement ps =conn.prepareStatement(query);
		
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery(); // SELECT문으로 받는 정보니까 rs 쓰고 execute사용
		MemberDTO dto = null;
		if(rs.next()) {
			dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setPassword(rs.getString("password"));
			dto.setName(rs.getString("name"));
			dto.setAddress(rs.getString("address"));
		}
		closeAll(rs, ps, conn);
		return dto;
	}

	@Override
	public ArrayList<MemberDTO> showAllMember() throws SQLException {
		
		Connection conn = getConnection();
		String query = "SELECT* FROM MEMBER";
		PreparedStatement ps = conn.prepareStatement(query);
				
		ResultSet rs = ps.executeQuery();
		ArrayList<MemberDTO> list = new ArrayList<>();
		
		while(rs.next()) {
			MemberDTO dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setPassword(rs.getString("password"));
			dto.setName(rs.getString("name"));
			dto.setAddress(rs.getString("address"));
			list.add(dto);
		}
		closeAll(rs, ps, conn);
		return list;
		
	}


public static void main(String[] args) {
	// 메인에서 register login 확인
	MemberDAO dao = new MemberDAO();
	
	MemberDTO dto = new MemberDTO();
	
	dto.setId("user1");
	dto.setPassword("user1");
	dto.setName("꼬랑");
	dto.setAddress("인천");
		
	try {
//		dao.registerMember(dto);
		dto=dao.login("user1", "user1");
		System.out.println("name : "+ dto.getName());
		System.out.println("address : "+ dto.getAddress());
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}