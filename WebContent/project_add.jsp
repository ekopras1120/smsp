<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
			<h4>New Data Project</h4>
			
			<stripes:form id="project" action="/action/project" method="post">
				<div id="form_add_project">
					<table>
						<tr>
							<td><stripes:label for="name">Project Name</stripes:label></td>
							<td><stripes:text class="input_text" name="projectName"></stripes:text></td>
						</tr>
						<tr>
							<td><stripes:label for="name">Project Value</stripes:label></td>
							<td><stripes:text class="input_text" name="projectValue"></stripes:text></td>
						</tr>
					</table>			
					<table>
						<tr>
							<td><stripes:submit name="addNewProject" value="Submit"></stripes:submit></td>
							<td><stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit></td>
						
						</tr>	
					</table>
				</div>					
		</stripes:form>	
		
	</stripes:layout-component>
</stripes:layout-render>