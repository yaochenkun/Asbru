package bean;

import java.sql.Timestamp;

public class ActionBean {
	
	private int id;
	private String name;
	private Timestamp time;

	public ActionBean() {
		
	}
	
	//≤‚ ‘”√£°÷Æ∫Û…æ≥˝
	public ActionBean(String name, Timestamp time) {
		super();
		this.name = name;
		this.time = time;
	}
	
	
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
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
