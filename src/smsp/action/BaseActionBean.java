package smsp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import smsp.bean.User;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;

public class BaseActionBean implements ActionBean {
	
	private ActionBeanContext context;
	private boolean isLoggedIn = false;
	private ValidationErrors errors;
	
	@Override
	public ActionBeanContext getContext() {
		return context;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.context = context;
	}

	/**
	 * Periksa session user. Berguna pada saat validasi status login.
	 * @return True jika ada session, False sebaliknya.
	 */
	public boolean haveSession() {
	    HttpServletRequest req = this.getContext().getRequest();
	    HttpSession sess = req.getSession();

	    if (sess.getAttribute("smspSession") != null)
		isLoggedIn = true;
	    
	    return isLoggedIn;
	}
	    
	public void setLoggedIn(boolean isLoggedIn) {
	    this.isLoggedIn = isLoggedIn;
	}
	
	public boolean isAdmin() {
	    HttpServletRequest req = this.getContext().getRequest();
	    HttpSession sess = req.getSession();
	    return ((User) sess.getAttribute("smspSession")).getRule() == "admin";
	}

	/**
	 * Set pesan atau notifikasi untuk pemakaian secara global.
	 * @param msg Pesan yang ingin disampaikan / ditampilkan.
	 */
	public void setGlobalMessage(String msg) {
	    context.getMessages().add(new SimpleMessage(msg));
	}
	
	/**
	 * Set pesan error validasi pada field tertentu.
	 * @param field Field yang ingin dihubungkan dengan error ini. Parameter ini harus berupa "String". 
	 * @param error Pesan error yang ingin ditampilkan. Parameter ini juga bertype String
	 */
	public void setCustomValidationError(String field, String error) {
	    if (errors == null)
		errors = new ValidationErrors();
	    
	    errors.add(field, new SimpleError(error));
	    context.setValidationErrors(errors);
	}

}
