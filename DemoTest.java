package adl;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.amazon.speech.speechlet.State;
import com.amazonaws.services.s3.internal.RestUtils;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.jmx.remote.util.OrderClassLoaders;
import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets;

public class DomTest {

	public static void main(String[] args) throws DocumentException {
		new Solution().start();
	}

}

class Solution {
	void start() throws DocumentException {
		SAXReader sax = new SAXReader();// 创建一个SAXReader对象
		File xmlFile = new File("Plan1.xml");// 根据指定的路径创建file对象
		Document document = sax.read(xmlFile);// 获取document对象,如果文档无节点，则会抛出Exception提前结束
		Element root = document.getRootElement();// 获取根节点

		getNodes(root);// 从根节点开始遍历所有节点
	}

	public void getNodes(Element node) {
		System.out.println("--------------------");

		// 当前节点的名称、文本内容和属性
		System.out.println("当前节点名称：" + node.getName());// 当前节点名称
		System.out.println("当前节点的内容：" + node.getTextTrim());// 当前节点名称
		List<Attribute> listAttr = node.attributes();// 当前节点的所有属性的list
		for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
			String name = attr.getName();// 属性名称
			String value = attr.getValue();// 属性的值
			System.out.println("属性名称：" + name + "属性值：" + value);
		}

		// 递归遍历当前节点所有的子节点
		List<Element> listElement = node.elements("plans");// 所有一级子节点的list
		for (Element e : listElement) {// 遍历所有一级子节点
			List<Element> plans = e.elements("plan");// 所有一级子节点的list
			for (Element plan : plans) {// 遍历所有一级子节点
				this.getNodes(plan);// 递归
			}
		}
	}
	
	
	//解析当node节点
	//node和parentPlan已经在上一层构建好了传进来
	//初始调用的时候这样写：
	//Plan rootPlan = new Plan("根plan名",type);
	//expandedPlansMap.put("根plan名", rootPlan);
	//buildPlan(<plans>节点， rootPlan)
	public void buildPlan(Element node, Plan parentPlan){
		
		//取节点名字
		String nodeName = node.getName();
		
		//根据节点名字不同进行不同处理
		//未处理的标签，如plan-body、wait-for、wait-for-group、plan-activation是对
		//parentPlan没有影响和改变的，因此不用处理他们，直接跳过这段代码，进入下段，取它的子节点
		if("plan".equals(nodeName)){
			try{
				String planName = node.attribute("name").getValue();// 父plan.name
				String planTitle = node.attribute("title").getValue();// 父plan.title
				
				//查看当前plan是否已被扩展过
				if(expandedPlansMap.containsKey(planName)){ //已被扩展
					Plan curParentPlan = expandedPlansMap.get(planName);
					parentPlan = curParentPlan;
				} else { //未被扩展，新的
					Plan curParentPlan = new Plan(planName, planTitle, type, ...);
					expandedPlansMap.put(planName, curParentPlan);
					parentPlan = curParentPlan;
				}
			} catch(Exception e){
				System.out.println("plan标签缺少name或title属性，请添加");
			}
		} else if("subplans".equals(nodeName)){
			try{
				String order = node.attribute("type").getValue();// parentPlan.order
				parentPlan.setOrder(order);
			} catch(Exception e){
				System.out.println("subplans标签缺少type属性，请添加");
			}
		} else if("all".equals(nodeName)) {
			(parentPlan.mandaPlansSet).add("All"); //parentPlan.demand（表明旗下全部都是强制的子plan）
			return; //底层标签，没有内容了， 可以返回了
		} else if("static-plan-pointer".equals(nodeName)) {
			try{
				String subPlanName = node.attribute("plan-name").getValue();// parentPlan.demand
				(parentPlan.mandaPlansSet).add(subPlanName);
			} catch(Exception e){
				System.out.println("static-plan-pointer缺少plan-name属性，请添加");
			}
			return; //底层标签,返回
		} else if("plan-schema".equals(nodeName)) {
			try{
				String subPlanName = node.attribute("plan-name").getValue();// 子plan.name
				Plan subPlan = new Plan(subPlanName, type); //构造一个新的plan，但他是空的，他的内容由接下来它递归自己时获取，这里的new的作用
				 								   //只是为建立与parentPlan的关系
				expandedPlansMap.put(subPlanName, subPlan);
				parentPlan.add(subPlan); //加入父plan的列表中	
			} catch(Exception e){
				System.out.println("plan-schema缺少name属性，请添加");
			}
			return; //底层标签,返回
		} else if("variable-assignment".equals(nodeName)) {
			try{		
				String actionName = node.attribute("variable").getValue();// action.name
				(parentPlan.mandaPlansSet).add(actionName); //action是最底层，都是强制
				
				Action action = new Action(actionName, type); //构造一个新的plan，但他是空的，他的内容由接下来它递归自己时获取，这里的new的作用
				 								   //只是为建立与parentPlan的关系
				parentPlan.add(action); //加入父plan的列表中	
			} catch(Exception e){
				System.out.println("variable-assignment缺少variable属性，请添加");
			}
			return; //底层标签,返回
		}
	
		//让node旗下的所有一级子节点作为新的父节点，深度遍历
		List<Element> subNodeList = node.elements();
		for (Element subNode : subNodeList)
			buildPlan(subNode, parentPlan);
	}
	
	
	//Plan.execute()
	Result execute() {
		
		Result result;
		if("sequential".equals(this.order))
			result = seqExecute();
		else
			result = parExecute();
		
		return result;
	}

