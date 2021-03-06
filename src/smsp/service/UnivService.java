package smsp.service;

import java.util.List;
import smsp.bean.Univ;


public interface UnivService {
	
	public List<?> getUniv(int page);
	
	public List<Univ> getUnivAll();
	
	public void insertUniv(String univName, String univAddress);
	
	public Univ getUnivById(int univId);
	
	public void updateUniv(int id, String univName, String univAddress);
	
	public void deleteUniv(int univId);
	
	public List<?> searchUniv(String search);
	
}
