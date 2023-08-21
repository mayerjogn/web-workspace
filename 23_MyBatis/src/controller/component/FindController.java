package controller.component;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.ModelAndView;
import model.service.StudentService;
import model.vo.StudentVO;

public class FindController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = request.getParameter("studentName");		
		List<StudentVO> list =new StudentService().showStudent(name);
		if(list!=null) {
			request.setAttribute("list",list);
		}
		
		return null;
	}

}
