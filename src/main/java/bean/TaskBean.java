package bean;


//ҽ���ƶ����ܼƻ��ࣨmorning��afternoon��evening��
public class TaskBean {
	
	private int id;
	private String name; //����
	private int beginTime; //��ʼʱ��(�����Сʱ�㣬��13)
	private int endTime; //��ֹʱ��
	
	
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
