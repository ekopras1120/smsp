package smsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smsp.bean.Univ;
import smsp.dao.UnivDao;
import smsp.service.UnivService;


@Service("univManager")
public class UnivServiceImpl implements UnivService{

	@Autowired
    private UnivDao univDao;

    public void setUnivDao(UnivDao univDao) {
        this.univDao = univDao;
    }
    
	@Override
	public List<?> getUniv(int page) {
		return univDao.getUniv(page);
	}
	
	@Override
	public List<Univ> getUnivAll() {
	    return univDao.getUnivAll();
	}

	@Override
	public void insertUniv(String univName, String univAddress) {
		univDao.insertUniv(univName, univAddress);
	}

	@Override
	public void updateUniv(int id, String univName, String univAddress) {
		univDao.updateUniv(id, univName, univAddress);
	}

	@Override
	public void deleteUniv(int univId) {
		univDao.deleteUniv(univId);
	}

	@Override
	public Univ getUnivById(int univId) {
		return univDao.getUnivById(univId);
	}

	@Override
	public List<?> searchUniv(String search) {
		return univDao.searchUniv(search);
		
	}
	
}
