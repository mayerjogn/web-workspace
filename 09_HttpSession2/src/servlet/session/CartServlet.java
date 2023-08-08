package servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 기존 세션을 하나 받아온다.
		HttpSession session = request.getSession();
		
		// 2. 세션에 있는 정보 출력
		PrintWriter out = response.getWriter();
		out.println("VO:: " + session.getAttribute("vo")+ "<br>");
		out.println("PRODUCT:: " + session.getAttribute("PRODUCT")+"<br>");
		
		// 3. 현재 세션에 정보가 있다면 세션을 코로쓰!
		if(session.getAttribute("vo")!=null) {
			session.invalidate();
			out.println("<script>");
			out.println("alert('로그아웃!')");
			out.println("location.href='ResultServlet'"); // 로그아웃 하는 순간 로그인 정보가 저장된 html로 보냄
			out.println("</script>");
		}else { // 4. 세션 정보가 없다면 처음부터 index.html
			response.sendRedirect("index.html");
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
