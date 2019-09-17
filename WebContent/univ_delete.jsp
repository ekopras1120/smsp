<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		
			<c:set var="del" value="${actionBean.univBean.univName}"></c:set>
			<stripes:errors globalErrorsOnly="true"></stripes:errors>
			<p>
				Are you sure want to delete?
			</p>
			<stripes:form action="/action/university" method="post">
				<stripes:hidden name="univBean.univId" value="${actionBean.univBean.univId}"></stripes:hidden>
				
				<stripes:submit name="deleteUniversity" value="Delete"></stripes:submit>
				<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
			</stripes:form>
	
	</stripes:layout-component>
</stripes:layout-render>