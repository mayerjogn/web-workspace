package servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 서버에 요청하는 순간 무조건 만들어지기 때문에 request로 가져오면 된다.
				// 로그인 했을 때 회원 정보를 계속 가지고 있는 경우 - session, cookie
				// 쿠키는 String으로 문자열로만 저장됨 Session은 객체로 저장할 수 잇음(보안성도 더 좋음)
		
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
