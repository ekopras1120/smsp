package smsp.service;

import java.util.List;

import smsp.bean.Position;
import smsp.util.QueryResultHelper;

public interface PositionService {
	
	public QueryResultHelper getPositionList(int pageNo, String search);
	
	public Position getPositionByName(String name);
	
	public List<?> getPositionAll();
	
	public Position getPositionById(int id);
		
	public void add(String posName, String posDesc);
	
	public void update(int posId, String posName, String posDesc);
	
	public void delete (int posId); 
}
