package member.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.SqlSessionTemplate;
import member.model.dao.MemberDAO;
import member.model.vo.MemberVO;

// DAO - service - Controller 순으로 연결됨
public class MemberService {

	public int registerMember(MemberVO vo) {
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		int result = MemberDAO.getInstance().registerMember(sqlSession, vo);

		if (result > 0)
			sqlSession.commit(); // commit은 DML 구문에서만 필요함 insert update delete 데이터 조작하는 것들에서만 필요
		sqlSession.close();
		return result;
	}

	// shoAllMember
	public List<MemberVO> showAllMember() {
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		List<MemberVO> result = MemberDAO.getInstance().showAllmember(sqlSession);		
			sqlSession.close();
		return result;

	}

	// findByIdMember
	public MemberVO findByIdMember(String id) {
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		MemberVO result = MemberDAO.getInstance().findByIdMember(sqlSession, id);
			sqlSession.close();
		return result;
	}

	// login
	public MemberVO login(MemberVO vo) {
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		MemberVO result = MemberDAO.getInstance().login(sqlSession, vo);
			sqlSession.close();
		return result;
	}

	// updateMember
	public int updateMember(MemberVO vo) {
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		int result = MemberDAO.getInstance().updateMember(sqlSession, vo);
		if (result > 0)sqlSession.commit();
			sqlSession.close();
		return result;
	}
}
