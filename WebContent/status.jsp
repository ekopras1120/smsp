<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		
			<h4>Status</h4>
			<a href="status?doAdd">Add Status</a>     
			<div class="clearer"></div>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">No</th>
						<th scope="col">Status</th>
						<th scope="col">Description</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${actionBean.statusList!=null }">
					<c:set var="i" value="${(actionBean.limit*(actionBean.page-1))+1}"></c:set>
						<c:forEach items="${actionBean.statusList}" var="item" >
							<tr>
								
								<td scope="row">${i}</td>
								<td>${item.statusName}</td>
								<td>${item.statusDescription}</td>
								<td>
									<a href="status?doEdit&statusBean.statusId=${item.statusId}">Edit</a>
									&nbsp;|&nbsp;
									<a href="status?doDelete&statusBean.statusId=${item.statusId}">Delete</a>								
								</td>
							</tr>
							<c:set var="i" value="${i+1}"></c:set>
						</c:forEach>
					
					</c:if>
				</tbody>
			</table>
			
			<div id="pagination">
				<c:if test="${actionBean.totalPage>1}">
					<p>Halaman ${actionBean.page} dari ${actionBean.totalPage} halaman | Total data : ${actionBean.totalRow}</p>
					
					<!-- menampilkan First dan Prev -->
						<c:if test="${actionBean.page!=1&&actionBean.totalPage>1}">
							<a href="status"> &lsaquo;First</a>
						</c:if>
					
						<c:if test="${actionBean.page!=1&&actionBean.totalPage>1}">
							<a href="status?goTo&page=${(actionBean.page)-1}">&lsaquo;Prev</a>
						</c:if>
					
					<!-- menampilkan 1 2 3 4 5 -->
						<c:if test="${actionBean.page==1&&actionBean.totalPage>1}">
							<strong>1</strong>
						</c:if>
						<c:if test="${actionBean.page!=1}">
							<a href="status">1</a>
						</c:if>
					
					
						<c:forEach var="i" begin="2" end="${actionBean.totalPage }">
							<c:if test="${actionBean.page==i}">
								<strong>${i}</strong>
							</c:if>
							<c:if test="${actionBean.page!=i}">
								<a href="status?goTo&page=${i}">${i}</a>
							</c:if>
						</c:forEach>
						
					<!-- menampilkan First dan Prev -->
						<c:if test="${actionBean.page!=actionBean.totalPage&&actionBean.totalPage>1}">
							<a href="status?goTo&page=${(actionBean.page)+1}">Next&rsaquo; </a>
						</c:if>
						
						<c:if test="${actionBean.page!=actionBean.totalPage&&actionBean.totalPage>1}">
							<a href="status?goTo&page=${actionBean.totalPage}">Last &rsaquo;</a>
						</c:if>
					
				</c:if>
			</div>
		
	</stripes:layout-component>
</stripes:layout-render>