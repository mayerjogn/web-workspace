package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.ItemDAO;
import controller.ModelAndView;

public class ItemViewController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int itemId = Integer.parseInt(request.getParameter("id"));
		
		ItemDAO.getInstance().updateRecordCount(itemId); //조회수
		
		Item item = ItemDAO.getInstance().getItem(itemId);
		request.setAttribute("item", item);
		// 오늘 본 상품정보를 저장하는 쿠키 로직
		// 1) 쿠키 생성
		Cookie cookie = new Cookie("fruit-"+itemId,item.getPictureUrl());
		cookie.setMaxAge(24*60*60);
		// 2) 생성한 쿠키를 웹브라우저로 보냄
		response.addCookie(cookie);
			
		//String path = "itemView.jsp";
		return new ModelAndView("itemView.jsp");
	}

}
