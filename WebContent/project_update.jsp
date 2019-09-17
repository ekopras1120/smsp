<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
			<h4>Edit Data Project</h4>
			
			<stripes:form action="/action/project" method="post">
				<div id="form_edit_project">
					<table>
						<tr>
							<td><stripes:label for="name">Project Name</stripes:label></td>
						 	<td><stripes:text class="input_text" name="projectName" >${actionBean.projectBean.projectName}</stripes:text></td>
						</tr>
						<tr>
							<td><stripes:label for="name">Project Value</stripes:label></td>
							<td><input type="number" class="input_text" name="projectValue" value="${actionBean.projectBean.projectValue}"></input></td>
							
						</tr>
						
					</table>			
							<stripes:hidden name="id" value="${actionBean.projectBean.projectId}"/>
							<stripes:submit name="doUpdate2" value="Update"></stripes:submit>		
							<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
						
					
				</div>					
			</stripes:form>	
		
	</stripes:layout-component>
</stripes:layout-render>	

