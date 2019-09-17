package smsp.interceptor;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

@Intercepts(LifecycleStage.HandlerResolution)
public class SecurityInterceptor implements Interceptor {

	@Override
	public Resolution intercept(ExecutionContext arg0) throws Exception {
		// TODO Auto-generated method stub
		return new ForwardResolution("/home.jsp");
	}
/*
    @Override
    public Resolution intercept(ExecutionContext ctx) throws Exception {
	Resolution resolution;
	ActionBeanContext abc = ctx.getActionBeanContext();

	if (isLoggedIn(abc)) {
	    resolution = ctx.proceed();
	} else if (!isLoggedIn(abc) && isLoginCheck(abc)) {
	    resolution = ctx.proceed();
	} else {
	    ctx.proceed();
	    resolution = new ForwardResolution("/login.jsp");
	}

	return resolution;
    }

    public boolean isLoggedIn(ActionBeanContext ctx) {
	HttpServletRequest request = ctx.getRequest();
	Object session = request.getSession().getAttribute("smspSession");
	return session != null;
    }

    public boolean isLoginCheck(ActionBeanContext ctx) {
	Map<?, ?> params = ctx.getRequest().getParameterMap();
	return params.containsKey("doLogin");
    }
*/
}
