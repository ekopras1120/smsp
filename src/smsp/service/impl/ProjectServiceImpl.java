package smsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smsp.bean.Project;
import smsp.dao.ProjectDao;
import smsp.service.ProjectService;
import smsp.util.QueryResultHelper;

@Service("projectManager")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
	this.projectDao = projectDao;
    }
	
    public List<Project> getProject() {
	return projectDao.getProject();
    }

    @Override
    public QueryResultHelper getProjectList(int pageNo, String search) {
	return projectDao.getProjectList(pageNo, search);
    }

    @Override
    public Project getProjectByName(Project name) {
	return projectDao.getProjectByName(name);
    }

    @Override
    public Project getProjectById(int id) {
	return projectDao.getProjectById(id);
    }

    @Override
    public void addProject(Project projectBean) {
	projectDao.addProject(projectBean);
    }

   

    @Override
    public void deleteProject(int id) {
	projectDao.deleteProject(id);
    }

	@Override
	public int getLocationId(String location) {
		// TODO Auto-generated method stub
		return projectDao.getLocationId(location);
	}

	@Override
	public void updateProject(Project project) {
		// TODO Auto-generated method stub
		projectDao.updateProject(project);
	}
}
