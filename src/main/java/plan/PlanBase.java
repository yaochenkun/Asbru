package plan;


import java.sql.Timestamp;

import org.apache.ibatis.session.SqlSession;

import bean.ResultBean;
import mapper.ResultMapper;
import mapper.SqlSessionHelper;

//柴皿児窃
public abstract class PlanBase {
	
	protected String name;
	protected String type;
	abstract ResultBean execute();
	
	PlanBase(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	} 
	
	//�鯤�象垂亟秘柴蛍潤惚
	public void writeResult(ResultBean resultBean){
    	
		SqlSession session = null;
		try {
			session = SqlSessionHelper.getSessionFactory().openSession();
			ResultMapper mapper = session.getMapper(ResultMapper.class);
			mapper.addResultBean(resultBean);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
	@Override
	public String toString() {
		String res = "！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！\n兆各=" + name + ", 窃侏=" + type + "\n！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！";
		return res;
	}
}
