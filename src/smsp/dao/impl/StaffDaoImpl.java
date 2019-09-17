package smsp.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import smsp.bean.Skill;
import smsp.bean.Staff;
import smsp.bean.StaffInProject;
import smsp.bean.StaffSkill;
import smsp.dao.StaffDao;
import smsp.util.Pagination;

@Repository("stfDao")
public class StaffDaoImpl extends SqlMapClientDaoSupport implements StaffDao {
    
    @Autowired
    public void createTemplate(SqlMapClient smc) {
	this.setSqlMapClient(smc);
	
    }

    @Override
    public List<Staff> getStaff(int page) {
	int offset = (page - 1) * Pagination.LIMIT;

	
	float pageCount;
	Pagination.setTotalRow((Integer) getSqlMapClientTemplate().queryForObject("countStaff")); 
	pageCount = (float)Pagination.getTotalRow() / (float)Pagination.LIMIT;
	Pagination.setTotalPage((int) Math.ceil(pageCount)); 
	
	HashMap<String, Integer> listData = new HashMap<String, Integer>();
	listData.put("offset", offset);  
	listData.put("limit", Pagination.LIMIT);  
	@SuppressWarnings("unchecked")
	List<Staff> list = getSqlMapClientTemplate().queryForList("getStaff", listData);
	return list;
    }

    @Override
    public Staff getStaffById(int staffId) {
	return (Staff) getSqlMapClientTemplate().queryForObject("getStaffById", staffId);
    }

    @Override
    public List<?> getSearchStaff(Staff staffBean) {

	return getSqlMapClientTemplate().queryForList("getSearchStaff", staffBean);
    }

    @Override
    public List<?> getSkillByStaffId(int stfId) {
	return getSqlMapClientTemplate().queryForList("getSkillByStaffId", stfId);
    }

    @Override
    public List<?> getProjectByStaffId(int stfId) {
	return getSqlMapClientTemplate().queryForList("getProjectByStaffId", stfId);
    }

    @Override
    public List<?> getAllStaff() {
	return getSqlMapClientTemplate().queryForList("getStaffDataAll");
    }
    
    @Override
    public List<?> getStaffReportingTo() {
	return getSqlMapClientTemplate().queryForList("getStaffReportingTo");
    }
    
    @Override
    public void insertStaff(Staff data) {
	// insert into staff
    	getSqlMapClientTemplate().insert("insertStaff", data);
    }

    @Override
    public void updateStaff(Staff data) {
    	getSqlMapClientTemplate().update("updateStaff",data);	

    }

    @Override
    public void deleteStaff(int staffId) {
    	getSqlMapClientTemplate().delete("deleteInStaffSkill", staffId);
    	getSqlMapClientTemplate().delete("deleteInStaff", staffId);
    }

	@Override
	public Staff getStaffByIdStaff(String idStaff) {
		// TODO Auto-generated method stub
		return (Staff) getSqlMapClientTemplate().queryForObject("getStaffByIdStaff", idStaff);
	}

	@Override
	public void insertStaffSkill(int staffId, int skillId) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		data.put("staffId", staffId);
		data.put("skillId", skillId);
		getSqlMapClientTemplate().insert("insertStaffSkill", data);
	}

	@Override
	public List<Skill> getSkillStaff(int staffId, boolean isSkillStaff) {
		List<Skill> output = null;
		if(isSkillStaff == true) {
			output = getSqlMapClientTemplate().queryForList("getSkillStaff", staffId);
		}else if(isSkillStaff == false) {
			output = getSqlMapClientTemplate().queryForList("getNotSkillStaff", staffId);
		}
		return output;
	}

	@Override
	public void deleteSkillStaff(int staffId) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("deleteInStaffSkill", staffId);
	}
    
}
