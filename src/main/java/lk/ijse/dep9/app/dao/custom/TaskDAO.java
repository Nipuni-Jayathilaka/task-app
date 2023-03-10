package lk.ijse.dep9.app.dao.custom;


import lk.ijse.dep9.app.dao.CurdDAO;
import lk.ijse.dep9.app.entity.Task;

import java.util.List;

public interface TaskDAO extends CurdDAO<Task, Integer> {

    List<Task> findAllTaskByProjectId(Integer projectId);
}
