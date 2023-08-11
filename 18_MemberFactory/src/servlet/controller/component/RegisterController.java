package servlet.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.controller.Controller;
import servlet.controller.ModelAndView;
import servlet.model.dao.MemberDAO;
import servlet.model.vo.MemberVO;

public class RegisterController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		String path = "";
		
		HttpSession session = request.getSession();
		MemberVO vo = new MemberVO(id, password, name, address);
		MemberDAO.getInstance().registerMember(vo);
		// 데이터 바인딩 - request, session, context의 차이는 영역의 차이임
		// setAttribute
		// request <--- 범위가 가장 좁음 요청이 들어오고 응답까지만
		// session <--- 로그인 하는 순간부터 로그아웃까지의 범위라고 봄
		// context <--  범위가 가장 넓지만 거의 사용하지 않음
		if(vo!=null) {
			session.setAttribute("vo", vo);
			path ="index.jsp";
		}
		return new ModelAndView(path);
	}

}
