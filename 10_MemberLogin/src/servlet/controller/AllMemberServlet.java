package servlet.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.dao.MemberDAO;
import servlet.model.vo.MemberDTO;

public class AllMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
//		String name = request.getParameter("name");
//		String address = request.getParameter("address");
		
		// dao 불러오기만 하면됨 세션은 상태 유지를 해야하는 데이터들에만 사용 
//		MemberDAO dao = new MemberDAO();
		try {
			ArrayList<MemberDTO> list = MemberDAO.getInstance().showAllMember();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/allShow.jsp").forward(request, response);
		} catch (SQLException e) {}
		
		request.getRequestDispatcher("allShow.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
