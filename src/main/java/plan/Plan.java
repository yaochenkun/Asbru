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
	ResultBean execute() {
		
		ResultBean resultBean;
		if((Order.SEQUENTIALLY).equals(this.order))
			resultBean = seqExecute();
		else
			resultBean = parExecute();
		
		return resultBean;
	}
	
	//与时间顺序有关的执行操作（父plan会根据时间顺序对不对修正得分 + 另外修正完成状态）
	ResultBean seqExecute(){
		
		int state_completed_count = 0; //该plan下完成的子plan个数
		int state_considered_count = 0; //该plan下一点未完成的子plan个数
		float score = 0; //得分
		float fullScore = 0; //满分
		Timestamp lasttime = new Timestamp(0); //完成时间
		
		//遍历旗下所有的plan
		for(PlanBase subPlan : this.subPlanList) {
			ResultBean result = subPlan.execute();
			
			//1.修正得分并累加得分
			boolean penalty = false; //是否因时间顺序不对被扣分的标识
			if(!(result.getState()).equals(ResultState.CONSIDERED)) { //罚分只针对完成或半完成任务
				if(result.getTime().getTime() >= lasttime.getTime()){ //时间在之后，对的，更新time
					lasttime = result.getTime();
				} else { //时间在之前，不对，不更新time，扣分
					result.setScore(result.getScore() - PlanDirector.getInstance().getPenalActionScore());
					penalty = true;
				}
			}
			score += result.getScore();
			fullScore += result.getFullScore();
			
			//2.修正完成状态
			if(penalty == false && (result.getState()).equals(ResultState.COMPLETED)){ //已完成
				state_completed_count++;
			} else if(!(this.mandaPlanSet).contains(AsbruNode.all)
				   && !(this.mandaPlanSet).contains(subPlan.getName())) { //虽然subPlan未完成，但它不是强制的，也算是完成了，但得分是不会加的了
				state_completed_count++; 
			} else if((result.getState()).equals(ResultState.CONSIDERED)){ //未完成
				state_considered_count++;
			}
			
			//3.把Result写入数据库
			//测试用！
			System.out.println(result.toString());
		}
		
		//4.包装： 得分+完成状态，生成当前这个父plan的result
		String state = ResultState.ACTIVATED; //初始化为部分完成
		if(state_completed_count == this.subPlanList.size()) //全部完成
			state = ResultState.COMPLETED;
		else if(state_considered_count == this.subPlanList.size()) //全部未完成
			state = ResultState.CONSIDERED;

		ResultBean resultBean = new ResultBean();
		resultBean.setName(this.name);
		resultBean.setType(this.type);
		resultBean.setScore(score);
		resultBean.setFullScore(fullScore);
		resultBean.setState(state);
		resultBean.setTime(lasttime);
		
		return resultBean; //因为与时间顺序有关，所以时间戳是最后一个有效子plan的完成时间time
	}

	
	//与时间顺序无关的执行操作（父plan仅会修正完成状态）
	ResultBean parExecute(){
		
		int state_completed_count = 0; //该plan下完成的子plan个数
		int state_considered_count = 0; //该plan下一点未完成的子plan个数
		float score = 0; //得分
		float fullScore = 0; //满分
		Timestamp lasttime = new Timestamp(0);
		
		//遍历旗下所有的plan
		for(PlanBase subPlan : this.subPlanList) {
			ResultBean result = subPlan.execute();
			
			//0.记录时间
			if(result.getTime().getTime() >= lasttime.getTime()) //时间在之后才更新time
				lasttime = result.getTime();
			
			//1.累加得分、满分
			score += result.getScore();
			fullScore += result.getFullScore();
			
			//2.修正完成状态
			if((result.getState()).equals(ResultState.COMPLETED)){ //已完成
				state_completed_count++;
			} else if(!(this.mandaPlanSet).contains(AsbruNode.all)
				   && !(this.mandaPlanSet).contains(subPlan.getName())) { //虽然subPlan未完成，但它不是强制的，也算是完成了，但得分是不会加的了
				state_completed_count++; 
			} else if((result.getState()).equals(ResultState.CONSIDERED)){ //未完成
				state_considered_count++;
			}
			
			//3.把Result写入数据库
			//测试用！
			System.out.println(result.toString());
		}
		
		//包装 得分+完成状态，生成当前这个父plan的result
		String state = ResultState.ACTIVATED; //初始化为部分完成
		if(state_completed_count == this.subPlanList.size()) //全部完成
			state = ResultState.COMPLETED;
		else if(state_considered_count == this.subPlanList.size()) //全部未完成
			state = ResultState.CONSIDERED;
		
		ResultBean resultBean = new ResultBean();
		resultBean.setName(this.name);
		resultBean.setType(this.type);
		resultBean.setScore(score);
		resultBean.setFullScore(fullScore);
		resultBean.setState(state);
		resultBean.setTime(lasttime);
		
		return resultBean; //因为与时间顺序无关，所以时间戳是-1
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

	@Override
	public String toString() {
		String res = "——————————————————————————————————\n名称=" + name + ", 类型=" + type + ", 顺序=" + order + "\n子计划:";
		for(PlanBase subPlan : subPlanList)
			res += subPlan.getName() + ", ";
		res += "\n强制计划:";
		for(String mandaPlanName : mandaPlanSet)
			res += mandaPlanName + ",";
		
		return res;
	}
}
