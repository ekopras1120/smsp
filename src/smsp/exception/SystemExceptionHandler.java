package smsp.exception;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.AutoExceptionHandler;

public class SystemExceptionHandler implements AutoExceptionHandler {
	private boolean isDevMode = true;
	private String errorType = "";
	private String errorMessage = "";

	public Resolution handleClassNotFound(NoClassDefFoundError ex, HttpServletRequest req,
			HttpServletResponse res) {
		errorType = "Class Not Found Exception";
		errorMessage = isDevMode ? ex.getMessage() : "Sorry, our server has encountered a problem.";
		
		ForwardResolution fwd = new ForwardResolution("/error.jsp");
		fwd.addParameter("isDevMode", isDevMode);
		fwd.addParameter("errorType", errorType);
		fwd.addParameter("errorMessage", errorMessage);
		return fwd;
	}

	public Resolution handleNullPointer(NullPointerException ex, HttpServletRequest req,
			HttpServletResponse res) {
		errorType = "Null Pointer Exception";
		errorMessage = isDevMode ? ex.getMessage() : "Sorry, our server has encountered a problem.";
		
		ForwardResolution fwd = new ForwardResolution("/error.jsp");
		fwd.addParameter("isDevMode", isDevMode);
		fwd.addParameter("errorType", errorType);
		fwd.addParameter("errorMessage", errorMessage);
		return fwd;
	}

	public Resolution handleInvocationTarget(InvocationTargetException ex, HttpServletRequest req,
			HttpServletResponse res) {
		errorType = "Invocation Target Exception";
		errorMessage = isDevMode ? ex.getMessage() : "Sorry, our server has encountered a problem.";
		
		ForwardResolution fwd = new ForwardResolution("/error.jsp");
		fwd.addParameter("isDevMode", isDevMode);
		fwd.addParameter("errorType", errorType);
		fwd.addParameter("errorMessage", errorMessage);
		return fwd;
	}

	public Resolution handle(Exception ex, HttpServletRequest req,
			HttpServletResponse res) {
		errorType = "Default Exception";
		errorMessage = isDevMode ? ex.getMessage() : "Sorry, there was an error while processing the data.";
		
		ForwardResolution fwd = new ForwardResolution("/error.jsp");
		fwd.addParameter("isDevMode", isDevMode);
		fwd.addParameter("errorType", errorType);
		fwd.addParameter("errorMessage", errorMessage);
		return fwd;
	}

}
