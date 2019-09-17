package smsp.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import smsp.bean.Univ;
import smsp.dao.UnivDao;
import smsp.util.Pagination;

@Repository("univDao")
public class UnivDaoImpl extends SqlMapClientDaoSupport implements UnivDao{

	
	@Autowired
	public void createTemplate(SqlMapClient sqlMapClient){
		this.setSqlMapClient(sqlMapClient);
	}
	
	
	@Override
	public List<?> getUniv(int page) {
	
	     int offset = (page - 1) * Pagination.LIMIT;
	     
	     // untuk paginasi output set jumlah page
	     float pageCount;
	     Pagination.setTotalRow((Integer) getSqlMapClientTemplate().queryForObject("countUniv")); //di dalam setTotalRow angka
	     pageCount = (float)Pagination.getTotalRow() / (float)Pagination.LIMIT;
	     Pagination.setTotalPage((int) Math.ceil(pageCount)); //buat jumlah halaman, memanggil method yg static >> Class.namaMetho
	     //sd sini paginasi						 //ceil pmbulatan keatas 
	     
	     //list ditampilan awal 
		 HashMap<String, Integer> listData = new HashMap<String, Integer>();
		 listData.put("offset", offset);  // key(biru) sama dengan #....# di sqlmapuniv
		 listData.put("limit", Pagination.LIMIT);  //memanggil method yg static >> Class.namaMethod
				
		return getSqlMapClientTemplate().queryForList("getUniv", listData); //biru = id sqlMapUniv
	}
	
	@Override
	public List<Univ> getUnivAll() {
	    return getSqlMapClientTemplate().queryForList("getUnivAll");
	}
	
	
	@Override
	public void insertUniv(String univName, String univAddress) {
		HashMap<String, String> tambah = new HashMap<String, String>();
		tambah.put("name", univName); 
		tambah.put("address", univAddress); 
		getSqlMapClientTemplate().insert("addUniversity",tambah); //biru = id sqlMapUniv
		
	}
	@Override
	public Univ getUnivById(int univId) {
	
		return (Univ) getSqlMapClientTemplate().queryForObject("getUnvById", univId); //biru = id sqlMapUniv
	}

	@Override
	public void updateUniv(int id, String univName, String univAddress) {
		HashMap<String, String> ganti = new HashMap<String, String>(); //HashMap <key,value>
		ganti.put("id",Integer.toString(id)); // key(biru) sama dengan #....# di sqlmapuniv
		ganti.put("name",univName);
		ganti.put("address",univAddress);
		getSqlMapClientTemplate().update("updtUniversity",ganti); //biru = id sqlMapUniv
	
	}

	@Override
	public void deleteUniv(int univId) {
		getSqlMapClientTemplate().delete("delUniversity",univId); //biru = id sqlMapUniv
		
	}
	
	@Override
	public List<?> searchUniv(String search) {
		
		return getSqlMapClientTemplate().queryForList("getSearch", search); //biru = id sqlMapUniv.xml
	}
}
