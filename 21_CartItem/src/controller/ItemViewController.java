package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.ItemDAO;
import controller.ModelAndView;

public class ItemViewController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Item item= ItemDAO.getInstance().getItem(id);
		String path = "itemView.jsp";
		request.setAttribute("id", id);
		
		return new ModelAndView(path);
	}

}
