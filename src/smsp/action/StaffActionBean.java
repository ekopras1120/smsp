package smsp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import smsp.bean.Project;
import smsp.bean.Skill;
import smsp.bean.Staff;
import smsp.bean.StaffInProject;
import smsp.bean.StaffSkill;
import smsp.bean.Univ;
import smsp.service.PositionService;
import smsp.service.ProjectService;
import smsp.service.SkillService;
import smsp.service.StaffService;
import smsp.service.StatusService;
import smsp.service.UnivService;
import smsp.util.DateFormatter;
import smsp.util.ImageManipulation;
import smsp.util.Pagination;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

@UrlBinding("/action/staff")
public class StaffActionBean extends BaseActionBean {

    @SpringBean
    private StaffService staffService;
    @SpringBean
    private PositionService posSvc;
    @SpringBean
    private StatusService stsSvc;

    @SpringBean
    private UnivService unvSvc;
    @SpringBean
    private SkillService sklSvc;
    @SpringBean
    private ProjectService prjSvc;
    
    private String page_login="/login.jsp";
    private String page_staff="/staff.jsp";
    private String page_staff_add="/staff_add.jsp";
    private String page_staff_delete="/staff_delete.jsp";
    private String page_staff_update="/staff_update.jsp";

    private List<Staff> staffList;
    private List<?> listStaff;
    private List<?> stsList;
    private List<?> posList;
    private List<?> locList;
    private List<?> reportingToList;
    private List<Univ> unvList;
    private List<Skill> sklList;
    private List<Project> prjList;
    private List<StaffSkill> sklStfList;
    private List<StaffInProject> prjStfList;
    private List<Integer> listSkillChecked;
    private List<?> listSkillcheckedSample;
    private List<String> skillListForUpdate;
    private List<Skill> listSkillStaff;
    private List<Skill> listNotSkillStaff;
    
    private Staff staffBean;
    private Staff staffReportTo;
    
    private Skill skillBean;
    private FileBean photoFile;
    private String birthDate;
    private String joinDate;
    private String resignDate;

    private String nameReportTo;  
    private String batchNo;
    private String search;
    
    private LoginActionBean loginActionBean;
  
    private String birthDateTemp;
    private String joinDateTemp;


    @DefaultHandler
    public Resolution main() {
	if (getPage() > 1)
	    setPage(1);
	
		if (haveSession() == false){
			return new ForwardResolution(page_login);
		}else{
			staffList = staffService.getStaff(getPage());
			for (int i = 0;i<staffList.size();i++) {
				if(null != staffList.get(i).getJoinDate()) {
					String tempJoinDate = DateFormatter.dateToString(staffList.get(i).getJoinDate(), "MM-dd-yyyy");
					staffList.get(i).setJoinDateString(tempJoinDate);
				}
				if(null != staffList.get(i).getBirthDate()) {
					String tempBirthDate = DateFormatter.dateToString(staffList.get(i).getBirthDate(), "MM-dd-yyyy");
					staffList.get(i).setBirthDateString(tempBirthDate);
				}
				if(null != staffList.get(i).getResignDate()) {
					String tempResignDate = DateFormatter.dateToString(staffList.get(i).getResignDate(), "MM-dd-yyyy");
					staffList.get(i).setResignDateString(tempResignDate);				
				}
				listSkillStaff = staffService.getSkillStaff(staffList.get(i).getStaffId(), true);
				if (listSkillStaff.size() != 0) {
					String skillTemp = "";
					for (int j=0;j<listSkillStaff.size();j++) {
						String a = skillTemp.concat(listSkillStaff.get(j).getSkillName());
						String b = a.concat(",");
						skillTemp = b;
					}
					staffList.get(i).setSkills(skillTemp.substring(0, skillTemp.length()-1));
				}
			}
			return new ForwardResolution(page_staff);
		}
    }
    
    public Resolution goTo() {
		staffList = staffService.getStaff(getPage());
		setStsList(stsSvc.getStatusAll());
		setPosList(posSvc.getPositionAll());
		return new ForwardResolution(page_staff);
    }

    public Resolution doSearch(){
    	
    	staffList=(List<Staff>) staffService.getSearchStaff(staffBean);
    	setStsList(stsSvc.getStatusAll());
    	setPosList(posSvc.getPositionAll());
    
    	return new ForwardResolution(page_staff);
    }



    public Resolution newEntry() {
		setUnvList(unvSvc.getUnivAll());
		setStsList(stsSvc.getStatusAll());
		setPosList(posSvc.getPositionAll());
		setSklList(sklSvc.getSkill());
	return new ForwardResolution(page_staff_add);
    }
    
