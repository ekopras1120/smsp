package smsp.dao;

import java.util.List;

import smsp.bean.Skill;
import smsp.util.QueryResultHelper;

public interface SkillDao {
	
	public QueryResultHelper getSkillList(int pageNo, String searchSkill);
	
	public List<Skill> getSkill();
	
	public Skill getSkillByName(String name);
	
	public Skill getSkillById(int id);
	
	public void add(String sklName);
	
	public void delete(int sklId);
	
	public void update(int sklId, String sklName);
}
