package plan;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicTreeUI.TreeTraverseAction;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bean.ResultBean;
import constants.AsbruAttribute;
import constants.AsbruNode;
import constants.Type;

//plan导演类，驱动解析与计分过程的执行
public class PlanDirector {
	
	private String xmlFilePath = "Plan1.xml";
	
	//计分常量
	private int totalScore;
	private int totalActionNum;
	private float gainedActionScore;
	private float penalActionScore;
	private final float penalActionRate;
	
	//计划相关变量
	private Plan rootPlan;
	private Map<String, Timestamp> inputActionMap;
	private Map<String, Plan> expandedPlanMap; //只可能是Plan，而非Action
	
	
	public PlanDirector() {
		
		penalActionRate = 0.2f;
		inputActionMap = new HashMap<String, Timestamp>();
		expandedPlanMap = new HashMap<String, Plan>();
	}
	
	//初始化
	public void start(){
		
		//readInputActions(); //1.读取数据库得到input
		
		buildRootPlan(); //2.解析xml得到rootPlan
		//traverse(rootPlan); //遍历rootPlan,测试用！
		//rootPlan.execute(); //3.匹配开始!
	}
	
	//从数据库读入用户输入的动作
	public void readInputActions() {
		
	}
	
	//读取xml文件路径得到rootPlan
	public void buildRootPlan(){
		
		SAXReader sax = new SAXReader();
		File xmlFile = new File(this.xmlFilePath);
		Document document = null;
		try { 
			document = sax.read(xmlFile);
		} catch (Exception e) { 
			System.out.println("XML文件不存在");
		}
		
		//取plans节点
		Element plansNode = null;
		try	{ 
			Element rootNode = document.getRootElement();
			plansNode = (Element) rootNode.elements("plans").get(0);
		} catch (Exception e) { 
			System.out.println("plans节点不存在，请添加");
		}
		
		//获取根plan的名字，方便之后从map中提取出来
		String rootPlanName = null;
		try	{ 
			Element rootPlanNode = (Element) plansNode.elements(AsbruNode.plan).get(0);
			rootPlanName = rootPlanNode.attribute(AsbruAttribute.name).getValue();
		} catch (Exception e) { 
			System.out.println("第一个plan节点不存在，请添加");
		}
		
		//rootPlan初始化
		rootPlan = new Plan(rootPlanName);
		expandedPlanMap.put(rootPlanName, rootPlan);
		
		//开始解析
	    analyzeNode(plansNode, rootPlan);
	    
	    //测试：遍历 
	    traverse(rootPlan);
	}
	
	//解析node节点，并以parentPlan作为父计划向其中注入解析到的数据
	public void analyzeNode(Element node, Plan parentPlan){
		
		//取节点名字
		String nodeName = node.getName();
		
		//根据节点名字不同进行不同处理
		//未处理的标签，如plan-body、wait-for、wait-for-group、plan-activation是对
		//parentPlan没有影响和改变的，因此不用处理他们，直接跳过这段代码，进入下段，取它的子节点
		if((AsbruNode.plan).equals(nodeName)){
			try{
				String planName = node.attribute(AsbruAttribute.name).getValue();// 父plan.name
				
				//查看当前plan是否已被扩展过
				if(expandedPlanMap.containsKey(planName)){ //已被扩展
					Plan curParentPlan = expandedPlanMap.get(planName);
					parentPlan = curParentPlan;
				} else { //未被扩展，新的
					Plan curParentPlan = new Plan(planName);
					expandedPlanMap.put(planName, curParentPlan);
					parentPlan = curParentPlan;
				}
			} catch(Exception e){
				System.out.println("plan标签缺少name或title属性，请添加");
			}
		} else if((AsbruNode.subplans).equals(nodeName)){
			try{
				String order = node.attribute(AsbruAttribute.type).getValue();// parentPlan.order
				parentPlan.setOrder(order);
			} catch(Exception e){
				System.out.println("subplans标签缺少type属性，请添加");
			}
		} else if((AsbruNode.all).equals(nodeName)) {
			(parentPlan.getMandaPlanSet()).add("All"); //parentPlan.demand（表明旗下全部都是强制的子plan）
			return; //底层标签，没有内容了， 可以返回了
		} else if((AsbruNode.static_plan_pointer).equals(nodeName)) {
			try{
				String subPlanName = node.attribute(AsbruAttribute.plan_name).getValue();// parentPlan.demand
				(parentPlan.getMandaPlanSet()).add(subPlanName);
			} catch(Exception e){
				System.out.println("static-plan-pointer缺少plan-name属性，请添加");
			}
			return; //底层标签,返回
		} else if((AsbruNode.plan_schema).equals(nodeName)) {

			try{
				String subPlanName = node.attribute(AsbruAttribute.name).getValue();// 子plan.name
				Plan subPlan = new Plan(subPlanName); //构造一个新的plan，但他是空的，他的内容由接下来它递归自己时获取，这里的new的作用
				 								   //只是为建立与parentPlan的关系
				expandedPlanMap.put(subPlanName, subPlan);
				(parentPlan.getSubPlanList()).add(subPlan); //加入父plan的列表中	
			} catch(Exception e){
				System.out.println("plan-schema缺少name属性，请添加");
			}
			return; //底层标签,返回
		} else if((AsbruNode.variable_assignment).equals(nodeName)) {
			try{		
				String actionName = node.attribute(AsbruAttribute.variable).getValue();// action.name
				(parentPlan.getMandaPlanSet()).add(actionName); //action是最底层，都是强制
				
				Action action = new Action(actionName); //构造一个新的plan，但他是空的，他的内容由接下来它递归自己时获取，这里的new的作用
				 								   //只是为建立与parentPlan的关系
				(parentPlan.getSubPlanList()).add(action); //加入父plan的列表中	
			} catch(Exception e){
				System.out.println("variable-assignment缺少variable属性，请添加");
			}
			return; //底层标签,返回
		}
	
		//让node旗下的所有一级子节点作为新的父节点，深度遍历
		List<Element> subNodeList = node.elements();
		for (Element subNode : subNodeList)
			analyzeNode(subNode, parentPlan);
	}
	
	//输出内存结果
	void traverse(PlanBase plan) {

		System.out.println(plan.toString());
		if(plan.getType().equals(Type.PLAN)) {
			for (PlanBase subPlan : ((Plan) plan).getSubPlanList())
				traverse(subPlan);
		}
	}
	
	//向数据库写入计分结果（由plan.execute()中调用）
	public static void writeResult(ResultBean resultBean){
		
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getTotalActionNum() {
		return totalActionNum;
	}
	public void setTotalActionNum(int totalActionNum) {
		this.totalActionNum = totalActionNum;
	}
	public float getGainedActionScore() {
		return gainedActionScore;
	}
	public void setGainedActionScore(float gainedActionScore) {
		this.gainedActionScore = gainedActionScore;
	}
	public float getPenalActionScore() {
		return penalActionScore;
	}
	public void setPenalActionScore(float penalActionScore) {
		this.penalActionScore = penalActionScore;
	}
}
