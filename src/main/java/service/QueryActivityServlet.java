package service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jmx.snmp.tasks.TaskServer;

import bean.ResultBean;
import bean.TaskBean;
import dao.ResultDAO;
import dao.TaskDAO;
import plan.PlanDirector;

/**
 * �������activity�������ط����������
 */
public class QueryActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryActivityServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.����beginDate��������
		Timestamp beginOfDayTime = getBeginOfDayTime(request.getParameter("beginDate"));
		
		//2.���ҽ���ƶ������мƻ�
		List<TaskBean> taskBeanList = TaskDAO.read();
		if(taskBeanList == null || taskBeanList.isEmpty())
			response.getWriter().print("<h1>No XML File has been found.</h1>");
		
		//3.����ÿ���ƻ��ļ�+ƥ��
		List<List<ResultBean>> jsonList = new ArrayList<List<ResultBean>>();
		for(TaskBean taskBean : taskBeanList){
			
			Timestamp beginTime = getTime(beginOfDayTime, taskBean.getBeginTime());
			Timestamp endTime   = getTime(beginOfDayTime, taskBean.getEndTime());
			int taskId = taskBean.getId();
			String taskName = taskBean.getName();

			//��̽���ݣ���ͨ����ѯresult�����beginTime��endTime֮���result�Ƿ���֮ǰ�Ѿ���������:
			List<ResultBean> resultBeanList = ResultDAO.read(taskId, beginTime, endTime);
			if(resultBeanList == null || resultBeanList.isEmpty()) {
				PlanDirector.getInstance().start(taskId, taskName, beginTime, endTime);
				resultBeanList = ResultDAO.read(taskId, beginTime, endTime);
			}
			
			//װ��Map
			jsonList.add(resultBeanList);
		}

		
		//4.�Ѳ�ѯ�����װ��JSON
		ObjectMapper jsonMapper = new ObjectMapper();
		String jsonStr = jsonMapper.writeValueAsString(jsonList);

		
		//5.���ز�������ǰ��
		response.getWriter().print(jsonStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	//����beginDateStr�õ���ʼʱ���
	private Timestamp getBeginOfDayTime(String beginDateStr) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		try { beginDate = format.parse(beginDateStr);} 
		catch (ParseException e) { e.printStackTrace();}
		Timestamp beginTime = new Timestamp(beginDate.getTime());
		
		return beginTime;
	}
	
	//��ȡbeginTime�����24.00��ֹʱ���
	private Timestamp getTime(Timestamp beginTime, int hours) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginTime);
		calendar.add(Calendar.HOUR_OF_DAY, hours); 
		Timestamp endTime = new Timestamp(calendar.getTimeInMillis()); //�õ���ֹʱ��
		
		return endTime;
	}	
	
}
