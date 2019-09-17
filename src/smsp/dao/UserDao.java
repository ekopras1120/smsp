package smsp.dao;

import java.util.HashMap;
import java.util.List;

import smsp.bean.User;

public interface UserDao {

	public List<?> getUser(int page);
	
	public User getUserById(String userId);
	
	public User getUserForLogin(HashMap<String, String> loginData);
	
	public void insertUser(HashMap<String,String> data);
	
	public void updateUser(HashMap<String,String> data);
	
	public void updateUser(String userId, String rule);
	
	public void deleteUser(String userId);

}
