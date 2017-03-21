package plan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import bean.ResultBean;
import constants.Order;
import constants.Type;

//柴皿窃
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
		
		
		return null;
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
		String res = "！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！\n兆各=" + name + ", 窃侏=" + type + ", 乏會=" + order + "\n徨柴皿:";
		for(PlanBase subPlan : subPlanList)
			res += subPlan.getName() + ", ";
		
		return res;
	}
}
