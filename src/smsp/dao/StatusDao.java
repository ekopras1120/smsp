package smsp.dao;

import java.util.List;

import smsp.bean.Status;

public interface StatusDao {
	
	public List<?> getStatus(int page);
	
	public List<?> getStatusAll();
	
	public void insertStatus(String statusName, String statusDesc);
	
	public Status getStatusById(int statusId);	
	
	public void updateStatus(int id, Status statusBean);
	
	public void deleteStatus(int statusId);
	
}
