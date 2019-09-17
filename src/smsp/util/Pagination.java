package smsp.util;

public class Pagination {

    public static final String PARAM_PAGED = "page";
    public static final int LIMIT = 10;
    private static int currentPage = 1;
    private static int totalPage = 1;
    private static int totalRow = 0;
    
    public static void setCurrentPage(int currentPage) {
	Pagination.currentPage = currentPage;
    }

    public static int getCurrentPage() {
	return currentPage;
    }

    public static void setTotalPage(int totalPage) {
	Pagination.totalPage = totalPage;
    }
    
    public static int getTotalPage() {
	return totalPage;
    }

    public static void setTotalRow(int totalRow) {
	Pagination.totalRow = totalRow;
    }

    public static int getTotalRow() {
	return totalRow;
    }
}
