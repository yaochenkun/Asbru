package plan;



import java.sql.Timestamp;
import bean.ResultBean;

//计划基类
public abstract class PlanBase {
	
	protected String name;
	protected String type;
	abstract ResultBean execute(int taskId, Timestamp beginTime);
	
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
}
