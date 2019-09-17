package smsp.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smsp.bean.Univ;
import smsp.service.UnivService;
import smsp.util.Pagination;
// import stripes
//import net.sf.jasperreports.engine.JRDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRParameter;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.view.JasperViewer;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/action/university")
public class UnivActionBean extends BaseActionBean {

    @SpringBean
    private UnivService univService;

    private Univ univBean;
    private List<?> univList; // list
    private String univName; // add
    private String univAddress; // add
    private String search;
    private List<?> listUniv;

    private String page_login="/login.jsp";
    private String page_univ="/univ.jsp";
    private String page_univ_add="/univ_add.jsp";
    private String page_univ_delete="/univ_delete.jsp";
    private String page_univ_update="/univ_update.jsp";

    @DefaultHandler
    public Resolution main() {
    	
    	if (haveSession() == false){
    		return new ForwardResolution(page_login);
    	}else{
			if (getPage() > 1)
			    setPage(1);
		
			univList = univService.getUniv(getPage());
			return new ForwardResolution(page_univ);
    	}
    }

    // public Resolution doPrint() throws Exception {
    // JasperReport jasperReport;
    // JasperPrint jasperPrint;
    // try{
    // System.out.println("Start Compile the .jrxml File");
    // jasperReport =
    // JasperCompileManager.compileReport("C:\\workspace\\smsp\\WebContent\\ireport\\university.jrxml");
    // System.out.println("Finish Compile!");
    // // Make list
    // listUniv=univService.getAll();
    //
    // // Make DataSource list
    // JRDataSource datasource = new JRBeanCollectionDataSource(listUniv);
    //
    // /* Add DataSource */
    // Map<Object, Object> map = new HashMap<Object, Object>();
    // map.put(JRParameter.REPORT_DATA_SOURCE, datasource);
    // /*Call Jasper Print*/
    // jasperPrint=JasperFillManager.fillReport(jasperReport,map);
    // JasperExportManager.exportReportToPdfFile(jasperPrint, "University.pdf");
    // JasperViewer.viewReport(jasperPrint);
    // }
    //
    // catch (JRException e)
    // {
    // e.printStackTrace();
    // }
    // catch (SQLException sqlException){
    // sqlException.printStackTrace();
    // }
    // return new ForwardResolution("/univ.jsp");
    //
    // }

    public Resolution doAdd() {
	return new ForwardResolution(page_univ_add);
    }

    public Resolution addUniversity() { // method = name univ_add.jsp
	univService.insertUniv(univName, univAddress);
	return main();
    }

    public Resolution doEdit() {
	univBean = univService.getUnivById(univBean.getUnivId());
	return new ForwardResolution(page_univ_update);
    }

    public Resolution updateUniversity() { // method = name univ_update.jsp
	univService.updateUniv(univBean.getUnivId(), univName, univAddress);
	return main();
    }

    public Resolution doDelete() {
	univBean = univService.getUnivById(univBean.getUnivId());
	return new ForwardResolution(page_univ_delete);
    }

    public Resolution deleteUniversity() { // method = name univ_delete.jsp
	univService.deleteUniv(univBean.getUnivId());
	return main();
    }

    public Resolution cancelAction() {
	return main();
    }

    // ini method untuk paginasi//
    public Resolution goTo() {
	univList = univService.getUniv(getPage());
	return new ForwardResolution(page_univ);
    }

    public Resolution doSearch() {
	univList = univService.searchUniv(search);

	return new ForwardResolution(page_univ);
    }

    @ValidationMethod(on = { "addUniversity", "updateUniversity" })
    public void validateLocation() {
	ValidationErrors errors = new ValidationErrors();

	if (univName == null) {
	    errors.add("univName", new SimpleError(
		    "Please enter the name of university"));
	    getContext().setValidationErrors(errors);
	} else {
	    if (univName.length() < 3) {
		errors.add("univName", new SimpleError(
			"Please enter at least 3 character"));
		getContext().setValidationErrors(errors);
	    } else if (univName.length() > 50) {
		errors.add("univName", new SimpleError(
			"Please enter no more than 50 character"));
		getContext().setValidationErrors(errors);
	    }
	}
	if (univAddress == null) {
	    errors.add("univAddress", new SimpleError(
		    "Please enter the address of university"));
	    getContext().setValidationErrors(errors);
	} else {
	    if (univAddress.length() < 5) {
		errors.add("univAddress", new SimpleError(
			"Please enter at least 5 character"));
		getContext().setValidationErrors(errors);
	    } else if (univAddress.length() > 200) {
		errors.add("univAddress", new SimpleError(
			"Please enter no more than 200 character"));
		getContext().setValidationErrors(errors);
	    }
	}

    }
    
    
    public List<?> getListUniv() {
    	return listUniv;
        }

        public void setListUniv(List<?> listUniv) {
    	this.listUniv = listUniv;
        }

        public Univ getUnivBean() {
    	return univBean;
        }

        public void setUnivBean(Univ univBean) {
    	this.univBean = univBean;
        }

        // ditambah objek dan method untuk add
        public String getUnivName() {
    	return univName;
        }

        public void setUnivName(String univName) {
    	this.univName = univName;
        }

        public String getUnivAddress() {
    	return univAddress;
        }

        public void setUnivAddress(String univAddress) {
    	this.univAddress = univAddress;
        }

        // sampai sini

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

        // //////////////////////////////////////////////////////
        public List<?> getUnivList() {
    	return univList;
        }

        public void setUnivList(List<?> univList) {
    	this.univList = univList;
        }

        // //////////////////////
        public String getSearch() {
    	return search;
        }

        public void setSearch(String search) {
    	this.search = search;
        }

}
