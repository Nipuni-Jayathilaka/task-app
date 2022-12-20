package lk.ijse.dep9.app.dao.custom;

import lk.ijse.dep9.app.dao.CurdDAO;
import lk.ijse.dep9.app.entity.Project;

import java.util.List;

public interface ProjectDAO extends CurdDAO<Project,Integer> {
    List<Project> findAllProjectByUsername(String username);
 }
