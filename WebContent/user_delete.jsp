<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		<p>
			Are you sure want to delete?
		</p>
		<stripes:form action="/action/user" method="post">
			<stripes:hidden name="uid" value="${actionBean.userBean.userId}"></stripes:hidden>
			<stripes:submit name="doDelete" value="Delete"></stripes:submit>
			<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>