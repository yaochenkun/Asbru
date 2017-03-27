package service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import bean.ResultBean;
import mapper.ResultMapper;
import mapper.SqlSessionHelper;
import plan.PlanDirector;

/**
 * 请求分析activity，并返回分析结果数据
 */
public class QueryActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryActivityServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.先通过查询result表，检查beginTime和endTime之间的result是否在之前已经分析过了:
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 0); //获取起始时间 
		calendar.set(Calendar.MINUTE, 0);  
		calendar.set(Calendar.SECOND, 0);  
		calendar.set(Calendar.MILLISECOND, 0);  
		calendar.add(Calendar.DATE, -1); //人为减去从27号回到26号

		Timestamp beginTime = new Timestamp(calendar.getTimeInMillis()); //得到起始时间
		calendar.add(Calendar.DATE, 1); 
		Timestamp endTime = new Timestamp(calendar.getTimeInMillis()); //得到终止时间
		
		List<ResultBean> resultBeanList = readResults(beginTime, endTime);
		if(resultBeanList == null || resultBeanList.isEmpty()) {
			PlanDirector.getInstance().start(beginTime, endTime);
			resultBeanList = readResults(beginTime, endTime);
		}
		
		//2.把查询结果包装成JSON返回前端

		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	//从数据库读取beginTime-endTime之间的result列表
	private List<ResultBean> readResults(Timestamp beginTime, Timestamp endTime){
		
		SqlSession session = null;
		List<ResultBean> resultBeanList = null;
		try {
			session = SqlSessionHelper.getSessionFactory().openSession();
			ResultMapper mapper = session.getMapper(ResultMapper.class);
			resultBeanList = mapper.selectResultBeanByPeriod(beginTime, endTime);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return resultBeanList;
	}
}
