package smsp.action;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import smsp.bean.Project;
import smsp.service.ProjectService;
import smsp.util.QueryResultHelper;
import smsp.util.SmspConstants;

@UrlBinding ("/action/project")
public class ProjectActionBean extends BaseActionBean {
	private int pageNo;
	private String search;
	private int totalPage;
	private List<Project> listProject = null;
		
	private int id;
	private String name;
	private Project projectBean;
	
	private String projectName;
	private int projectValue;
	private String projectLocation;
	
	private String page_login="/login.jsp";
    private String page_project="/project.jsp";
    private String page_project_add="/project_add.jsp";
    private String page_project_delete="/project_delete.jsp";
    private String page_project_update="/project_update.jsp";

	@SpringBean
	private ProjectService proService;

	@DefaultHandler
	public Resolution show(){
		
		if (haveSession() == false){
    		return new ForwardResolution(page_login);
    	}else{
	
			if (pageNo <=0) pageNo = 1;
				
			QueryResultHelper queryResultHelper = proService.getProjectList(pageNo, search);
				
			setTotalPage(queryResultHelper.totalPage);
			
			setListProject((List<Project>) queryResultHelper.list);
				
			return new ForwardResolution(page_project);
    	}
	}
	
    public Resolution cancelAction() {
    	return show();
    }
	
	public Resolution doSearch(){
		return show();
	}
	
	public Resolution addPage(){
		
	
		return new ForwardResolution(page_project_add);
	}
	

	public Resolution addNewProject(){
		Project project = new Project();
		project.setProjectName(getProjectName());
		project.setProjectValue(getProjectValue());	
		proService.addProject(project);		
		return show();
	}
	
	public Resolution update(){
		projectBean = proService.getProjectById(id);
	//	listLocation = locService.getLocation();
		return new ForwardResolution(page_project_update);
	}
	

	public Resolution doUpdate2(){
		Project project = new Project();
		project.setProjectId(getId());
		project.setProjectName(getProjectName());
		project.setProjectValue(getProjectValue());

		proService.updateProject(project);

		return show();
	}
	public Resolution delete(){
		return new ForwardResolution(page_project_delete);
	}
	
	public Resolution doDelete(){
		proService.deleteProject(id);
	
		return show();
	}

	@ValidationMethod(on={"doAdd","doUpdate"})
    public void validateLocation() {
		ValidationErrors errors = new ValidationErrors();
		
		if (name == null) {
			setCustomValidationError("name", "Please insert position name");
		} else {
		    if (name.length() > 50) {
		    	setCustomValidationError("name", "Please enter no more than 50 characters");
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

	public List<Project> getListProject() {
		return listProject;
	}

	public void setListProject(List<Project> listProject) {
		this.listProject = listProject;
	}
	
	public int getListProjectSize() {
		return listProject.size();
	}

	public Project getProjectBean() {
		return projectBean;
	}

	public void setProjectBean(Project projectBean) {
		this.projectBean = projectBean;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getProjectValue() {
		return projectValue;
	}

	public void setProjectValue(int projectValue) {
		this.projectValue = projectValue;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}
}
