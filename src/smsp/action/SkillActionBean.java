package smsp.action;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import smsp.bean.Skill;
import smsp.service.SkillService;
import smsp.util.QueryResultHelper;
import smsp.util.SmspConstants;

@UrlBinding ("/action/skill")
public class SkillActionBean extends BaseActionBean {
	private int pageNo;
	private String search;
	private int totalPage;
	private List<Skill>listSkill;
		
	private int sklId;
	private String sklName;	
	private Skill sklBean;
	
	private String page_login="/login.jsp";
    private String page_skill="/skill.jsp";
    private String page_skill_add="/skill_add.jsp";
    private String page_skill_delete="/skill_delete.jsp";
    private String page_skill_update="/skill_update.jsp";
	
	@SpringBean
	private SkillService skillService;
	

	
	@DefaultHandler
	public Resolution show() {
		
		if (haveSession() == false){
    		return new ForwardResolution(page_login);
    	}else{
			if (pageNo<=0) pageNo = 1;			
			QueryResultHelper queryResultHelper = skillService.getSkillList(pageNo, search);	
			setTotalPage(queryResultHelper.totalPage);
			setListSkill((List<Skill>)queryResultHelper.list);
			return new ForwardResolution(page_skill);
    	}
	}
	
    public Resolution cancelAction() {
    	return show();
    }
	
	public Resolution doSearch(){
		return show();
	}
	
	public Resolution add(){
		return new ForwardResolution(page_skill_add);
	}
	
	public Resolution doAdd(){
		skillService.add(sklName);
		setGlobalMessage("A new skill added successfully");
		return show();
	}
	
	public Resolution update(){
		sklBean = skillService.getSkillById(sklId);
		return new ForwardResolution(page_skill_update);
	}
	
	public Resolution doUpdate(){
		skillService.update(sklId,sklName);
		setGlobalMessage("A skill updated successfully");
		return show();
	}
	
	public Resolution delete(){
		return new ForwardResolution(page_skill_delete);
	}
	
	public Resolution doDelete(){
		skillService.delete(sklId);
		setGlobalMessage("A project deleted successfully");
		return show();
	}
	
	@ValidationMethod(on={"doAdd","doUpdate"})
    public void validateLocation() {
		ValidationErrors errors = new ValidationErrors();
		
		if (sklName == null) {
			setCustomValidationError("sklName", "Please insert skill name");
		} else {
		    if (sklName.length() > 30) {
		    	setCustomValidationError("sklName", "Please enter no more than 30 characters");
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
	
	public List<Skill> getListSkill() {
		return listSkill;
	}
	
	public void setListSkill(List<Skill> listSkill) {
		this.listSkill = listSkill;
	}
	
	public int getListSkillSize() {
		return listSkill.size();
	}

	public void setSklBean(Skill skl) {
		this.sklBean = skl;
	}

	public Skill getSklBean() {
		return sklBean;
	}
	
	public void setSklId(int sklId) {
		this.sklId = sklId;
	}

	public int getSklId() {
		return sklId;
	}

	public void setSklName(String sklName) {
		this.sklName = sklName;
	}

	public String getSklName() {
		return sklName;
	}
}
