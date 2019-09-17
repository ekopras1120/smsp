<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		
			<p>
				Are you sure want to delete?
			</p>
			
			<stripes:form action="/action/position" method="post">
				<stripes:hidden name="posId" value="${actionBean.posBean.idPos}"></stripes:hidden>
				<stripes:submit name="doDelete" value="Delete"></stripes:submit>
				<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
			</stripes:form>
		
		
	</stripes:layout-component>
</stripes:layout-render>