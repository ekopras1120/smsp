package smsp.service;

import java.util.List;

import smsp.bean.Skill;
import smsp.util.QueryResultHelper;

public interface SkillService {

	public QueryResultHelper getSkillList(int pageNo, String searchSkill);
	
	public List<Skill> getSkill();
	
	public Skill getSkillById(int id);
	
	public Skill getSkillByName(String name);
	
	public void add(String sklName);
	
	public void delete(int sklId);
	
	public void update(int sklId, String sklName);
}
