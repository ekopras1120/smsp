package smsp.action;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import smsp.bean.Position;
import smsp.service.PositionService;
import smsp.util.QueryResultHelper;
import smsp.util.SmspConstants;

@UrlBinding ("/action/position")
public class PositionActionBean extends BaseActionBean {
	private int pageNo;
	private String search;
	private int totalPage;
	private List<Position>listPosition;
	
	private int posId;
	private String posDesc;
	private String posName;
	private Position posBean;
	
	private String page_login="/login.jsp";
    private String page_position="/position.jsp";
    private String page_position_add="/position_add.jsp";
    private String page_position_delete="/position_delete.jsp";
    private String page_position_update="/position_update.jsp";
	
	@SpringBean
	private PositionService posService;
	
	
	
	@DefaultHandler
	public Resolution show(){
		if (haveSession() == false){
    		return new ForwardResolution(page_login);
    	}else{
			if (pageNo <= 0) pageNo = 1;
				
			QueryResultHelper queryResultHelper = posService.getPositionList(pageNo, search);
			setTotalPage(queryResultHelper.totalPage);
			setListPosition((List<Position>) queryResultHelper.list);
				
			return new ForwardResolution(page_position);
    	}
	}
	
    public Resolution cancelAction() {
    	return show();
    }
	
	public Resolution doSearch() {
		return show();
	}
	
	public Resolution add(){
		return new ForwardResolution(page_position_add);
	}
	
	public Resolution doAdd(){
		posService.add(posName,posDesc);
		setGlobalMessage("A new position added successfully");
		return show();
	}
	
	public Resolution update(){		
		posBean = posService.getPositionById(posId);	
		return new ForwardResolution(page_position_update);
	}
	
	public Resolution doUpdate(){
		posService.update(posId,posName,posDesc);
		setGlobalMessage("A position updated successfully");
		return show();
	}
	
	public Resolution delete(){
		return new ForwardResolution(page_position_delete);
	}
	
	public Resolution doDelete(){
		posService.delete(posId);
		setGlobalMessage("A position deleted successfully");
		return show();	
	}
	
    @ValidationMethod(on={"doAdd","doUpdate"})
    public void validateLocation() {
		ValidationErrors errors = new ValidationErrors();
		
		if (posName == null) {
			setCustomValidationError("posName", "Please insert position name");
		} else {
		    if (posName.length() > 20) {
		    	setCustomValidationError("posName", "Please enter no more than 20 characters");
		    }
		}
    }
    
    public int getLimit(){
		return SmspConstants.LINE_PER_PAGE;
	}
		
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<Position> getListPosition() {
		return listPosition;
	}

	public void setListPosition(List<Position> position) {
		this.listPosition = position;
	}
	
	public int getListPositionSize() {
		return listPosition.size();
	}

	public void setPosBean(Position posBean) {
		this.posBean = posBean;
	}

	public Position getPosBean() {
		return posBean;
	}
	
	public void setPosId(int posId) {
		this.posId = posId;
	}

	public int getPosId() {
		return posId;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getPosName() {
		return posName;
	}
	
	public void setPosDesc(String posDesc) {
		this.posDesc = posDesc;
	}

	public String getPosDesc() {
		return posDesc;
	}
}
