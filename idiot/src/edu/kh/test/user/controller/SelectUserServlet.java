package edu.kh.test.user.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.test.user.model.dao.UserDAO;
import edu.kh.test.user.model.vo.UserDTO;

@WebServlet("/SelectUserServlet")
public class SelectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getParameter("userNO"); // 숫자로 바꿔주는 명령어가 기억이안나여..
		int userNo = 0;
		try {
			UserDTO vo = new UserDAO().selectUser(userNo);
			if(vo!=null) {
				request.setAttribute("vo", vo);
				request.getRequestDispatcher("WEB-INF/veiws/searchSuccess.jsp");
			}else {
				request.getRequestDispatcher("WEB-INF/veiws/searchFail.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
