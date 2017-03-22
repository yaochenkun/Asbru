package plan;

import java.sql.Timestamp;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import bean.ResultBean;
import constants.ResultState;
import constants.Type;

//动作类
public class Action extends PlanBase{

	Action(String name) {
		super(name);
		this.type = Type.ACTION;
	}

	@Override
	ResultBean execute() {
		
		String state; //完成状态
		float score = 0; //得分
		Timestamp time = new Timestamp(0);
		
		
		//查看输入动作input中是否有该action， 若有则匹配
		if((PlanDirector.inputActionMap).containsKey(this.name)){
			score = PlanDirector.getInstance().getGainedActionScore();
			state = ResultState.COMPLETED;
			time = (PlanDirector.inputActionMap).get(this.name).getTime(); //获取时间
			(PlanDirector.inputActionMap).remove(this.name); //删除该输入动作，减轻后面查询负担
		} else {
			score = 0;
			state = ResultState.CONSIDERED;
		}

		return new ResultBean(this.name, this.type, score, state, time);
	}

}
