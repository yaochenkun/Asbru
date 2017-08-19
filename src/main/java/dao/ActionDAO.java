package dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.ActionBean;
import mapper.ActionMapper;
import mapper.SqlSessionHelper;

//����ActionBean�����ݿ⽻������
public class ActionDAO {
	
	private static final Class<ActionMapper> mapperClass = ActionMapper.class;
	
	//�����ݿ�����û�����Ķ���
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
