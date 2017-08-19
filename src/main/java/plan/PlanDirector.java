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
import dao.ActionDAO;
import dao.ResultDAO;

//plan�����࣬����������Ʒֹ��̵�ִ��
public class PlanDirector {
	
	//����
	private static PlanDirector instance;
	
	//�Ʒֳ���
	private final float FULL_SCORE = 100.0f; //�ܷ���
	private final float PENAL_RATE = 0.5f; //�ͷ���
	
	private int totalActionNum = 0; //��action��
	private float gainedActionScore = 0.0f; //ÿ��action��ֵ
	private float penalScore = 0.0f;  //ÿ��plan��ʱ��˳�򲻶�ʱ�ĳͷ���
	
	//�ƻ���ر���
	private Plan rootPlan;
	private Map<String, List<ActionBean>> inputActionMap; //��action����
	private Map<String, Plan> expandedPlanMap; //ֻ������Plan������Action
	
	
	public PlanDirector() {
		
		this.inputActionMap = new HashMap<String, List<ActionBean>>();
		this.expandedPlanMap = new HashMap<String, Plan>();
	}
	
	//������ȡ
	public static PlanDirector getInstance() {
		if(instance == null) 
			instance = new PlanDirector();
		return instance;
	}
	
	//��ʼ��
	public void start(int taskId, String taskName, Timestamp beginTime, Timestamp endTime){
		
		//0.���һ������
		clearData();
		
		//1.��ȡ���ݿ�õ�input
		readInputActionsToMap(beginTime, endTime);
		
		//2.����xml�õ�rootPlan
		buildRootPlan(taskName + ".xml");
		
		//3.�������
		initScore();
		
		//4.ƥ��! 
		rootPlan.execute(taskId, beginTime);
	}
	
	private void clearData(){
		
		this.totalActionNum = 0;
		this.gainedActionScore = 0.0f;
		this.penalScore = 0.0f;
		this.rootPlan = null;
		this.inputActionMap.clear();
		this.expandedPlanMap.clear();
	}
	
	//�����ݿ�����û�����Ķ���
	private void readInputActionsToMap(Timestamp beginTime, Timestamp endTime) {
			
		List<ActionBean> actionBeanList = ActionDAO.read(beginTime, endTime);
		
		//����inputActionMap��
		if(actionBeanList != null) {
			for(ActionBean actionBean : actionBeanList) {
				String actionName = actionBean.getName();
				if(!inputActionMap.containsKey(actionName)) //��һ��
					inputActionMap.put(actionName, new ArrayList<ActionBean>());
	
				inputActionMap.get(actionName).add(actionBean);//׷��
			}
		}
	}
	
	//��ȡxml�ļ�·���õ�rootPlan
	private void buildRootPlan(String xmlFilePath){
		
		SAXReader sax = new SAXReader();
		
		xmlFilePath = this.getClass().getClassLoader().getResource(xmlFilePath).getPath();
		File xmlFile = new File(xmlFilePath);
		Document document = null;
		try { 
			document = sax.read(xmlFile);
		} catch (Exception e) { 
			System.out.println("XML�ļ�������");
		}
		
		//ȡplans�ڵ�
		Element plansNode = null;
		try	{ 
			Element rootNode = document.getRootElement();
			plansNode = (Element) rootNode.elements("plans").get(0);
		} catch (Exception e) { 
			System.out.println("plans�ڵ㲻���ڣ������");
		}
		
		//��ȡ��plan�����֣�����֮���map����ȡ����
		String rootPlanName = null;
		try	{ 
			Element rootPlanNode = (Element) plansNode.elements(AsbruNode.plan).get(0);
			rootPlanName = rootPlanNode.attribute(AsbruAttribute.name).getValue();
		} catch (Exception e) { 
			System.out.println("��һ��plan�ڵ㲻���ڣ������");
		}
		
		//rootPlan��ʼ��
		rootPlan = new Plan(rootPlanName);
		expandedPlanMap.put(rootPlanName, rootPlan);
		
		//��ʼ����
	    analyzeNode(plansNode, rootPlan);
	}
	
