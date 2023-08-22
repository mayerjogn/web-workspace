package model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import model.vo.StudentVO;

public class StudentDAO {

	private static StudentDAO dao = new StudentDAO();
	public StudentDAO() {}
	public static StudentDAO getInstance() {
		return dao;
	}			
	//, String name
	public List<StudentVO> showStudent(SqlSession sqlSession,String name){
//		System.out.println(name);                      
		return sqlSession.selectList("studentMapper.showStudent", name);
	}
	
//	public List<StudentVO> AllStudentShow(SqlSession sqlSession, List<String> list){
//		return sqlSession.selectList("studentMapper.findStudent");
//	}
	
}