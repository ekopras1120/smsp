<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		
		<h4>Project</h4>
		<a href="project?addPage=">Add Project</span></a>
		
		<stripes:messages/>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">No.</th>
					<th scope="col">Project Name</th>
					<th scope="col">Value</th>
					<c:if test="${sessionScope.smspSession.rule == 'admin'}">
						<th scope="col">Actions</th>
					</c:if>
					
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${actionBean.listProjectSize!=0}">
						<c:set var="i" value="${(actionBean.limit * (actionBean.pageNo-1))+1}"></c:set>				
						<c:forEach items="${actionBean.listProject}" var="listProjectItem" >				
							<tr>
								<td scope="row">${i}</td>
								<td>${listProjectItem.projectName}</td>
								<td>$ ${listProjectItem.projectValue}</td>
								<c:if test="${sessionScope.smspSession.rule == 'admin' }">
								<td>									
									<a href="project?update=&id=${listProjectItem.projectId}">Edit</a> |					
									<a href="project?delete&id=${listProjectItem.projectId}">Delete</a>							
								</td>
								</c:if>
								
							</tr>	
							<c:set var="i" value="${i+1}"></c:set>				
						</c:forEach>
					</c:when>
					<c:otherwise> 
						<tr>
							<td colspan="3">No data</td>
						</tr>
					</c:otherwise>
				</c:choose>						
			</tbody>
		</table>		
			
		<c:if test="${actionBean.pageNo>1}">
			<a href="project">&lsaquo; First</a>
			<a href="project?doSearch=&pageNo=${actionBean.pageNo-1}&search=${actionBean.search}">&lsaquo; Prev</a>
		</c:if>
		<c:forEach var="i" begin="1" end="${actionBean.totalPage}">
			<c:if test="${actionBean.totalPage!=1}">
				<c:if test="${actionBean.pageNo==i}">
					<strong>${i}</strong>
				</c:if>
				<c:if test="${i!=actionBean.pageNo}">
					<a href="project?doSearch=&pageNo=${i}&search=${actionBean.search}">${i}</a>	
				</c:if>
			</c:if>
		</c:forEach>
		<c:if test="${actionBean.totalPage>1 && actionBean.totalPage!=actionBean.pageNo}">
			<a href="project?doSearch=&pageNo=${actionBean.pageNo+1}&search=${actionBean.search}">Next &rsaquo;</a>
			<a href="project?doSearch=&pageNo=${(actionBean.totalPage)}&search=${actionBean.search}">Last &rsaquo;</a>
		</c:if> 
				
		
	</stripes:layout-component>
</stripes:layout-render>