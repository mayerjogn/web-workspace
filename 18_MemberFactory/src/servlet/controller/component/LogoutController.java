package servlet.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.controller.Controller;
import servlet.controller.ModelAndView;

public class LogoutController implements Controller{

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String path = "";
		if(session.getAttribute("vo")!=null) {
			session.invalidate();
			path= "views/logout.jsp";
		}			
		
		return new ModelAndView(path);
	}

}
