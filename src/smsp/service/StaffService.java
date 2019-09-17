package smsp.service;

import java.util.List;

import smsp.bean.Skill;
import smsp.bean.Staff;

public interface StaffService {

    public List<Staff> getStaff(int page);

    public Staff getStaffById(int staffId);

    public List<?> getSkillByStaffId(int stf_id);
    
    public List<?> getProjectByStaffId(int stfId);

    public List<?> getSearchStaff(Staff staffBean);

    public List<?> getAllStaff();
    
    public List<?> getStaffReportingTo();

    public void insertStaff(Staff data);

    public void updateStaff(Staff data);

    public void deleteStaff(int staffId);
    
    public Staff getStaffByIdStaff(String idStaff);
    
    public void insertStaffSkill(int stafId, int skillId);
    
    public List<Skill> getSkillStaff(int staffId, boolean isSkillStaff);
    
    public void deleteSkillStaff(int staffId);
    
}
