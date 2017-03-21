package bean;

import java.sql.Timestamp;

public class ResultBean {
	
	private String name; //名称
	private String type; //类别：计划/动作
	private Timestamp time; //完成时间
	private int score; //得分
	private String state; //完成情况

	public ResultBean(String name, String type, Timestamp time, int score, String state) {
		this.name = name;
		this.type = type;
		this.time = time;
		this.score = score;
		this.state = state;
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
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
