package dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.TaskBean;
import mapper.SqlSessionHelper;
import mapper.TaskMapper;

public class TaskDAO {
	
	private static final Class<TaskMapper> mapperClass = TaskMapper.class;
	
	//从数据库读取医生制定的所有task
	public static List<TaskBean> read(){
		
		SqlSession session = null;
		List<TaskBean> taskBeanList = null;
		try {
			session = SqlSessionHelper.getSessionFactory().openSession();
			TaskMapper mapper = session.getMapper(mapperClass);
			taskBeanList = mapper.selectTaskBean();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return taskBeanList;
	}
}
