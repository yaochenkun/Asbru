package dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.ResultBean;
import mapper.ResultMapper;
import mapper.SqlSessionHelper;
public class ResultDAO {
	
	private static final Class<ResultMapper> mapperClass = ResultMapper.class;

	//向数据库写入计分结果
	public static void write(ResultBean resultBean){
    	
		SqlSession session = null;
		try {
			session = SqlSessionHelper.getSessionFactory().openSession();
			ResultMapper mapper = session.getMapper(mapperClass);
			mapper.addResultBean(resultBean);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	//从数据库读取beginTime-endTime之间的result列表
	public static List<ResultBean> read(int taskId, Timestamp beginTime, Timestamp endTime){
		
		SqlSession session = null;
		List<ResultBean> resultBeanList = null;
		try {
			session = SqlSessionHelper.getSessionFactory().openSession();
			ResultMapper mapper = session.getMapper(mapperClass);
			resultBeanList = mapper.selectResultBeanByPeriod(taskId, beginTime, endTime);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return resultBeanList;
	}
}
