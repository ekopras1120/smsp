<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
			<h4>Edit Data Position</h4>
			<stripes:form id="position" action="/action/position" method="post">
				<stripes:errors globalErrorsOnly="true"/>
				<ul>					
					<li>
						<stripes:label for="position">Position :</stripes:label>
						<stripes:text class="input_text" id="position" name="posName" value="${actionBean.posBean.position}"></stripes:text>
						<stripes:errors field="posName"/>						
					</li>
					<li>
						<stripes:label for="desc">Description (optional) :</stripes:label>
						<stripes:textarea class="input_text" id="desc" name="posDesc" value="${actionBean.posBean.positionDescription}"></stripes:textarea>
					</li>		
					<li>
						<stripes:hidden name="posId" value="${actionBean.posBean.positionId}"></stripes:hidden>
						<stripes:submit name="doUpdate" value="Update"></stripes:submit>
						<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
					</li>			
				</ul>
			</stripes:form>
		
	</stripes:layout-component>
</stripes:layout-render>