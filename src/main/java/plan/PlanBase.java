package plan;


import bean.ResultBean;

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
	
	@Override
	public String toString() {
		String res = "！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！\n兆各=" + name + ", 窃侏=" + type + "\n！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！";
		return res;
	}
}
