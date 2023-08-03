package servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.MemberVO;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로직은 여기서 작성!
		/*
		 * 2. 폼값 받기
		 * 3. vo 객체 생성
		 * 4. 객체 바인딩.. ServletContext에
		 * 5.ViewServlet (view)한테 결과 (이름, 나이, 주소) 출력
		 * 
		 * */
		// 1. 양방향 한글처리
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8"); Filter로 작성으로 변경
		
		// 2. 폼값 받아서
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String addr = request.getParameter("addr");
			
		// 3. vo 객체 생성
		MemberVO vo = new MemberVO(name,age,addr);
			
		// 4. 객체 바인딩.. ServletContext에
		context = getServletContext();
		context.setAttribute("vo", vo); // 안에 들어가는 " "는 걍 변수 지정한다 생각하면됨 뒤에가 객체임
		
		// 5. ViewServlet (view)한테 결과 (이름, 나이, 주소) 출력		
		PrintWriter out = response.getWriter();
		
//		out.println("<a href='View?name="+name + "&age="+age+ "&addr="+addr+ "'</a>");	 //VO로 옮겨 담았음	
//		out.println("<a href='view'>ViewServlet 결과 확인하러 가기</a>");
		out.println("<a href='result.jsp'>결과 확인하러 가기</a>");
		out.close();
		
	
		// a링크로 ViewServlet 결과 확인하러 가기
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
