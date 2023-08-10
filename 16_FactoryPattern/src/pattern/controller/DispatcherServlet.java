package pattern.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DispatcherServlet ~~~");
		String command = request.getParameter("command");
		String path = "index.jsp";
		
		System.out.println("command :: " + command);

		// Factory controller
		Controller controller = ControllerFactory.getInstance().createController(command);
		try {
			path = controller.execute(request, response);
			System.out.println("path :: " + path);
		} catch (Exception e) {}

		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
