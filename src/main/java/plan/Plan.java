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

//计划类
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
	
	//与时间顺序有关的执行操作（父plan会根据时间顺序对不对修正得分 + 另外修正完成状态）
	ResultBean seqExecute(int taskId, Timestamp beginTime){
		
		PlanDirector planDirector = PlanDirector.getInstance();
		
		float state_completed_count = 0; //该plan下完成的子plan个数
		int   state_considered_count = 0; //该plan下一点未完成的子plan个数
		float score = 0; //得分
		float fullScore = 0; //满分
		Timestamp lasttime = beginTime; //完成时间
		float belief = 0.0f; //置信度
		StringBuilder reason = new StringBuilder(""); //置信理由

		//遍历旗下所有的plan
		for(PlanBase subPlan : this.subPlanList) {
			ResultBean result = subPlan.execute(taskId, beginTime);
			
			//1.修正得分并累加得分
			boolean penalty = false; //是否因时间顺序不对被扣分的标识
			if(!(result.getState()).equals(ResultState.CONSIDERED)) { //罚分只针对完成或半完成任务
				if(result.getFinishTime().getTime() >= lasttime.getTime()){ //时间在之后，对的，更新time
					lasttime = result.getFinishTime();
				} else { //时间在之前，不对，不更新time，扣分
					result.setScore(result.getScore() - planDirector.getPenalScore());
					penalty = true;
				}
			}
			score += result.getScore();
			fullScore += result.getFullScore();
			
			//2.修正当前subplan的完成状态（非强制计划不会影响完成状态）
			if((this.mandaPlanSet).contains(AsbruNode.all) || (this.mandaPlanSet).contains(subPlan.getName())){
				if(	penalty == false && (result.getState()).equals(ResultState.COMPLETED)){ //完成且是强制的计划才计数，否则不作数
					state_completed_count++;
					reason.append(result.getName()).append(" - <b class='text-success'>completed</b><br>"); 
				} else if((result.getState()).equals(ResultState.CONSIDERED)){ //未完成
					state_considered_count++;
					reason.append(result.getName()).append(" - <b class='text-error'>considered</b><br>");
				} else {
					//半完成
					state_completed_count += 1 - planDirector.getPENAL_RATE(); //惩罚，加不了一个，只能加半个
					if(penalty == true)
						reason.append(result.getName()).append(" - <b class='text-warning'>activated and break time order</b><br>");
					else 
						reason.append(result.getName()).append(" - <b class='text-warning'>activated</b><br>");
				}
			}
		}
		
		//4.根据子plan的完成数量最终确定这个父plan的完成状态、置信度
		//获得父plan的强制计划数量
		int expectedCompletedCount = mandaPlanSet.size(); 
		if(mandaPlanSet.contains(AsbruNode.all)) 
			expectedCompletedCount = subPlanList.size();
		
		//4.1 计算置信度
		belief = state_completed_count * 1.0f / expectedCompletedCount;
		
		//4.2 计算完成状态
		String state = ResultState.ACTIVATED; //初始化为部分完成
		if((int)state_completed_count == expectedCompletedCount) //全部完成
			state = ResultState.COMPLETED;
		else if(state_considered_count == expectedCompletedCount) //全部未完成
			state = ResultState.CONSIDERED;

		//5.包装： 得分+完成状态，生成当前这个父plan的result
		ResultBean resultBean = new ResultBean();
		resultBean.setName(this.name);
		resultBean.setType(this.type);
		resultBean.setScore(score);
		resultBean.setFullScore(fullScore);
		resultBean.setState(state);
		resultBean.setFinishTime(lasttime); //因为与时间顺序有关，所以时间戳是最后一个有效子plan的完成时间time
		resultBean.setBelief(belief);
		resultBean.setReason(reason.toString());
		resultBean.setTaskId(taskId);
				
		//6.把Result写入数据库
		ResultDAO.write(resultBean);
		return resultBean;
	}

	
	//与时间顺序无关的执行操作（父plan仅会修正完成状态）
	ResultBean parExecute(int taskId, Timestamp beginTime){

		PlanDirector planDirector = PlanDirector.getInstance();
		
		int state_completed_count = 0; //该plan下完成的子plan个数
		int state_considered_count = 0; //该plan下一点未完成的子plan个数
		float score = 0; //得分
		float fullScore = 0; //满分
		Timestamp lasttime = beginTime;
		float belief = 0.0f; //置信度
		String reason = ""; //置信理由
		
		//遍历旗下所有的plan
		for(PlanBase subPlan : this.subPlanList) {
			ResultBean result = subPlan.execute(taskId, beginTime);
			
			//0.记录时间
			if(result.getFinishTime().getTime() >= lasttime.getTime()) //时间在之后才更新time
				lasttime = result.getFinishTime();
			
			//1.累加得分、满分
			score += result.getScore();
			fullScore += result.getFullScore();
			
			//2.修正完成状态
			if((this.mandaPlanSet).contains(AsbruNode.all) || (this.mandaPlanSet).contains(subPlan.getName())){
				if((result.getState()).equals(ResultState.COMPLETED)){ //完成且是强制的计划才计数，否则不作数
					state_completed_count++;
					reason += result.getName() + " - <b class='text-success'>completed</b><br>"; 
				} else if((result.getState()).equals(ResultState.CONSIDERED)){ //未完成
					state_considered_count++;
					reason += result.getName() + " - <b class='text-error'>considered</b><br>"; 
				} else {
					//半完成
					state_completed_count += 1 - planDirector.getPENAL_RATE(); //惩罚，加不了一个，只能加半个
					reason += result.getName() + " - <b class='text-warning'>activated</b><br>";
				}
			}
		}
		
		//4.根据子plan的完成数量最终确定这个父plan的完成状态
		//获得父plan的强制计划数量
		int expectedCompletedCount = mandaPlanSet.size(); 
		if(mandaPlanSet.contains(AsbruNode.all)) 
			expectedCompletedCount = subPlanList.size();

		//4.1 计算置信度
		belief = state_completed_count * 1.0f / expectedCompletedCount;

		//4.2 计算完成状态
		String state = ResultState.ACTIVATED; //初始化为部分完成
		if((int)state_completed_count == expectedCompletedCount) //全部完成
			state = ResultState.COMPLETED;
		else if(state_considered_count == expectedCompletedCount) //全部未完成
			state = ResultState.CONSIDERED;

		//5.包装： 得分+完成状态，生成当前这个父plan的result
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

		//6.把Result写入数据库
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
