package smsp.util;

import java.util.List;

public class QueryResultHelper {
    public int pageNo;
    public int totalPage;
    public List<?> list;

    public QueryResultHelper() {
	
    }

    public QueryResultHelper(int pageNo, int totalPage, List<?> list) {
    	this.pageNo = pageNo;
    	this.totalPage = totalPage;
    	this.list = list;
    }
}
