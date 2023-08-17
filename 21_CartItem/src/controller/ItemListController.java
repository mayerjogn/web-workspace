package controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.ItemDAO;

// itemview.jsp랑 연결되면서 itemView.do
public class ItemListController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<Item> list = ItemDAO.getInstance().getAllItem();
		System.out.println("list :: " + list);
		request.setAttribute("list", list);

		ArrayList<String> fruits = new ArrayList<>();
		// 쿠키 정보 받아오는 로직
		Cookie[] cs = request.getCookies();
		if(cs!=null) {
			for(Cookie c : cs) {
				if(c.getName().startsWith("fruit-")) {
					fruits.add(c.getValue());
				}
			}
		}
		request.setAttribute("fruits", fruits);
		return new ModelAndView("itemList.jsp");
	}

}
