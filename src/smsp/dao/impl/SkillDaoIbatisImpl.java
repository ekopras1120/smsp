package smsp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import smsp.bean.Skill;
import smsp.dao.SkillDao;
import smsp.util.QueryResultHelper;
import smsp.util.SmspConstants;

@Repository("skillDao")
public class SkillDaoIbatisImpl extends SqlMapClientDaoSupport implements SkillDao {
	
	private static Log log = LogFactory.getLog(SkillDaoIbatisImpl.class);
	
	@Autowired
	public void createTemplate(SqlMapClient sqlMapClient) {
		this.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public List<Skill> getSkill() {
	    return getSqlMapClientTemplate().queryForList("getSkillAll");
	}

	@Override
	public Skill getSkillByName(String name) {
		return (Skill) getSqlMapClientTemplate().queryForObject("getSkillByName",name);
	}

	@Override
	public QueryResultHelper getSkillList(int pageNo, String searchSkill) {
		if (pageNo<=0) pageNo = 1;
		
		Object obj = getSqlMapClientTemplate().queryForObject("countSkill",searchSkill);
		log.debug("Total skill " + obj);
		
		int totalRecord = (Integer) obj;
		int startRow = SmspConstants.LINE_PER_PAGE * (pageNo-1);
		
		float totalPage = (float)totalRecord/(float)SmspConstants.LINE_PER_PAGE;
		int iTotalPage = (int)Math.ceil(totalPage);
		
		
		Map map = new HashMap();
		map.put("start",startRow);
		map.put("length",SmspConstants.LINE_PER_PAGE);
		map.put("name", searchSkill);
		
		List<Skill>list = getSqlMapClientTemplate().queryForList("getSkillByName",map);
		
		return new QueryResultHelper(pageNo,iTotalPage,list);	
	}

	@Override
	public Skill getSkillById(int id) {
		return (Skill) getSqlMapClientTemplate().queryForObject("getSkillById", id);
	}

	@Override
	public void add(String sklName) {
		getSqlMapClientTemplate().insert("addSkill", sklName);
	}

	@Override
	public void update(int sklId, String sklName) {
		HashMap data = new HashMap();
		data.put("sklId", Integer.toString(sklId));
		data.put("sklName", sklName);
		getSqlMapClientTemplate().update("updateSkill", data);		
	}

	@Override
	public void delete(int sklId) {
		getSqlMapClientTemplate().delete("deleteSkill", sklId);
	}	
}
