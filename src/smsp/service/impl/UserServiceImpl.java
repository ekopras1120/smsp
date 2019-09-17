package smsp.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smsp.bean.User;
import smsp.dao.UserDao;
import smsp.service.UserService;

@Service("userManager")
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<?> getUser(int page) {
	return userDao.getUser(page);
    }

    @Override
    public User getUserById(String userId) {
	return userDao.getUserById(userId);
    }

    @Override
    public User getUserForLogin(HashMap<String, String> loginData) {
	return userDao.getUserForLogin(loginData);
    }

    @Override
    public void insertUser(HashMap<String,String> data) {
	userDao.insertUser(data);
    }

    @Override
    public void updateUser(HashMap<String,String> data) {
	userDao.updateUser(data);
    }

    @Override
    public void updateUser(String userId, String rule) {
	userDao.updateUser(userId, rule);
    }

    @Override
    public void deleteUser(String userId) {
	userDao.deleteUser(userId);
    }

}
