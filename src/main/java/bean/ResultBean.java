package bean;

import java.sql.Timestamp;

public class ResultBean {
	
	private int id; //编号
	private String name; //名称
	private String type; //类别：计划/动作
	private float score; //得分
	private float fullScore; //满分
	private String state; //完成情况
	private Timestamp finishTime; //完成时间
	
	
	@Override
	public String toString() {

		String res = "名称=" + name + ", 类型=" + type + ", 完成情况=" + state + ", 得分=" + score + ", 满分=" + fullScore + ", 时间=" + finishTime;
		return res;
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public float getFullScore() {
		return fullScore;
	}
	public void setFullScore(float fullScore) {
		this.fullScore = fullScore;
	}
}
