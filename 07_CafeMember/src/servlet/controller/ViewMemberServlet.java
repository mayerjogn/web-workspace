package servlet.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.MemberDAO;
import servlet.model.MemberVO;

public class ViewMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. DAO 리턴 받기

		System.out.println("~~~~~~");
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list= null;
		try {
			list= dao.showAllMember();
				
			}catch (SQLException e) {
			e.printStackTrace();
			}
	
		// 2. 바인딩 (어트리뷰트 사용)
		request.setAttribute("list", list);
		
		System.out.println(list);
		
		// 3. 네비게이션 --> viewMember.jsp	
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
			
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
