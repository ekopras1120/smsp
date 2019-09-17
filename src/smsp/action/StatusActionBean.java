package smsp.action;

import java.util.List;

import smsp.bean.Status;
import smsp.service.StatusService;
import smsp.service.UnivService;
import smsp.util.Pagination;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/action/status")
public class StatusActionBean extends BaseActionBean {
	
	@SpringBean
	private StatusService statusService;
	
	private List<?> statusList; //list
	private Status statusBean; //add
	private String statusName; //add
	private String statusDesc;
	
	private String page_login="/login.jsp";
    private String page_status="/status.jsp";
    private String page_status_add="/status_add.jsp";
    private String page_status_delete="/status_delete.jsp";
    private String page_status_update="/status_update.jsp";
	
	@DefaultHandler
	public Resolution main(){
		
		if (haveSession() == false){
    		return new ForwardResolution(page_login);
    	}else{
			if (getPage()>1)
				setPage(1);
			statusList=statusService.getStatus(getPage());
			return new ForwardResolution(page_status);
    	}
	}

	public Resolution doAdd(){
		return new ForwardResolution(page_status_add);
	}
	
	public Resolution addStatus(){
		
		statusService.insertStatus(statusName, statusDesc); // gunakan method insert di StatusService
		return main();
	}
	
	public Resolution doEdit(){
		statusBean = statusService.getStatusById(statusBean.getStatusId());
		return new ForwardResolution(page_status_update);
	}
	
	public Resolution updStatus(){
		statusBean.setStatusName(getStatusName());
		statusBean.setStatusDescription(getStatusDesc());
		statusService.updateStatus(statusBean.getStatusId(), statusBean);
		return main();
	}
	
	public Resolution doDelete(){
		statusBean=statusService.getStatusById(statusBean.getStatusId());
		return new ForwardResolution(page_status_delete);
	}
	
	public Resolution delStatus(){
		statusService.deleteStatus(statusBean.getStatusId());
		return main();
	}
	
	public Resolution cancelAction(){
		return main();
	}
	
	//untuk paginasi 
	public Resolution goTo(){
		statusList=statusService.getStatus(getPage());
		return new ForwardResolution(page_status);
	}
	
	@ValidationMethod(on={"addStatus","updStatus"})
    public void validateLocation() {
	ValidationErrors errors = new ValidationErrors();
	
	if (statusName == null) {
	    errors.add("statusName", new SimpleError("Please enter the name of staff status"));
	    getContext().setValidationErrors(errors);
	} else {
	    if (statusName.length() < 3) {
		errors.add("statusName", new SimpleError("Please enter at least 3 character"));
		getContext().setValidationErrors(errors);
	    } else if (statusName.length() > 20) {
		errors.add("statusName", new SimpleError("Please enter no more than 20 character"));
		getContext().setValidationErrors(errors);
	    }
	}
	
	
    }

	public Status getStatusBean() {
		return statusBean; 
	}
	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}

	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public List<?> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<?> statusList) {
		this.statusList = statusList;
	}
	
	
	public int getPage(){
		return Pagination.getCurrentPage();
	}
	public void setPage(int page){
		Pagination.setCurrentPage(page);
	}
	public int getTotalPage(){
		return Pagination.getTotalPage();
	}
	public int getTotalRow(){
		return Pagination.getTotalRow();
	}
	public int getLimit() {
		return Pagination.LIMIT;
	    }
}
