package servlet.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ServerInfo;
import servlet.model.vo.MemberDTO;

//Repository는 dao 역할 (명칭차이일 뿐 ) - 시험

public class MemberRepository implements MemberDAOTemplate {

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
		
		System.out.println("login :: " + password);
		
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

	
	
	public void updateMember(MemberDTO dto) throws SQLException{
		
			Connection conn =  getConnection();
			String query = "UPDATE member SET password = ?, name = ?, address = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, dto.getPassword());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getAddress());
			ps.setString(4, dto.getId());
			
			ps.executeUpdate();		
			closeAll(ps,conn);
			}
		
public static void main(String[] args) {
	
}
}