	//����node�ڵ㣬����parentPlan��Ϊ���ƻ�������ע�������������
	@SuppressWarnings("unchecked")
	private void analyzeNode(Element node, Plan parentPlan){
		
		//ȡ�ڵ�����
		String nodeName = node.getName();
		
		//���ݽڵ����ֲ�ͬ���в�ͬ����
		//δ����ı�ǩ����plan-body��wait-for��wait-for-group��plan-activation�Ƕ�
		//parentPlanû��Ӱ��͸ı�ģ���˲��ô������ǣ�ֱ��������δ��룬�����¶Σ�ȡ�����ӽڵ�
		if((AsbruNode.plan).equals(nodeName)){
			try{
				String planName = node.attribute(AsbruAttribute.name).getValue();// ��plan.name
				
				//�鿴��ǰplan�Ƿ��ѱ���չ��
				if(expandedPlanMap.containsKey(planName)){ //�ѱ���չ
					Plan curParentPlan = expandedPlanMap.get(planName);
					parentPlan = curParentPlan;
				} else { //δ����չ���µ�
					Plan curParentPlan = new Plan(planName);
					expandedPlanMap.put(planName, curParentPlan);
					parentPlan = curParentPlan;
				}
			} catch(Exception e){
				System.out.println("plan��ǩȱ��name��title���ԣ������");
			}
		} else if((AsbruNode.subplans).equals(nodeName)){
			try{
				String order = node.attribute(AsbruAttribute.type).getValue();// parentPlan.order
				parentPlan.setOrder(order);
			} catch(Exception e){
				System.out.println("subplans��ǩȱ��type���ԣ������");
			}
		} else if((AsbruNode.all).equals(nodeName)) {
			(parentPlan.getMandaPlanSet()).add(AsbruNode.all); //parentPlan.demand����������ȫ������ǿ�Ƶ���plan��
			return; //�ײ��ǩ��û�������ˣ� ���Է�����
		} else if((AsbruNode.static_plan_pointer).equals(nodeName)) {
			try{
				String subPlanName = node.attribute(AsbruAttribute.plan_name).getValue();// parentPlan.demand
				(parentPlan.getMandaPlanSet()).add(subPlanName);
			} catch(Exception e){
				System.out.println("static-plan-pointerȱ��plan-name���ԣ������");
			}
			return; //�ײ��ǩ,����
		} else if((AsbruNode.plan_schema).equals(nodeName)) {

			try{
				String subPlanName = node.attribute(AsbruAttribute.name).getValue();// ��plan.name
				Plan subPlan = new Plan(subPlanName); //����һ���µ�plan�������ǿյģ����������ɽ��������ݹ��Լ�ʱ��ȡ�������new������
				 								   //ֻ��Ϊ������parentPlan�Ĺ�ϵ
				expandedPlanMap.put(subPlanName, subPlan);
				(parentPlan.getSubPlanList()).add(subPlan); //���븸plan���б���	
			} catch(Exception e){
				System.out.println("plan-schemaȱ��name���ԣ������");
			}
			return; //�ײ��ǩ,����
		} else if((AsbruNode.variable_assignment).equals(nodeName)) {
			try{		
				String actionName = node.attribute(AsbruAttribute.variable).getValue();// action.name
				(parentPlan.getMandaPlanSet()).add(actionName); //action����ײ㣬����ǿ��
				
				Action action = new Action(actionName); //����һ���µ�plan�������ǿյģ����������ɽ��������ݹ��Լ�ʱ��ȡ�������new������ֻ��Ϊ������parentPlan�Ĺ�ϵ
				(parentPlan.getSubPlanList()).add(action); //���븸plan���б���	
				
				totalActionNum++; //action����1
			} catch(Exception e){
				System.out.println("variable-assignmentȱ��variable���ԣ������");
			}
			return; //�ײ��ǩ,����
		}
	
		//��node���µ�����һ���ӽڵ���Ϊ�µĸ��ڵ㣬��ȱ���
		List<Element> subNodeList = node.elements();
		for (Element subNode : subNodeList)
			analyzeNode(subNode, parentPlan);
	}
	
	//����ÿ��actionƥ���ĵ÷���ͷ���
	private void initScore(){
		gainedActionScore = FULL_SCORE / totalActionNum;
		penalScore  = gainedActionScore * PENAL_RATE;
	}
	
	//����ڴ���(�����ã�)
//	@SuppressWarnings("unused")
//	private void traverse(PlanBase plan) {
//
//		System.out.println(plan.toString());
//		if(plan.getType().equals(Type.PLAN)) {
//			for (PlanBase subPlan : ((Plan) plan).getSubPlanList())
//				traverse(subPlan);
//		}
//	}

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

	public float getPenalScore() {
		return penalScore;
	}

	public void setPenalActionScore(float penalScore) {
		this.penalScore = penalScore;
	}

	public Map<String, List<ActionBean>> getInputActionMap() {
		return inputActionMap;
	}

	public void setInputActionMap(Map<String, List<ActionBean>> inputActionMap) {
		this.inputActionMap = inputActionMap;
	}

	public float getPENAL_RATE() {
		return PENAL_RATE;
	}
	
}
