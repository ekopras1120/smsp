package smsp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import smsp.bean.Project;
import smsp.dao.ProjectDao;
import smsp.util.QueryResultHelper;
import smsp.util.SmspConstants;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("projectDao")
public class ProjectDaoIbatisImpl extends SqlMapClientDaoSupport implements
	ProjectDao {

    private static Log log = LogFactory.getLog(ProjectDaoIbatisImpl.class);

    @Autowired
    public void createTemplate(SqlMapClient smc) {
	this.setSqlMapClient(smc);
    }

    public List<Project> getProject() {
	return getSqlMapClientTemplate().queryForList("getProjectAll");
    }

    @Override
    public Project getProjectByName(Project name) {
	return (Project) getSqlMapClientTemplate().queryForObject(
		"getProjectByName", name);
    }

    @Override
    public QueryResultHelper getProjectList(int pageNo, String search) {
	if (pageNo <= 1)
	    pageNo = 1;

	Object obj = getSqlMapClientTemplate().queryForObject("countProject",
		search);
	log.debug("Banyak project : " + obj);

	int totalRecord = (Integer) obj;
	int startRow = SmspConstants.LINE_PER_PAGE * (pageNo - 1);

	float totalPage = (float) totalRecord
		/ (float) SmspConstants.LINE_PER_PAGE;
	int iTotalPage = (int) Math.ceil(totalPage);

	Map map = new HashMap();
	map.put("start", startRow);
	map.put("length", SmspConstants.LINE_PER_PAGE);
	map.put("name", search);

	List<Project> list = getSqlMapClientTemplate().queryForList(
		"getProjectByName", map);

	return new QueryResultHelper(pageNo, iTotalPage, list);
    }

    @Override
    public Project getProjectById(int id) {
	return (Project) getSqlMapClientTemplate().queryForObject(
		"getProjectById", id);
    }

    @Override
    public void addProject(Project projectBean) {
	getSqlMapClientTemplate().insert("addProject", projectBean);
    
    }

  

    @Override
    public void deleteProject(int id) {
	getSqlMapClientTemplate().delete("deleteProject", id);
    }

	@Override
	public int getLocationId(String location) {
		// TODO Auto-generated method stub
		Object obj = getSqlMapClientTemplate().queryForObject("getLocationId", location);
		int locationId = (Integer) obj;
		return locationId;
	}

	@Override
	public void updateProject(Project project) {
		getSqlMapClientTemplate().update("update", project);
		
	}
}
