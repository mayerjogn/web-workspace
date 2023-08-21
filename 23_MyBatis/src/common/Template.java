package common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {

	public static SqlSession getSqlSession() {
	
		SqlSession session = null;
		String resource = "/mybatis-config.xml";
		// 1. mybatis-config.xml의 설정 정보를 InputStream 객체를 통해 읽어온다.
		
		try {
			InputStream stream = Resources.getResourceAsStream(resource);
			
		
			SqlSessionFactoryBuilder bulider = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = bulider.build(stream);
			
			session = factory.openSession(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
		
	}
}
