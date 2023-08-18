package member.controller;

import member.controller.component.AllMemberController;
import member.controller.component.FindController;
import member.controller.component.LoginController;
import member.controller.component.LogoutController;
import member.controller.component.RegisterController;
import member.controller.component.UpdateController;

// ControllerFactory랑 역할은 같음
public class HandlerMapping {

	private static HandlerMapping handler = new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return handler;
	}
	
	public Controller createController(String command) {
		Controller controller = null;
		
		if(command.equals("find.do")) {
			controller = new FindController();
		}else if(command.equals("login.do")) {
			controller = new LoginController();
		}else if(command.equals("update.do")) {
			controller = new UpdateController();
		}else if(command.equals("allmember.do")) {
			controller = new AllMemberController();
		}else if(command.equals("logout.do")) {
			controller = new LogoutController();
		}else if(command.equals("register.do")) {
			controller = new RegisterController();
		}
		
		return controller;
	}
}
