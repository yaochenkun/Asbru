package service;

import bean.ResultBean;
import plan.PlanDirector;

public class MatchingService {

	public static void main(String[] args) {
		
		ResultBean finalResult = PlanDirector.getInstance().start();
		
		//显示！！
		System.out.println("======================================================================================");
		System.out.println("总计划名称=" + finalResult.getName() + ", 最终得分=" + finalResult.getScore() + ", 最终完成情况=" + finalResult.getState() + ", 最后完成时间=" + finalResult.getTime());
	}
}
