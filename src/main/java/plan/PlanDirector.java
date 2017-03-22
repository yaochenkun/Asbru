package plan;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bean.ActionBean;
import bean.ResultBean;
import constants.AsbruAttribute;
import constants.AsbruNode;
import constants.Type;

//plan导演类，驱动解析与计分过程的执行
public class PlanDirector {
	
	//单例
	private static PlanDirector instance;
	
	private final String xmlFilePath = "morning.xml";
	
	//计分常量
	private final float TOTAL_SCORE = 100.0f; //总分数
	private final float PENAL_ACTION_RATE = 0.2f; //惩罚率
	
	private int totalActionNum = 0; //总action数
	private float gainedActionScore = 0.0f; //每个action分值
	private float penalActionScore = 0.0f;  //每个action在时间顺序不对时的惩罚分
	
	//计划相关变量
	private Plan rootPlan;
	public static Map<String, ActionBean> inputActionMap; //被action访问
	private Map<String, Plan> expandedPlanMap; //只可能是Plan，而非Action
	
	
	public PlanDirector() {
		
		inputActionMap = new HashMap<String, ActionBean>();
		this.expandedPlanMap = new HashMap<String, Plan>();
	}
	
	//单例获取
	public static PlanDirector getInstance() {
		if(instance == null) instance = new PlanDirector();
		return instance;
	}
	
	//初始化
	public ResultBean start(){
		
		//1.读取数据库得到input
		readInputActions();
		
		//2.解析xml得到rootPlan
		buildRootPlan();
		
		//3.计算分数
		initScore();
		
		//4.匹配开始!
		return rootPlan.execute();		
	}
	
	//从数据库读入用户输入的动作
	private void readInputActions() {
		
		List<ActionBean> actionBeanList = new ArrayList<ActionBean>();
		
		//getting up-seq
			actionBeanList.add(new ActionBean("enter bathroom", new Timestamp(1)));
			actionBeanList.add(new ActionBean("wash hands", new Timestamp(2)));
			actionBeanList.add(new ActionBean("brush teeth", new Timestamp(3)));
			actionBeanList.add(new ActionBean("wash face", new Timestamp(4)));
			actionBeanList.add(new ActionBean("leave bathroom", new Timestamp(5)));
		
		//breakfast-seq
			actionBeanList.add(new ActionBean("enter kitchen", new Timestamp(6)));
		
			//prepare breakfast-seq
				actionBeanList.add(new ActionBean("open fridge", new Timestamp(7)));
				actionBeanList.add(new ActionBean("find food", new Timestamp(8)));
				actionBeanList.add(new ActionBean("close fridge", new Timestamp(9)));
				actionBeanList.add(new ActionBean("cook food", new Timestamp(10)));
		
			actionBeanList.add(new ActionBean("eat", new Timestamp(11)));
			actionBeanList.add(new ActionBean("leave kitchen", new Timestamp(12)));
		
		//run
			actionBeanList.add(new ActionBean("run", new Timestamp(13)));
		
		//entertainment-par
			actionBeanList.add(new ActionBean("sing", new Timestamp(14)));
			actionBeanList.add(new ActionBean("play game", new Timestamp(15)));
			actionBeanList.add(new ActionBean("watch TV", new Timestamp(16)));
		
		actionBeanList.add(new ActionBean("read book", new Timestamp(17)));
		
		//cleaning-seq
			actionBeanList.add(new ActionBean("open window", new Timestamp(18)));
		
			//cleaning floor-seq
				actionBeanList.add(new ActionBean("sweep floor", new Timestamp(19)));
				actionBeanList.add(new ActionBean("mod floor", new Timestamp(20)));
			
			//wipe furniture
			actionBeanList.add(new ActionBean("wipe furniture", new Timestamp(21)));
			
			//empty trash
			actionBeanList.add(new ActionBean("empty trash", new Timestamp(22)));
		
		//washing clothes-seq
			actionBeanList.add(new ActionBean("enter washroom", new Timestamp(23)));
			actionBeanList.add(new ActionBean("take dirty clothes", new Timestamp(24)));
			actionBeanList.add(new ActionBean("wash clothes", new Timestamp(25)));
			actionBeanList.add(new ActionBean("wring clothes", new Timestamp(26)));
			actionBeanList.add(new ActionBean("leave washroom", new Timestamp(27)));
			//actionBeanList.add(new ActionBean("sun clothes", new Timestamp(28)));	
		
		
		//存入inputActionMap中
		for(ActionBean actionBean : actionBeanList)
			inputActionMap.put(actionBean.getName(), actionBean);
	}
	
	//读取xml文件路径得到rootPlan
	private void buildRootPlan(){
		
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
	    //traverse(rootPlan);
	}
	
	//解析node节点，并以parentPlan作为父计划向其中注入解析到的数据
	private void analyzeNode(Element node, Plan parentPlan){
		
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
			(parentPlan.getMandaPlanSet()).add(AsbruNode.all); //parentPlan.demand（表明旗下全部都是强制的子plan）
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
				
				Action action = new Action(actionName); //构造一个新的plan，但他是空的，他的内容由接下来它递归自己时获取，这里的new的作用只是为建立与parentPlan的关系
				(parentPlan.getSubPlanList()).add(action); //加入父plan的列表中	
				
				totalActionNum++; //action数增1
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
	
	//计算每个action匹配后的得分与惩罚分
	private void initScore(){
		gainedActionScore = TOTAL_SCORE / totalActionNum;
		penalActionScore  = gainedActionScore * PENAL_ACTION_RATE;
	}
	
	//输出内存结果
	private void traverse(PlanBase plan) {

		System.out.println(plan.toString());
		if(plan.getType().equals(Type.PLAN)) {
			for (PlanBase subPlan : ((Plan) plan).getSubPlanList())
				traverse(subPlan);
		}
	}
	
	//向数据库写入计分结果（由plan.execute()中调用）
	public static void writeResult(ResultBean resultBean){
		
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
