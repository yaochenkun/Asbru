package mapper;



import java.util.List;
import org.apache.ibatis.annotations.Select;
import bean.TaskBean;

public interface TaskMapper {
	
	@Select("select * from task")
	List<TaskBean> selectTaskBean();
}
