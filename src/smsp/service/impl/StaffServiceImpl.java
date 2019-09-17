package smsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smsp.bean.Staff;
import smsp.dao.StaffDao;
import smsp.bean.Skill;
import smsp.service.StaffService;

@Service("stfManager")
public class StaffServiceImpl implements StaffService {
    
    @Autowired
    private StaffDao stfDao;

    public void setStfDao(StaffDao stfDao) {
        this.stfDao = stfDao;
    }

    @Override
    public List<Staff> getStaff(int page) {
	return stfDao.getStaff(page);
    }

    @Override
    public Staff getStaffById(int staffId) {
	return stfDao.getStaffById(staffId);
    }

    @Override
    public List<?> getSearchStaff(Staff staffBean) {
	return stfDao.getSearchStaff(staffBean);
    }

    @Override
    public List<?> getSkillByStaffId(int stfId) {
	return stfDao.getSkillByStaffId(stfId);
    }

    @Override
    public List<?> getProjectByStaffId(int stfId) {
	return stfDao.getProjectByStaffId(stfId);
    }

    @Override
    public List<?> getAllStaff() {
	return stfDao.getAllStaff();
    }
    
    @Override
    public List<?> getStaffReportingTo() {
	return stfDao.getStaffReportingTo();
    }
    
    @Override
    public void insertStaff(Staff data) {
	stfDao.insertStaff(data);
    }

    @Override
    public void updateStaff(Staff data) {
	stfDao.updateStaff(data);
    }

    @Override
    public void deleteStaff(int staffId) {
	stfDao.deleteStaff(staffId);
    }

    @Override
	public Staff getStaffByIdStaff(String idStaff) {
		// TODO Auto-generated method stub
		return stfDao.getStaffByIdStaff(idStaff);
	}
    
	@Override
	public void insertStaffSkill(int staffId, int skillId) {
		stfDao.insertStaffSkill(staffId, skillId);
		
	}

	@Override
	public List<Skill> getSkillStaff(int staffId, boolean isSkillStaff) {
		// TODO Auto-generated method stub
		return stfDao.getSkillStaff(staffId, isSkillStaff);
	}

	@Override
	public void deleteSkillStaff(int staffId) {
		// TODO Auto-generated method stub
		stfDao.deleteSkillStaff(staffId);
	}

	
}
