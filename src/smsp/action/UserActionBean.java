package smsp.action;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bca.esb.util.SysProperties;

import smsp.bean.User;
import smsp.service.UserService;
import smsp.util.ConfigManager;
import smsp.util.DataProperties;
import smsp.util.Pagination;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import net.sourceforge.stripes.validation.ValidationState;

@UrlBinding("/action/user")
public class UserActionBean extends BaseActionBean {

    @SpringBean
    private UserService userSvc;
    
    private List<?> userList;
    private User userBean;
    private String uid;
    private String loginName = "";
    private String loginPwd = "";
    private String password2;
    private String rule;
  
 
    private String page_login="/login.jsp";
    private String page_user="/user.jsp";
    private String page_user_add="/user_add.jsp";
    private String page_user_delete="/user_delete.jsp";
    private String page_user_update="/user_update.jsp";
    
   // private static final Logger logger = Logger.getLogger("test");
  //  private String propFile="src/smsp.properties";
 //   public  String MESSAGE_PROP = DataProperties.getValueFromProperties(propFile, "page.user");
    
    public final static Logger logger = Logger.getLogger(UserActionBean.class.getName());  
    

    @Validate(mask = "[^0-9]+$", maxlength = 50, minlength = 3, required = true, on = {"doInsert", "doUpdate" })
    private String name;

    @Validate(converter = EmailTypeConverter.class, required = true, on = {"doInsert", "doUpdate" })
    private String email;

    @Validate(minlength = 3, required = true, on = "doInsert")
    private String password;

 
    
    public static void main(String[] args) {
    	UserActionBean obj = new UserActionBean();
    //	System.out.println("output from properties : "+obj.MESSAGE_PROP);
    }
    
    

    @DefaultHandler
    public Resolution main() {	
    	if (haveSession() == false){
    		return new ForwardResolution(page_login);
    	}else{
			if (getPage() > 1)
			    setPage(1);
			userList = userSvc.getUser(getPage());		
			return new ForwardResolution(page_user);
    	}
    }
     
    public Resolution goTo() {
	userList = userSvc.getUser(getPage());
	return new ForwardResolution(page_user);
    }
    
    // START of CRUD jsp pages method
    public Resolution newEntry() {
	return new ForwardResolution(page_user_add);
    }

    public Resolution editEntry() {
	userBean = userSvc.getUserById(uid);
	if (userBean != null)
	    return new ForwardResolution(page_user_update);
	else
	    return main();
    }

    public Resolution deleteEntry() {
	userBean = userSvc.getUserById(uid);
	if (userBean != null)
	    return new ForwardResolution(page_user_delete);
	else
	    return main();
    }

    @DontValidate
    public Resolution cancelAction() {
	return main();
    }


    public Resolution doInsert() {
	HashMap<String, String> data = new HashMap<String, String>();
	data.put("name", name);
	data.put("email", email);
	data.put("pwd", password);
	data.put("rule", rule);
	userSvc.insertUser(data);
	setGlobalMessage("success");
	return main();
    }

    public Resolution doUpdateRule() {
	userSvc.updateUser(uid, rule);
	setGlobalMessage("User rule changed successfully");
	return main();
    }

    public Resolution doUpdate() {
	HashMap<String, String> data = new HashMap<String, String>();
	data.put("uid", uid);
	data.put("name", name);
	data.put("email", email);
	data.put("pwd", password);
	data.put("rule", rule);
	userSvc.updateUser(data);
	setGlobalMessage("A user updated successfully");
	return main();
    }

    public Resolution doDelete() {
	userSvc.deleteUser(uid);
	setGlobalMessage("A user deleted successfully");
	return main();
    }

    // END CRUD actions

    // START custom validation
    @ValidationMethod(when = ValidationState.ALWAYS, on = { "doInsert",
	    "doUpdate" })
    public void passwordValidation() {
	ValidationErrors errors = new ValidationErrors();
	if (password != null) {
	    if (password2 != null) {
		if (password2.equals(password) == false) {
		    errors.add("password2", new SimpleError(
			    "Please type the same password."));
		    getContext().setValidationErrors(errors);
		}
	    } else {
		errors.add("password2", new SimpleError(
			"Please confirm the new password."));
		getContext().setValidationErrors(errors);
	    }
	}

	if (userBean == null) {
	    userBean = new User();
	    userBean.setRule(rule);
	} else {
	    userBean.setRule(rule);
	}
    }

    /**
     * Login process
     * 
     * @return Masuk ke halaman login jika gagal, masuk halaman utama jika
     *         berhasil
     */
    public Resolution doLogin() {
    System.out.println("start logging");
    logger.debug("debug logging");
    logger.error("error logger");
    logger.warn("warn logging");
    System.out.println("end logging");
    HttpServletRequest req = this.getContext().getRequest();
    HttpSession sess = req.getSession();
	HashMap<String, String> loginData = new HashMap<String, String>();
	loginData.put("uname", loginName);
	logger.debug("username login : "+loginName);
	loginData.put("pwd", loginPwd);
	logger.debug("password login : "+loginPwd);
	logger.debug("start process to get user from database!");
	userBean = userSvc.getUserForLogin(loginData);

	if (userBean != null) {
//	if (true) {
	    sess.setAttribute("smspSession", userBean);
	    	return new ForwardResolution("/action/home");
		}else {
			return new ForwardResolution(page_login);
		}
    }

    /**
     * Proses log out user, hapus semua session
     * 
     * @return loncat ke halaman utama
     */
    public Resolution doLogout() {
    HttpServletRequest req = this.getContext().getRequest();
   	HttpSession sess = req.getSession();
	sess.removeAttribute("smspSession");
	return new ForwardResolution("/action/login");
    }

    public String getLoginName() {
	return loginName;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }
    public String getLoginPwd() {
	return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
	this.loginPwd = loginPwd;
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

    public List<?> getUserList() {
	return userList;
    }

    public void setUserList(List<?> userList) {
	this.userList = userList;
    }

    public void setUserBean(User userBean) {
	this.userBean = userBean;
    }

    public User getUserBean() {
	return userBean;
    }

    public String getUid() {
	return uid;
    }

    public void setUid(String uid) {
	this.uid = uid;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setPassword2(String password2) {
	this.password2 = password2;
    }

    public String getPassword2() {
	return password2;
    }

    public String getRule() {
	return rule;
    }

    public void setRule(String rule) {
	this.rule = rule;
    }
}
