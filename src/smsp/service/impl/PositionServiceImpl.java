package smsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smsp.bean.Position;
import smsp.dao.PositionDao;
import smsp.service.PositionService;
import smsp.util.QueryResultHelper;

@Service("positionManager")
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionDao positionDao;
	
	@Override
	public Position getPositionByName(String name){
		return positionDao.getPositionByName(name);
	}

	@Override
	public QueryResultHelper getPositionList(int pageNo, String search) {
		return positionDao.getPositionList(pageNo, search);
	}
	
	@Override
	public List<?> getPositionAll() {
	    return positionDao.getPositionAll();
	}

	@Override
	public Position getPositionById(int id){
		return positionDao.getPositionById(id);
	}

	@Override
	public void update(int posId, String posName, String posDesc){
		positionDao.update(posId, posName, posDesc);
		
	}

	@Override
	public void add(String posName, String descName){
		positionDao.add(posName, descName);		
	}

	@Override
	public void delete(int posId){
		positionDao.delete(posId);		
	}

}
