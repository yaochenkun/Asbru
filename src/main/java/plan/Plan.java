package plan;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bean.ResultBean;
import constants.AsbruNode;
import constants.Order;
import constants.ResultState;
import constants.Type;
import dao.ResultDAO;

//�ƻ���
public class Plan extends PlanBase{

	private String order;
	private List<PlanBase> subPlanList;
	private Set<String> mandaPlanSet;
	
	Plan(String name) {
		
		super(name);
		type = Type.PLAN;
		order = Order.PARALLEL;
		subPlanList = new ArrayList<PlanBase>();
		mandaPlanSet = new HashSet<String>();
	}
	
	@Override
	ResultBean execute(int taskId, Timestamp beginTime) {
		
		ResultBean resultBean;
		if((Order.SEQUENTIALLY).equals(this.order))
			resultBean = seqExecute(taskId, beginTime);
		else
			resultBean = parExecute(taskId, beginTime);
		
		return resultBean;
	}
	
	//��ʱ��˳���йص�ִ�в�������plan�����ʱ��˳��Բ��������÷� + �����������״̬��
	ResultBean seqExecute(int taskId, Timestamp beginTime){
		
		PlanDirector planDirector = PlanDirector.getInstance();
		
		float state_completed_count = 0; //��plan����ɵ���plan����
		int   state_considered_count = 0; //��plan��һ��δ��ɵ���plan����
		float score = 0; //�÷�
		float fullScore = 0; //����
		Timestamp lasttime = beginTime; //���ʱ��
		float belief = 0.0f; //���Ŷ�
		StringBuilder reason = new StringBuilder(""); //��������

		//�����������е�plan
		for(PlanBase subPlan : this.subPlanList) {
			ResultBean result = subPlan.execute(taskId, beginTime);
			
			//1.�����÷ֲ��ۼӵ÷�
			boolean penalty = false; //�Ƿ���ʱ��˳�򲻶Ա��۷ֵı�ʶ
			if(!(result.getState()).equals(ResultState.CONSIDERED)) { //����ֻ�����ɻ���������
				if(result.getFinishTime().getTime() >= lasttime.getTime()){ //ʱ����֮�󣬶Եģ�����time
					lasttime = result.getFinishTime();
				} else { //ʱ����֮ǰ�����ԣ�������time���۷�
					result.setScore(result.getScore() - planDirector.getPenalScore());
					penalty = true;
				}
			}
			score += result.getScore();
			fullScore += result.getFullScore();
			
			//2.������ǰsubplan�����״̬����ǿ�Ƽƻ�����Ӱ�����״̬��
			if((this.mandaPlanSet).contains(AsbruNode.all) || (this.mandaPlanSet).contains(subPlan.getName())){
				if(	penalty == false && (result.getState()).equals(ResultState.COMPLETED)){ //�������ǿ�Ƶļƻ��ż�������������
					state_completed_count++;
					reason.append(result.getName()).append(" - <b class='text-success'>completed</b><br>"); 
				} else if((result.getState()).equals(ResultState.CONSIDERED)){ //δ���
					state_considered_count++;
					reason.append(result.getName()).append(" - <b class='text-error'>considered</b><br>");
				} else {
					//�����
					state_completed_count += 1 - planDirector.getPENAL_RATE(); //�ͷ����Ӳ���һ����ֻ�ܼӰ��
					if(penalty == true)
						reason.append(result.getName()).append(" - <b class='text-warning'>activated and break time order</b><br>");
					else 
						reason.append(result.getName()).append(" - <b class='text-warning'>activated</b><br>");
				}
			}
		}
		
		//4.������plan�������������ȷ�������plan�����״̬�����Ŷ�
		//��ø�plan��ǿ�Ƽƻ�����
		int expectedCompletedCount = mandaPlanSet.size(); 
		if(mandaPlanSet.contains(AsbruNode.all)) 
			expectedCompletedCount = subPlanList.size();
		
		//4.1 �������Ŷ�
		belief = state_completed_count * 1.0f / expectedCompletedCount;
		
		//4.2 �������״̬
		String state = ResultState.ACTIVATED; //��ʼ��Ϊ�������
		if((int)state_completed_count == expectedCompletedCount) //ȫ�����
			state = ResultState.COMPLETED;
		else if(state_considered_count == expectedCompletedCount) //ȫ��δ���
			state = ResultState.CONSIDERED;

		//5.��װ�� �÷�+���״̬�����ɵ�ǰ�����plan��result
		ResultBean resultBean = new ResultBean();
		resultBean.setName(this.name);
		resultBean.setType(this.type);
		resultBean.setScore(score);
		resultBean.setFullScore(fullScore);
		resultBean.setState(state);
		resultBean.setFinishTime(lasttime); //��Ϊ��ʱ��˳���йأ�����ʱ��������һ����Ч��plan�����ʱ��time
		resultBean.setBelief(belief);
		resultBean.setReason(reason.toString());
		resultBean.setTaskId(taskId);
				
		//6.��Resultд�����ݿ�
		ResultDAO.write(resultBean);
		return resultBean;
	}

	
	//��ʱ��˳���޹ص�ִ�в�������plan�����������״̬��
	ResultBean parExecute(int taskId, Timestamp beginTime){

		PlanDirector planDirector = PlanDirector.getInstance();
		
		int state_completed_count = 0; //��plan����ɵ���plan����
		int state_considered_count = 0; //��plan��һ��δ��ɵ���plan����
		float score = 0; //�÷�
		float fullScore = 0; //����
		Timestamp lasttime = beginTime;
		float belief = 0.0f; //���Ŷ�
		String reason = ""; //��������
		
		//�����������е�plan
		for(PlanBase subPlan : this.subPlanList) {
			ResultBean result = subPlan.execute(taskId, beginTime);
			
			//0.��¼ʱ��
			if(result.getFinishTime().getTime() >= lasttime.getTime()) //ʱ����֮��Ÿ���time
				lasttime = result.getFinishTime();
			
			//1.�ۼӵ÷֡�����
			score += result.getScore();
			fullScore += result.getFullScore();
			
			//2.�������״̬
			if((this.mandaPlanSet).contains(AsbruNode.all) || (this.mandaPlanSet).contains(subPlan.getName())){
				if((result.getState()).equals(ResultState.COMPLETED)){ //�������ǿ�Ƶļƻ��ż�������������
					state_completed_count++;
					reason += result.getName() + " - <b class='text-success'>completed</b><br>"; 
				} else if((result.getState()).equals(ResultState.CONSIDERED)){ //δ���
					state_considered_count++;
					reason += result.getName() + " - <b class='text-error'>considered</b><br>"; 
				} else {
					//�����
					state_completed_count += 1 - planDirector.getPENAL_RATE(); //�ͷ����Ӳ���һ����ֻ�ܼӰ��
					reason += result.getName() + " - <b class='text-warning'>activated</b><br>";
				}
			}
		}
		
		//4.������plan�������������ȷ�������plan�����״̬
		//��ø�plan��ǿ�Ƽƻ�����
		int expectedCompletedCount = mandaPlanSet.size(); 
		if(mandaPlanSet.contains(AsbruNode.all)) 
			expectedCompletedCount = subPlanList.size();

		//4.1 �������Ŷ�
		belief = state_completed_count * 1.0f / expectedCompletedCount;

		//4.2 �������״̬
		String state = ResultState.ACTIVATED; //��ʼ��Ϊ�������
		if((int)state_completed_count == expectedCompletedCount) //ȫ�����
			state = ResultState.COMPLETED;
		else if(state_considered_count == expectedCompletedCount) //ȫ��δ���
			state = ResultState.CONSIDERED;

		//5.��װ�� �÷�+���״̬�����ɵ�ǰ�����plan��result
		ResultBean resultBean = new ResultBean();
		resultBean.setName(this.name);
		resultBean.setType(this.type);
		resultBean.setScore(score);
		resultBean.setFullScore(fullScore);
		resultBean.setState(state);
		resultBean.setFinishTime(lasttime);
		resultBean.setBelief(belief);
		resultBean.setReason(reason);
		resultBean.setTaskId(taskId);

		//6.��Resultд�����ݿ�
		ResultDAO.write(resultBean);
		return resultBean; 
	}
	
	
	
	

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<PlanBase> getSubPlanList() {
		return subPlanList;
	}

	public void setSubPlanList(List<PlanBase> subPlanList) {
		this.subPlanList = subPlanList;
	}

	public Set<String> getMandaPlanSet() {
		return mandaPlanSet;
	}

	public void setMandaPlanSet(Set<String> mandaPlanSet) {
		this.mandaPlanSet = mandaPlanSet;
	}
}
