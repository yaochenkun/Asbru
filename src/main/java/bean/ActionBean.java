package bean;

import java.sql.Timestamp;

public class ActionBean {
	
	private String name;
	private Timestamp time;
	
	public ActionBean(String name, Timestamp time) {

		this.name = name;
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
