package dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.ActionBean;
import mapper.ActionMapper;
import mapper.SqlSessionHelper;

//驱动ActionBean与数据库交互的类
public class ActionDAO {
	
	private static final Class<ActionMapper> mapperClass = ActionMapper.class;
	
	//从数据库读入用户输入的动作
	public static List<ActionBean> read(Timestamp beginTime, Timestamp endTime) {
		
		SqlSession session = null;
		List<ActionBean> actionBeanList = null;
		try {
			session = SqlSessionHelper.getSessionFactory().openSession();
			ActionMapper mapper = session.getMapper(mapperClass);
			actionBeanList = mapper.selectActionsByPeriod(beginTime, endTime);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return actionBeanList;
	}
}
