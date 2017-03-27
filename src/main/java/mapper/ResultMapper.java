package mapper;



import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import bean.ResultBean;

public interface ResultMapper {
	
	@Insert("insert into result(id, name, type, score, fullScore, state, finishTime) values(#{id}, #{name}, #{type}, #{score}, #{fullScore}, #{state}, #{finishTime})")
	@Options(useGeneratedKeys = true)
	void addResultBean(ResultBean resultBean);
	
	@Select("select * from result where finishTime between #{param1} and #{param2}")
	List<ResultBean> selectResultBeanByPeriod(Timestamp beginTime, Timestamp endTime);
}
