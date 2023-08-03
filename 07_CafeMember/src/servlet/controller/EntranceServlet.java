package servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.MemberVO;

/**
 * 회원 가입하면.. 정보를 바탕으로 MemberVO를 생성..ArrayList에 추가 ArrayList를 통째로 ServletContext에
 * 바인딩 출력 결과는 viewMember.jsp 페이지에서
 */
public class EntranceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;

	List<MemberVO> list = Collections.synchronizedList(new ArrayList<MemberVO>());

	public void init(ServletConfig config) throws ServletException {
		
		// 4. 객체 바인딩.. ServletContext에
//		context = config.getServletContext();
//		context.setAttribute("vo", list);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("voList", list);
		// 2. 폼값 받아서
		String name = request.getParameter("name");
		//int age = Integer.parseInt(request.getParameter("age"));
		int age = request.getParameter("age")!=null ? Integer.parseInt(request.getParameter("age")) : 0;
		String addr = request.getParameter("addr");
		
		System.out.println("1. 폼값을 받아온다");

		// 3. vo 객체 생성
		
		if(name!=null) {
		MemberVO vo = new MemberVO(name, age, addr);
	//	System.out.println("2. MemberVO 생성한다");
		
		list.add(vo);
		}
		System.out.println("3. MemberVO를 List에 저장한다");
		// 5. ViewServlet (view)한테 결과 (이름, 나이, 주소) 출력

		
		// 내비게이션
		RequestDispatcher rdp = request.getRequestDispatcher("result.jsp"); 
		// PrintWriter out = response.getWriter(); 대신 설정
		rdp.forward(request, response); // 이때 위에 설정한 페이지로 이동됨 result.jsp로
		
		
//		PrintWriter out = response.getWriter();
//		out.println("<a href='result.jsp'>회원정보내놔라</a>");
//		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
