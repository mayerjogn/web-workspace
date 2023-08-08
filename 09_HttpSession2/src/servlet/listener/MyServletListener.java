package servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MyServletListener implements ServletContextListener {

	private ServletContext context;
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("contextDestroyed...");
    }

    
    
    // dd(web.xml)읽고 - ServletContext를 생성 - ServletContextEvent가 발생함 - 리스너를 감지함 - 호출
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("contextInitialized...");
    	
    	// sce이벤트에서 ServletContext를 받아온다.
    	context = sce.getServletContext();
    	context.setAttribute("context", "contextData..01");
    }
	
}
