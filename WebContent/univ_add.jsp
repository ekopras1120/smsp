<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		
			<h4>New Data University</h4>
			<stripes:errors globalErrorsOnly="true" />
			<stripes:form action="/action/university" method="post" id="univ">
				<ul>
					<li>
						<stripes:label for="name">University :</stripes:label>
						<stripes:text id="name" class="input_text" name="univName" value="" />
						<stripes:errors field="univName"></stripes:errors>
					</li>
					<li>
						<stripes:label for="address">Address :</stripes:label>
						<stripes:text id="address" class="input_text" name="univAddress" value="" /> <!-- name sama dg nama objek n method si acyin bean -->
						<stripes:errors field="univAddress"></stripes:errors>
					</li>
				</ul>
				<ul>
					<stripes:submit name="addUniversity" value="Add" /> <!-- name sama dg nama method si acyin bean -->
					<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>				
				</ul>
			</stripes:form>
	
	</stripes:layout-component>
</stripes:layout-render>

