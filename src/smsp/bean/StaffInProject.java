package smsp.bean;

import java.util.Date;

import smsp.util.DateFormatter;

public class StaffInProject {

	private String staffId;
	private int projectId;
	private String projectName;
	private Date startDate;
	private Date dueDate;

	public String getDueDateProject() {
		return DateFormatter.dateToString(dueDate, "dd-MMMM-yyyy") ;
	}

	public String getStartDateProject() {
		return DateFormatter.dateToString(startDate, "dd-MMMM-yyyy") ;
	}
	
	public String getDueDateProjectEdit() {
		return DateFormatter.dateToString(dueDate, "yyyy-MM-dd") ;
	}

	public String getStartDateProjectEdit() {
		return DateFormatter.dateToString(startDate, "yyyy-MM-dd") ;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public int getProjectId() {
		return projectId;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
