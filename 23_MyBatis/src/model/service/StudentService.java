package model.service;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.Template;
import model.dao.StudentDAO;
import model.vo.StudentVO;

public class StudentService {

	
	
	public List<StudentVO> showStudent(String name){
		
		SqlSession sqlSession = Template.getSqlSession();
		List<StudentVO>list = new StudentDAO().showStudent(sqlSession, name);
			return list;
	}
//	public List<StudentVO> AllStudentShow(String[] idList){
//		SqlSession sqlSession = Template.getSqlSession();
//		List<String> list = Arrays.asList(idList);
//		List<StudentVO> student = StudentDAO.getInstance().AllStudentShow(sqlSession, list);
//			sqlSession.close();		
//		return student;
//	}
}
