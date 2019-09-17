package smsp.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import smsp.bean.User;
import smsp.dao.UserDao;
import smsp.util.DataProperties;
import smsp.util.Pagination;

@Repository("userDao")
public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {
    
    @Autowired
    public void createTemplate(SqlMapClient smc) {
	this.setSqlMapClient(smc);
    }

    @Override
    public List<?> getUser(int page) {
        float pageCount;
        int offset = (page - 1) * Pagination.LIMIT;
        
        Pagination.setTotalRow((Integer) getSqlMapClientTemplate().queryForObject("countUser"));
        pageCount = (float)Pagination.getTotalRow() / (float)Pagination.LIMIT;
        Pagination.setTotalPage((int) Math.ceil(pageCount));

	HashMap<String, Integer> preparedData = new HashMap<String, Integer>();
	preparedData.put("offset", offset);
	preparedData.put("limit", Pagination.LIMIT);
	return getSqlMapClientTemplate().queryForList("getUser", preparedData);
    }

    @Override
    public User getUserById(String userId) {
	return (User) getSqlMapClientTemplate().queryForObject("getUserById", userId);
    }

    @Override
    public User getUserForLogin(HashMap<String, String> loginData) {
	return (User) getSqlMapClientTemplate().queryForObject("getUserForLogin", loginData);
    }

    @Override
    public void insertUser(HashMap<String,String> data) {
	getSqlMapClientTemplate().insert("insertUser", data);
    }

    @Override
    public void updateUser(HashMap<String,String> data) {
	// update user tanpa perubahan password
	if (data.get("pwd") == null || data.get("pwd").equals(""))
	    getSqlMapClientTemplate().update("updateUserNoPassword", data);
	
	// update semua field user
	else
	    getSqlMapClientTemplate().update("updateUser", data);
    }
    
    @Override
    public void updateUser(String userId, String rule) {
	HashMap<String, String> userData = new HashMap<String, String>();
	userData.put("uid", userId);
	userData.put("rule", rule);
	getSqlMapClientTemplate().update("updateUserRule", userData);
    }    

    @Override
    public void deleteUser(String userId) {
	getSqlMapClientTemplate().delete("deleteUser", userId);
    }

}
