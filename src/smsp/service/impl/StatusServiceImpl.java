package smsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import smsp.bean.Status;
import smsp.dao.StatusDao;
import smsp.service.StatusService;


@Service("statusManager")
public class StatusServiceImpl implements StatusService{

	@Autowired
    private StatusDao statusDao;
		
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	@Override
	public List<?> getStatus(int page) {
		
		return statusDao.getStatus(page);
	}
	
	public List<?> getStatusAll() {
	    return statusDao.getStatusAll();
	}

	@Override
	public void insertStatus(String statusName, String statusDesc) {
		statusDao.insertStatus(statusName, statusDesc);
		
	}

	@Override
	public void updateStatus(int id, Status statusBean) {
		statusDao.updateStatus(id, statusBean);
		
	}

	@Override
	public Status getStatusById(int statusId) {
		return statusDao.getStatusById(statusId);
		
	}



	@Override
	public void deleteStatus(int statusId) {
		statusDao.deleteStatus(statusId);
		
	}

}
