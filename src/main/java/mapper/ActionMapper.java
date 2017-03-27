package mapper;


import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.annotations.Select;

import bean.ActionBean;

public interface ActionMapper {
	
	@Select("select * from action where time between #{param1} and #{param2}")
	List<ActionBean> selectActionsByPeriod(Timestamp beginTime, Timestamp endTime);
}