    public Resolution doInsert() throws IOException {
	/**	if (photoFile != null) {
		    File uploadPath = new File(getContext().getServletContext().getRealPath("/uploads/normal/" + photoFile.getFileName()));
		    File thumbPath = new File(getContext().getServletContext().getRealPath("/uploads/small/" + photoFile.getFileName()));
		    photoFile.save(uploadPath);
		    ImageManipulation.resize(uploadPath, thumbPath);
		    staffBean.setPhotoFilename(photoFile.getFileName());
		}
		
		*/
    
    	staffService.insertStaff(staffBean);
		Staff staffBean2 = staffService.getStaffByIdStaff(staffBean.getIdStaff());
		if (null != listSkillChecked) {
			for (int i=0; i<listSkillChecked.size();i++){
				staffService.insertStaffSkill(staffBean2.getStaffId(),listSkillChecked.get(i));
		}	
		}
		
		
	return main();
    }

    public Resolution update(){
    	setStsList(stsSvc.getStatusAll());
    	setPosList(posSvc.getPositionAll());
    	setUnvList(unvSvc.getUnivAll());
    	setSklList((List<Skill>) sklSvc.getSkill());
    	
 	
    	staffBean = staffService.getStaffById(staffBean.getStaffId());
    	if(null != staffBean.getBirthDate()) {
			String tempBirthDate = DateFormatter.dateToString(staffBean.getBirthDate(), "MM-dd-yyyy");
			staffBean.setBirthDateString(tempBirthDate);
		}
    	if(null != staffBean.getJoinDate()) {
			String tempJoinDate = DateFormatter.dateToString(staffBean.getJoinDate(), "MM-dd-yyyy");
			staffBean.setJoinDateString(tempJoinDate);
		}
    	if(null != staffBean.getResignDate()) {
			String tempResignDate = DateFormatter.dateToString(staffBean.getResignDate(), "MM-dd-yyyy");
			staffBean.setResignDateString(tempResignDate);
		}
  
    	listSkillStaff = staffService.getSkillStaff(staffBean.getStaffId(), true);
    	listNotSkillStaff = staffService.getSkillStaff(staffBean.getStaffId(), false);
       
    	return new ForwardResolution(page_staff_update);
    }  
    
    public Resolution doUpdate() throws IOException{
        /*		if (photoFile != null) {
        	    File uploadPath = new File(getContext().getServletContext().getRealPath("/uploads/normal/" + photoFile.getFileName()));
        	    File thumbPath = new File(getContext().getServletContext().getRealPath("/uploads/small/" + photoFile.getFileName()));
        	    photoFile.save(uploadPath);
        	    ImageManipulation.resize(uploadPath, thumbPath);
        	    staffBean.setPhotoFilename(photoFile.getFileName());
        	}
        	*/

    		staffService.deleteSkillStaff(staffBean.getStaffId());
        	staffService.updateStaff(staffBean);
        	if (null != listSkillChecked) {
    			for (int i=0; i<listSkillChecked.size();i++){
    				staffService.insertStaffSkill(staffBean.getStaffId(),listSkillChecked.get(i));
    			}
    		} 
        	return main();

        }  
       
    
    public Resolution doDelete(){
		System.out.println("rule: " + (isAdmin() ? "admin" : "user"));
		staffBean = staffService.getStaffById(staffBean.getStaffId());
	return new ForwardResolution(page_staff_delete);
    }
    
    public Resolution deleteStaff() {
    	staffService.deleteStaff(staffBean.getStaffId());
    	
    	return main();
        }

    public Resolution doPrint() throws JRException, FileNotFoundException {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		jasperReport = JasperCompileManager.compileReport(new FileInputStream(new File(getContext().getServletContext().getRealPath("/ireport/staff.jrxml"))));		
		listStaff = staffService.getAllStaff();
		JRDataSource datasource = new JRBeanCollectionDataSource(listStaff);
		/* Add DataSource */
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put(JRParameter.REPORT_DATA_SOURCE, datasource);
		/*Call Jasper Print*/
		jasperPrint = JasperFillManager.fillReport(jasperReport,map);
	
		FileOutputStream output = new FileOutputStream(new File(getContext().getServletContext().getRealPath("/ireport/" + jasperPrint.getName() + ".pdf")));
		//ServletOutputStream out = getContext().getResponse().getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, output);
	
	return new StreamingResolution("application/pdf", new FileInputStream(new File(getContext().getServletContext().getRealPath("/ireport/" + jasperPrint.getName() + ".pdf"))));
    }

    
    public Resolution cancelAction() {
	//return main();
    	return null;
    }

   
    
