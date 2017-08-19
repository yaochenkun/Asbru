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
	private float belief; //置信度
	private String reason; //置信理由
	private int taskId; //对应的总计划（morning/afternoon/evening）

	public int getId() {
		return id;
	}

	public float getBelief() {
		return belief;
	}

	public void setBelief(float belief) {
		this.belief = belief;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
}
