package bean;


//医生制定的总计划类（morning、afternoon、evening）
public class TaskBean {
	
	private int id;
	private String name; //名称
	private int beginTime; //起始时间(存的是小时点，如13)
	private int endTime; //终止时间
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

}
