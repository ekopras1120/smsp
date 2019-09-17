package smsp.dao;

import java.util.List;

import smsp.bean.Project;
import smsp.util.QueryResultHelper;

public interface ProjectDao {

	public QueryResultHelper getProjectList (int pageNo, String search);
	
	public List<Project> getProject();
	
	public Project getProjectByName(Project name);
	
	public Project getProjectById(int id);
	
	public void addProject(Project projectBean);
	
	public void updateProject(Project project);
	
	public void deleteProject(int id);
	
	public int getLocationId(String location);
	
}
