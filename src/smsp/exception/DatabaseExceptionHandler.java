package smsp.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.AutoExceptionHandler;

public class DatabaseExceptionHandler implements AutoExceptionHandler {
	private boolean isDevMode = true;
	private String errorType = "";
	private String errorMessage = "";

	public Resolution handleSQL(SQLException ex, HttpServletRequest req,
			HttpServletResponse res) {
		errorType = "SQL Exception";
		errorMessage = isDevMode ? ex.getMessage() : "Sorry, the database has encountered a problem.";

		ForwardResolution fwd = new ForwardResolution("/error.jsp");
		fwd.addParameter("isDevMode", isDevMode);
		fwd.addParameter("errorType", errorType);
		fwd.addParameter("errorMessage", errorMessage);
		return fwd;
	}

	public Resolution handleDataIntegrity(DataIntegrityViolationException ex,
			HttpServletRequest req, HttpServletResponse res) {
		errorType = "Data Integrity Violation Exception";
		errorMessage = isDevMode ? ex.getMessage() : "Sorry, there was an error while transfering the data.";
		
		ForwardResolution fwd = new ForwardResolution("/error.jsp");
		fwd.addParameter("isDevMode", isDevMode);
		fwd.addParameter("errorType", errorType);
		fwd.addParameter("errorMessage", errorMessage);
		return fwd;
	}

}