  /*  ==================
    @ValidationMethod(on="doInsert")
    public void validateStaff() {
	// validate ID
	if (staffBean.getStaffId() == 0) {
	    setCustomValidationError("staffBean.staffId", "Please enter staff ID");
	} else {
	    if (staffBean.getStaffId() > 10)
		setCustomValidationError("staffBean.staffId", "Please enter no more than 10 character");
	}
	
	// validate name
	if (staffBean.getStaffName() == null) {
	    setCustomValidationError("staffBean.fullName", "Please enter staff's name");
	} else {
	    if (staffBean.getStaffName().length() > 30)
		setCustomValidationError("staffBean.fullName", "Please enter no more than 30 characters");
	}

	// validate birthdate
	//if (staffBean.getBirthDate() == null) {
	//    setCustomValidationError("staffBean.birthDate", "Birth date is required");
	//}

	// validate address
	if (staffBean.getHomeAddress() == null) {
	    setCustomValidationError("staffBean.homeAddress", "Please enter home address");
	} else {
	    if (staffBean.getHomeAddress().length() > 200)
		setCustomValidationError("staffBean.homeAddress", "Please enter no more than 200 characters");
	}

	if (staffBean.getCurrentAddress() == null) {
	    setCustomValidationError("staffBean.currentAddress", "Please enter current address");
	} else {
	    if (staffBean.getCurrentAddress().length() > 200)
		setCustomValidationError("staffBean.currentAddress", "Please enter no more than 200 characters");
	}

	// validate phone
	if (staffBean.getPhoneNumber() != null) {
	    if (!staffBean.getPhoneNumber().matches("[0-9]+$"))
		setCustomValidationError("staffBean.phoneNumber", "Please enter number only");
	    else if (staffBean.getPhoneNumber().length() > 15)
		setCustomValidationError("staffBean.phoneNumber", "Please enter no more than 15 characters");
	}

	// validate mobile phone
	if (staffBean.getMobileNumber() == null) {
	    setCustomValidationError("staffBean.mobileNumber", "Mobile phone number is required");
	} else {
	    if (!staffBean.getMobileNumber().matches("[0-9]+$"))
		setCustomValidationError("staffBean.mobileNumber", "Please enter number only");
	    else if (staffBean.getMobileNumber().length() > 15)
		setCustomValidationError("staffBean.mobileNumber", "Please enter no more than 15 characters");
	}
	
	// validate batch
	if (batchNo != null) {
	    if (!batchNo.matches("[0-9]+$"))
		setCustomValidationError("batchNo", "Please enter number only");
	}

	// validate join date

	
	// validate batch number
	if (batchNo != null) {
	    if (!batchNo.matches("[0-9]+$"))
		setCustomValidationError("batchNo", "Please enter number only");
	}
	

	
	/* validasi projects
	if (staffBean.getProjects() != null) {
	    List<StaffInProject> stfPrj = staffBean.getProjects();
	    for (int i = 0; i < stfPrj.size(); i++) {
		if (stfPrj.get(i) != null) {
		    if (stfPrj.get(i).getProjectId() != 0) {
			// validasi start date
			if (stfPrj.get(i).getStartDate() == null) {
			    setCustomValidationError("staffBean.projects[" + i + "].startDate", "Please enter start date");
			}
		    }
		}
	    }
	}

	setUnvList(unvSvc.getUnivAll());
	setStsList(stsSvc.getStatusAll());
	setPosList(posSvc.getPositionAll());
	setSklList(sklSvc.getSkill());
	setPrjList(prjSvc.getProject());
	sklStfList = (List<StaffSkill>) staffService.getSkillByStaffId(staffBean.getStaffId());
    	prjStfList = (List<StaffInProject>) staffService.getProjectByStaffId(staffBean.getStaffId());
    }

*/
    // START LIST Getter and Setter
    public List<?> getStsList() {
        return stsList;
    }

    public void setStsList(List<?> stsList) {
        this.stsList = stsList;
    }

    public List<?> getPosList() {
        return posList;
    }

    public void setPosList(List<?> posList) {
        this.posList = posList;
    }

    public List<?> getLocList() {
        return locList;
    }

    public void setLocList(List<?> locList) {
        this.locList = locList;
    }
    
    public void setReportingToList(List<?> reportingToList) {
	this.reportingToList = reportingToList;
    }

    public List<?> getReportingToList() {
	return reportingToList;
    }

