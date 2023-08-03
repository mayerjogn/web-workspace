package servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.MemberVO;

public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletContext context;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		
		
//		String name = request.getParameter("name");
//		int age = Integer.parseInt(request.getParameter("age"));
//		String addr = request.getParameter("addr");
		
		/*
		 * 1. Attribute에 바인딩된 데이터를 받아오기
		 * 
		 * */
		
		// 1. Attribute에 바인딩된 데이터를 받아와서
		context = getServletContext();
		MemberVO vo =(MemberVO) context.getAttribute("vo");
		// (MemberVO)캐스팅은 형변환이라 생각하면됨 리턴이 오브젝트임 상위클래스를 하위클래스로
		
		//2. 웹브라우저로 출력하는 로직을 작성..
		PrintWriter out = response.getWriter();
		
		out.println("<p>이름 : "+vo.getName() + "<br>나이 : "+vo.getAge()+ 
				"<br>주소: "+vo.getAddr()+ "</p>");		
		out.close();
	
		
	}

}