<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
	
			<h4>New Data Status</h4>
			<stripes:errors globalErrorsOnly="true"/>
			<stripes:form action="/action/status" method="post" id="status">
				<ul>
					<li>
						<stripes:label for="name">Status :</stripes:label>
						<stripes:text class="input_text" id="name" name="statusName" value=""/>
						<stripes:errors field="statusName"></stripes:errors>
					</li>
				
					<li>
						<stripes:label for="desc">Status Description :</stripes:label>
						<stripes:textarea class="input_text" id="desc" name="statusDesc" value=""/>
					</li>
				</ul>
				<ul>
					<li>
						<stripes:submit name="addStatus" value="Add"/>
						<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
					</li>
				</ul>		
			</stripes:form>

	</stripes:layout-component>
</stripes:layout-render>