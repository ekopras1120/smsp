<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		
			
			<h4>Skill</h4>
			<a href="skill?add=">Add Skill</a>
			<div class="clearer"></div>
					
			<stripes:messages/>			
			
			<table class="table">
			<thead>
				<tr>
					<th scope="col">No.</th>
					<th scope="col">Skill Name</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${actionBean.listSkillSize!=0}">
						<c:set var="i" value="${(actionBean.limit * (actionBean.pageNo-1))+1}"></c:set>
						<c:forEach items="${actionBean.listSkill}" var="listSkillItem" >				
							<tr>
								<td scope="row">${i}</td>
								<td>${listSkillItem.skillName}</td>
								<td>
									<a href="skill?update&sklId=${listSkillItem.skillId}">Edit</a>
									&nbsp;|&nbsp;
									<a href="skill?delete&sklId=${listSkillItem.skillId}&skillName=${listSkillItem.skillName}">Delete</a>
								</td>
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
				<a href="skill">&lsaquo; First</a>
				<a href="skill?doSearch=&pageNo=${actionBean.pageNo-1}&search=${actionBean.search}">&lsaquo; Prev</a>
			</c:if>
			<c:forEach var="i" begin="1" end="${actionBean.totalPage}">
				<c:if test="${actionBean.totalPage!=1}">
					<c:if test="${actionBean.pageNo==i}">
						<strong>${i}</strong>
					</c:if>
					<c:if test="${i!=actionBean.pageNo}">
						<a href="skill?doSearch=&pageNo=${i}&search=${actionBean.search}">${i}</a>	
					</c:if>
				</c:if>
			</c:forEach>
			<c:if test="${actionBean.totalPage>1 && actionBean.totalPage!=actionBean.pageNo}">
				<a href="skill?doSearch=&pageNo=${actionBean.pageNo+1}&search=${actionBean.search}">Next &rsaquo;</a>
				<a href="skill?doSearch=&pageNo=${(actionBean.totalPage)}&search=${actionBean.search}">Last &rsaquo;</a>
			</c:if> 		

		
	</stripes:layout-component>
</stripes:layout-render>