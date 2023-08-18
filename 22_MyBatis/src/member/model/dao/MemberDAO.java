package member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import member.model.vo.MemberVO;

public class MemberDAO{
	
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		return dao;
	}
	/*
	 * SqlSession을 통한 쿼리 실행
	 * - insert(String mapper, Object param) : int - DB에 데이터를 입력하고자 할 때 사용
	 * - update(String mapper, Object param) : int -DB의 데이터를 수정하고자 할 때 사용
	 * - delete(String mapper, Object param) : int - DB의 데이터를 삭제하고자 할 때 사용
	 * 
	 * - selectOne(String mapper, Object param) : Object - 하나의 객체만을 받고자 할 때 사용
	 * - selectList(String mapper, Object param) : List<E> - 결과에 대한 값을 List로 받고자 할 때 사용
	 * - selectMap(String mapper, Object param, String mapKey) : Map<key,value> - 결과에 대한 값을 Map으로 받고자 할 때 사용
	 *  
	 * */
	public int registerMember(SqlSession sqlSession, MemberVO vo) {
		return sqlSession.insert("memberMapper.registerMember", vo); // 파라미터 값 vo를 넘김
		
	}

	// showAllmember 
	public List<MemberVO> showAllmember(SqlSession sqlSession) {
		return sqlSession.selectList("memberMapper.showAllmember");
		
	}
	
	// findByIdMember
	public MemberVO findByIdMember(SqlSession sqlSession,String id) {
		return sqlSession.selectOne("memberMapper.findByIdMember",id);		
	}

	// login
	public MemberVO login(SqlSession sqlSession,MemberVO vo) {
		return sqlSession.selectOne("memberMapper.login",vo);
	}
		
	// updateMember
	public int updateMember(SqlSession sqlSession, MemberVO vo ) {
		return sqlSession.update("memberMapper.updateMember",vo);	
	}
}
