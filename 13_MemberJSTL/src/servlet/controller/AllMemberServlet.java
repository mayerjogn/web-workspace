package servlet.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.dao.MemberDAO;
import servlet.model.service.MemberService;
import servlet.model.vo.MemberDTO;
@WebServlet("/member/all")

public class AllMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ArrayList<MemberDTO> list = new MemberService().showAllMember();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/allShow.jsp").forward(request, response);
			//views 앞에 / 붙이기
		} catch (SQLException e) {}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
