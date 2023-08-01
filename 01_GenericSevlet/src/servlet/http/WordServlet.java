package servlet.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WordSevlet
 */
public class WordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 1. 폼값 받아서
		 * 2. 다시 웹브라우저로 전송...
		 * */
		String result=request.getParameter("word"); // form의 지정한 name값
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Client Send Data<h2>");
		out.println("<p>"+ result + "</p>");
		out.println("</body></html>");
		out.close();
	}

}
