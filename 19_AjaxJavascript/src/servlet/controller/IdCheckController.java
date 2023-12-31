package servlet.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.controller.Controller;
import servlet.controller.ModelAndView;
import servlet.model.dao.MemberDAO;
import servlet.model.vo.MemberVO;

public class IdCheckController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		String id = request.getParameter("id");		
		MemberVO vo = MemberDAO.getInstance().findByIdMember(id);
			
		boolean flag = false;
		
		// 비동기방식
		
		if(vo!=null) {
			flag = true;
		}
		PrintWriter out = response.getWriter(); 
		out.print(flag);
//		if(vo!=null) {
//			out.print("중복된 아이디입니다.");
//		}else {
//			out.print("사용 가능한 아이디입니다.");
//		}
		return null;
	}

}
