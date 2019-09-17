package smsp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import smsp.bean.Position;
import smsp.dao.PositionDao;
import smsp.util.QueryResultHelper;
import smsp.util.SmspConstants;
import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("positionDao")
public class PositionDaoIbatisImpl extends SqlMapClientDaoSupport implements PositionDao {

	private static Log log = LogFactory.getLog(PositionDaoIbatisImpl.class);
	
	@Autowired
	public void createTemplate(SqlMapClient sqlMapClient){
		this.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public List<?> getPositionAll() {
	    return getSqlMapClientTemplate().queryForList("getPositionAll");
	}
	
	@Override
	public Position getPositionByName(String name) {
		return (Position) getSqlMapClientTemplate().queryForObject("getPositionByName",name);
	}

	@Override
	public QueryResultHelper getPositionList(int pageNo, String search) {
		if (pageNo<=0) pageNo = 1;
		
		Object obj = getSqlMapClientTemplate().queryForObject("countPosition",search);
		log.debug("Jumlah position = " + obj);
		
		int totalRecord = (Integer) obj;
		int startRow = SmspConstants.LINE_PER_PAGE * (pageNo-1);
		
		float totalPage = (float)totalRecord/(float)SmspConstants.LINE_PER_PAGE;
		int iTotalPage = (int)Math.ceil(totalPage);
		
		Map map = new HashMap();
		map.put("start", startRow);
		map.put("length", SmspConstants.LINE_PER_PAGE);
		map.put("name", search);
		
		List<Position>list = getSqlMapClientTemplate().queryForList("getPositionByName", map);
		
		return new QueryResultHelper(pageNo, iTotalPage, list);
	}

	@Override
	public Position getPositionById(int id) {
		return (Position) getSqlMapClientTemplate().queryForObject("getPositionById",id);
	}
	
	@Override
	public void add(String posName, String posDesc) {
		HashMap data = new HashMap();
		data.put("posName", posName);
		data.put("posDesc", posDesc);
		
		getSqlMapClientTemplate().insert("addPosition", data);
		
	}

	@Override
	public void update(int posId, String posName, String posDesc) {
		HashMap data = new HashMap();
		data.put("posId", Integer.toString(posId));
		data.put("posName", posName);
		data.put("posDesc", posDesc);
		
		getSqlMapClientTemplate().update("updatePosition", data);
		
	}

	@Override
	public void delete(int posId) {
		getSqlMapClientTemplate().delete("deletePosition",posId);
	}
}
