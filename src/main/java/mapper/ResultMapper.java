package mapper;



import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import bean.ResultBean;

public interface ResultMapper {
	
	@Insert("INSERT INTO result (id, name, type, score, fullScore, state, finishTime, taskId) VALUES (#{id}, #{name}, #{type}, #{score}, #{fullScore}, #{state}, #{finishTime}, #{taskId})")
	@Options(useGeneratedKeys = true)
	void addResultBean(ResultBean resultBean);
	
	@Select("select * from result where taskId = #{param1} and finishTime between #{param2} and #{param3}")
	List<ResultBean> selectResultBeanByPeriod(int taskId, Timestamp beginTime, Timestamp endTime);
}
