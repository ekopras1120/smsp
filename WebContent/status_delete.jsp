<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		
			<c:set var="del" value="${actionBean.statusBean.statusName}"></c:set>
			<stripes:errors globalErrorsOnly="true"></stripes:errors>
			<p>
				Are you sure want to delete?
			</p>
			<stripes:form action="/action/status" method="post">
				<stripes:hidden name="statusBean.statusId" value="${actionBean.statusBean.statusId}"></stripes:hidden>	
				<stripes:submit name="delStatus" value="Delete"></stripes:submit>
				<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
			</stripes:form>
	
	</stripes:layout-component>
</stripes:layout-render>