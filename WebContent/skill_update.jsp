<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
			<h4>Edit Data Skill</h4>
			<stripes:form id="skill" action="/action/skill" method="post">
				<stripes:errors globalErrorsOnly="true"/>				
				<ul>					
					<li>
						<stripes:label for="skill_name">Skill Name :</stripes:label>
						<stripes:text class="input_text" id="skill_name" name="sklName" value="${actionBean.sklBean.skillName}"></stripes:text>
						<stripes:errors field="sklName"/>	
					</li>
					<li>
						<stripes:hidden name="sklId" value="${actionBean.sklBean.skillId}"></stripes:hidden>
						<stripes:submit name="doUpdate" value="Update"></stripes:submit>
						<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
					</li>
				</ul>
			</stripes:form>
			
	</stripes:layout-component>
</stripes:layout-render>