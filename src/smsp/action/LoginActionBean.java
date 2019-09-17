package smsp.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/action/login")
public class LoginActionBean extends BaseActionBean{

	
	   @DefaultHandler
	    public Resolution main() {    	
		   return new ForwardResolution("/login.jsp");
	    }
}