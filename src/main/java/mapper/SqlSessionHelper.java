package mapper;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//��ȡsession�����ĵ���
public class SqlSessionHelper {
	private static SqlSessionFactory instance;
	private static String configFilePath = "mybatis-config.xml";
	
	public synchronized static SqlSessionFactory getSessionFactory() throws IOException {
		if(instance == null) {
			try {
				InputStream inputStream = Resources.getResourceAsStream(configFilePath);
				instance = new SqlSessionFactoryBuilder().build(inputStream);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}
}
