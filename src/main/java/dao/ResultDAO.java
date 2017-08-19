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

	//�����ݿ�д��Ʒֽ��
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
	
	//�����ݿ��ȡbeginTime-endTime֮���result�б�
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
