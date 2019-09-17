<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		<h3>There was An Error</h3>
		<ul>
			<li>error code: ${param.errorType}</li>
			<li>message: ${param.errorMessage}</li>
		</ul>
	</stripes:layout-component>
</stripes:layout-render>