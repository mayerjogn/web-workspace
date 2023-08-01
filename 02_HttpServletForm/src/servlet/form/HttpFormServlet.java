package servlet.form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HttpFormServlet
 */
public class HttpFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * 한글처리는 양방향을 다 처리해줘야한다 request랑 response 둘다를
		 */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		String gender = request.getParameter("gender");
		PrintWriter out = response.getWriter();
		
	
		out.println("<h2>정보를 출력합니다...</h2>");
		out.println("<p>당신의 아이디는" + id + "</p>");
		out.println("<p>당신의 비밀번호는" + pwd + "</p>");

		String[] menuList = request.getParameterValues("menu");
		// 불러오는 값이 여러개일때 배열사용해서 반복문이나 조건문으로 사용할 수 있음
		
		
		out.println("<ul>");
		for (String menu : menuList) {
			out.println("<li>" + menu + "</li>");
		}

//		for(int i =0; i<menuList.length; i++) {
//			out.println("<p>당신의 기타는 "+ menuList[i] + "</p>");		
//		}
		out.println("</ul>");
		
		// 당신의 성별은 --> form.html 라디오 사용
	
	
		out.println("<p>당신의 성별은 "+ (gender.charAt(0)=='M' ? "남":"여") +"</p>");	
		
		out.close();
	}

}