//与时间顺序有关的执行操作（父plan会根据时间顺序对不对修正得分 + 另外修正完成状态）
Result seqExecute(){
	
	int state_completed_count = 0; //该plan下完成的子plan个数
	int state_considered_count = 0; //该plan下一点未完成的子plan个数
	int score = 0; //得分
	Timestamp time = -1; //父plan的待返回的时间戳
	
	//遍历旗下所有的plan
	for(PlanBase subpPlan : subPlans) {
		Result result = subPlan.execute();
		
		//1.修正得分并累加得分
		boolean penalty = false; //是否因时间顺序不对被扣分的标识 
		if(result.time >= time){ //时间在之后，对的，更新time
			time = result.time;
		} else { //时间在之前，不对，不更新time，扣分
			result.score -= PlanManager.ACTION_SCORE_PENALTY;
			penalty = true
		}
		score += result.score;
		
		//2.修正完成状态
		if(penalty == false && result.state == State.COMPLETED){ //已完成
			state_completed_count++;
		} else if(!(this.mandaPlansSet).contains("All")
			   && !(this.mandaPlansSet).contains(subPlan.name)) { //虽然subPlan未完成，但它不是强制的，也算是完成了，但得分是不会加的了
			state_completed_count++; 
		} else if(result.state == State.CONSIDERED){ //未完成
			state_considered_count++;
		}
		
		//3.把Result写入数据库
		//%%%%%%%%%%%%%%%%%%%
		
	}
	
	//4.包装： 得分+完成状态，生成当前这个父plan的result
	int state = State.ACTIVITED; //初始化为部分完成
	if(state_completed_count == subPlans.size()) //全部完成
		state = State.COMPLETED;
	else if(state_considered_count == subPlans.size()) //全部未完成
		state = State.CONSIDERED;
	
	return new Result(score, state, time); //因为与时间顺序有关，所以时间戳是最后一个有效子plan的完成时间time
}
	
	//与时间顺序无关的执行操作（父plan仅会修正完成状态）
	Result parExecute(){
		
		int state_completed_count = 0; //该plan下完成的子plan个数
		int state_considered_count = 0; //该plan下一点未完成的子plan个数
		int score = 0; //得分
		
		//遍历旗下所有的plan
		for(PlanBase subpPlan : subPlans) {
			Result result = subPlan.execute();
			
			//1.累加得分
			score += result.score;
			
			//2.修正完成状态
			if(result.state == State.COMPLETED){ //已完成
				state_completed_count++;
			} else if(!(this.mandaPlansSet).contains("All")
				   && !(this.mandaPlansSet).contains(subPlan.name)) { //虽然subPlan未完成，但它不是强制的，也算是完成了，但得分是不会加的了
				state_completed_count++; 
			} else if(result.state == State.CONSIDERED){ //未完成
				state_considered_count++;
			}
			
			//3.把Result写入数据库
			//%%%%%%%%%%%%%%%%%%%
		}
		
		//包装 得分+完成状态，生成当前这个父plan的result
		int state = State.ACTIVITED; //初始化为部分完成
		if(state_completed_count == subPlans.size()) //全部完成
			state = State.COMPLETED;
		else if(state_considered_count == subPlans.size()) //全部未完成
			state = State.CONSIDERED;
		
		return new Result(score, state, -1); //因为与时间顺序无关，所以时间戳是-1
	}
	
	
	
	
	
	
	
	
	//Action.execute()
	Result execute(){
		
		int score; //得分
		State state; //完成状态
		Timestamp time = -1; //时间
		
		//查看输入动作input中是否有该action， 若有则匹配
		if(inputActionsMap.containsKey(this.name)){
			score = PlanManager.ACTION_SCORE;
			state = State.COMPLETED;
			time = inputActionsMap.get(this.name); //获取时间
		} else {
			score = 0;
			state = State.CONSIDERED;
		}
	
		return new Result(score, state, time);
	}
	
	
}

