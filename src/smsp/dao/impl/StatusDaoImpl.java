package smsp.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import smsp.bean.Status;
import smsp.dao.StatusDao;
import smsp.util.Pagination;


@Repository ("StatusDao")
public class StatusDaoImpl extends SqlMapClientDaoSupport implements StatusDao {

	@Autowired
	public void createTemplate(SqlMapClient sqlMapClient){
		this.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public List<?> getStatus(int page){
		int offset=(page-1)*Pagination.LIMIT;
		
		//untuk paginasi
		float pageCount;
		Pagination.setTotalRow((Integer) getSqlMapClientTemplate().queryForObject("countList"));
		pageCount=(float)Pagination.getTotalRow()/(float)Pagination.LIMIT;
		Pagination.setTotalPage((int) Math.ceil(pageCount));		
		// sampai sini
		
		HashMap<String, Integer> listData = new HashMap<String, Integer>();
		listData.put("offset", offset);
		listData.put("limit", Pagination.LIMIT);
		return getSqlMapClientTemplate().queryForList("getList",listData) ;
	}
	
	public List<?> getStatusAll() {
	    return getSqlMapClientTemplate().queryForList("getStatusAll");
	}

	@Override
	public void insertStatus(String statusName, String statusDesc) {
		
		HashMap<String, String> tambah = new HashMap<String, String>();
		tambah.put("name", statusName);
		tambah.put("desc",statusDesc);
		getSqlMapClientTemplate().insert("inStatus",tambah);
	}


	@Override
	public Status getStatusById(int statusId) {
		return (Status) getSqlMapClientTemplate().queryForObject("getStsById",statusId);
		
	}
	@Override
	public void updateStatus(int id, Status statusBean) {
		HashMap<String, String> ganti = new HashMap<String, String>();
		ganti.put("id", Integer.toString(id));
		ganti.put("status", statusBean.getStatusName());
		ganti.put("description", statusBean.getStatusDescription());
	
		getSqlMapClientTemplate().update("upStatus",ganti);	
	}


	@Override
	public void deleteStatus(int statusId) {
		getSqlMapClientTemplate().delete("deltStatus", statusId);
		
	}


	

	
}
