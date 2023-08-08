package servlet.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.jar.Attributes.Name;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.MemberVO;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 세션을 하나 받아온다.. request.getSession()
		// 클라이언트가 요청하는 순간 만들어져서 request로만 만들어짐
		HttpSession session = request.getSession();
		
		// 2. 폼에 입력된 값을 가지고 객체 생성... MemberVO로 생성
		String id = request.getParameter("id");
		String password = request.getParameter("password");		
		MemberVO vo = new MemberVO(id, password, "꼬랑","인천 부평"); // DAO가 생략된 상태임
		
		// 3. 세션에 바인딩
		session.setAttribute("vo", vo);
		
		// 4. 네비게이션
		PrintWriter out = response.getWriter();
		out.println("<a href=ProductServlet>ProductServlet...</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
