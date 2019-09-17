<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		
			<p>
				Are you sure want to delete?
			</p>
			
			<stripes:form action="/action/project" method="post">
				<stripes:hidden name="id" value="${actionBean.id}"></stripes:hidden>
				<stripes:submit name="doDelete" value="Delete"></stripes:submit>
				<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
			</stripes:form>		
		
	</stripes:layout-component>
</stripes:layout-render>