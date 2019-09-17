<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
	
		<h4>University</h4>
			<a href="university?doAdd">Add University</a>
			<div class="clearer"></div>			
			<stripes:form action="/action/university" method="post">
				
				<input type="text" name="search" value=""/> &nbsp;
				<input type="submit" name="doSearch" value="Filter" class="btn btn-info">
				
			</stripes:form>
					
				<table class="table">
					<thead>
						<tr>
							<th scope="col">No</th>
							<th scope="col">University</th>
							<th scope="col">Address</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					
					<tbody>
						<c:if test="${actionBean.univList!=null}">   
							<c:set var="i" value="${(actionBean.limit*(actionBean.page-1))+1}"></c:set>
								<c:forEach items="${actionBean.univList}" var="item"> 												
									<tr>
										<td scope="row">${i}</td>
										<td>${item.univName}</td>
										<td>${item.univAddress}</td>
										<td>
											<a href="university?doEdit&univBean.univId=${item.univId}">Edit</a> 
											&nbsp;|&nbsp;
											<a href="university?doDelete&univBean.univId=${item.univId}">Delete</a>
										</td>
									</tr>
									<c:set var="i" value="${i+1}"></c:set>
								</c:forEach>
						</c:if>
					</tbody>
				</table>
				
	
				<div class="pagination">
				
				<c:if test="${actionBean.totalPage>1}">
				<p>Halaman ${actionBean.page} dari ${actionBean.totalPage} halaman | Total data: ${actionBean.totalRow}</p>
					<c:if test="${actionBean.page!=1}">
						<a href="university"> &lsaquo; First</a>
						<c:if test="${actionBean.page==2}">
							<a href="university"> &lsaquo; Prev</a>
						</c:if>
						<c:if test="${actionBean.page>2}">
							<a href="university?goTo&page=${actionBean.page-1}"><span>&lsaquo; Prev</span></a>
						</c:if>
					</c:if>
					
					<c:if test="${actionBean.page==1&&actionBean.totalPage>1}">
						<strong>1</strong>
					</c:if>
					<c:if test="${actionBean.page!=1}">
						<a href="university">1</a>
					</c:if>
					
					<c:forEach var="i" begin="2" end="${actionBean.totalPage}">  
						<c:if test="${actionBean.page==i}">
							<strong>${i}</strong>
						</c:if>
						<c:if test="${actionBean.page!=i}">
							<a href="university?goTo&page=${i}">${i}</a>
						</c:if>
					</c:forEach>
					
					<c:if test="${actionBean.page!=actionBean.totalPage}">
						<a href="university?goTo&page=${actionBean.page+1}">Next &rsaquo;</a>
						<a href="university?goTo&page=${actionBean.totalPage}">Last &rsaquo;</a>
					</c:if>
				</c:if>
				<div class="clearer"></div>
			</div>
			
	
	
	</stripes:layout-component>
</stripes:layout-render>