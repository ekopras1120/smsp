package smsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smsp.bean.Skill;
import smsp.dao.SkillDao;
import smsp.service.SkillService;
import smsp.util.QueryResultHelper;

@Service("skillManager")
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillDao skillDao;
	
	public List<Skill> getSkill() {
	    return skillDao.getSkill();
	}
	
	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	@Override
	public Skill getSkillByName(String name){
		return skillDao.getSkillByName(name);
	}

	@Override
	public QueryResultHelper getSkillList(int pageNo, String searchSkill) {
		return skillDao.getSkillList(pageNo, searchSkill);
	}

	@Override
	public Skill getSkillById(int id){
		return skillDao.getSkillById(id);
	}

	@Override
	public void add(String sklName){
		skillDao.add(sklName);		
	}

	@Override
	public void update(int sklId, String sklName){
		skillDao.update(sklId, sklName);		
	}

	@Override
	public void delete(int sklId){
		skillDao.delete(sklId);
	}

}
