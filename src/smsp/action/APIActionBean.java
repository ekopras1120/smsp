package smsp.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
@UrlBinding("/action/apibca")
public class APIActionBean extends BaseActionBean {
	
	 @DefaultHandler
	    public Resolution main() {
	    	
	    	if (haveSession() == false){
	    		return new ForwardResolution("/login.jsp");
	    	}else{
				
				return new ForwardResolution("/apibca.jsp");
	    	}
	    }
}