    public void setStaffList(List<Staff> staffList) {
	this.staffList = staffList;
    }

    public List<Staff> getStaffList() {
	return staffList;
    }

    public List<Univ> getUnvList() {
        return unvList;
    }

    public void setUnvList(List<Univ> unvList) {
        this.unvList = unvList;
    }

    public List<Skill> getSklList() {
        return sklList;
    }

    public void setSklList(List<Skill> sklList) {
        this.sklList = sklList;
    }

    public List<Project> getPrjList() {
        return prjList;
    }

    public void setPrjList(List<Project> prjList) {
        this.prjList = prjList;
    }

    public void setSklStfList(List<StaffSkill> sklStfList) {
	this.sklStfList = sklStfList;
    }

    public List<StaffSkill> getSklStfList() {
	return sklStfList;
    }

    public void setPrjStfList(List<StaffInProject> prjStfList) {
	this.prjStfList = prjStfList;
    }

    public List<StaffInProject> getPrjStfList() {
	return prjStfList;
    }
    // END LIST Getter and Setter

    public int getSklStfListSize() {
	return sklStfList.size();
    }

    public int getPrjStfListSize() {
	return prjStfList.size();
    }

    // START Beans Getter and Setter
    public Staff getStaffBean() {
	return staffBean;
    }

    public void setStaffBean(Staff stfBean) {
	this.staffBean = stfBean;
    }
    // END beans getter and setter

    public void setStaffReportTo(Staff staffReportTo) {
	this.staffReportTo = staffReportTo;
    }

    public Staff getStaffReportTo() {
	return staffReportTo;
    }

    public FileBean getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(FileBean photoFile) {
        this.photoFile = photoFile;
    }

    public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
    }

    public String getBirthDate() {
	return birthDate;
    }

    public void setJoinDate(String joinDate) {
	this.joinDate = joinDate;
    }

    public String getJoinDate() {
	return joinDate;
    }

    public void setResignDate(String resignDate) {
	this.resignDate = resignDate;
    }

    public String getResignDate() {
	return resignDate;
    }

    public void setNameReportTo(String nameReportTo) {
	this.nameReportTo = nameReportTo;
    }

    public String getNameReportTo() {
	return nameReportTo;
    }

    public void setBatchNo(String batchNo) {
	this.batchNo = batchNo;
    }

    public String getBatchNo() {
	return batchNo;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    // START Pagination methods
    public int getPage() {
	return Pagination.getCurrentPage();
    }

    public void setPage(int page) {
	Pagination.setCurrentPage(page);
    }

    public int getTotalPage() {
	return Pagination.getTotalPage();
    }

    public int getTotalRow() {
	return Pagination.getTotalRow();
    }

    public int getLimit() {
	return Pagination.LIMIT;
    }
    // END Pagination methods

	

	public List<?> getListSkillcheckedSample() {
		return listSkillcheckedSample;
	}

	public void setListSkillcheckedSample(List<?> listSkillcheckedSample) {
		this.listSkillcheckedSample = listSkillcheckedSample;
	}


	public String getJoinDateTemp() {
		return joinDateTemp;
	}

	public void setJoinDateTemp(String joinDateTemp) {
		this.joinDateTemp = joinDateTemp;
	}

	public String getBirthDateTemp() {
		return birthDateTemp;
	}

	public void setBirthDateTemp(String birthDateTemp) {
		this.birthDateTemp = birthDateTemp;
	}

	public List<?> getSkillListForUpdate() {
		return skillListForUpdate;
	}

	public void setSkillListForUpdate(List<String> skillListForUpdate) {
		this.skillListForUpdate = skillListForUpdate;
	}

	public String printaja(){
		return "ini tes aja yaa";
	}

	public Skill getSkillBean() {
		return skillBean;
	}

	public void setSkillBean(Skill skillBean) {
		this.skillBean = skillBean;
	}

	public List<Integer> getListSkillChecked() {
		return listSkillChecked;
	}

	public void setListSkillChecked(List<Integer> listSkillChecked) {
		this.listSkillChecked = listSkillChecked;
	}

	public List<Skill> getListSkillStaff() {
		return listSkillStaff;
	}

	public void setListSkillStaff(List<Skill> listSkillStaff) {
		this.listSkillStaff = listSkillStaff;
	}

	public List<Skill> getListNotSkillStaff() {
		return listNotSkillStaff;
	}

	public void setListNotSkillStaff(List<Skill> listNotSkillStaff) {
		this.listNotSkillStaff = listNotSkillStaff;
	}

}
