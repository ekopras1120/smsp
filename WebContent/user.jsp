<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		<h4>User</h4>
		<a class="button" href="user?newEntry"><span class="choice"><span class="user">&nbsp;</span>Add User</span></a>
		<stripes:messages/>
		
		<table class="table">
			<thead>
				<tr>
					<th scope="col">No.</th>
					<th scope="col">Username</th>
					<th scope="col">Email</th>
					<th scope="col">Rule</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
			<a href="www.google.com" > test ini link ${contextPath }</a>
				<c:if test="${actionBean.userList!=null}">
					<c:set var="i" value="${(actionBean.limit * (actionBean.page-1))+1}"></c:set>
					<c:forEach items="${actionBean.userList}" var="item">
					<tr>
						<td scope="row">${i}</td>
						<td>${item.userName}</td>
						<td>${item.email }</td>
						<td>${item.rule}</td>
						<td>
							<a href="user?editEntry&uid=${item.userId}">Edit</a>
							&nbsp;|&nbsp;
							<a href="user?deleteEntry&uid=${item.userId}">Delete</a>					
						</td>
					</tr>
					<c:set var="i" value="${i+1}"></c:set>
					</c:forEach>
				
				</c:if>
				<c:if test="${actionBean.userList==null}">
					<tr>
						<td colspan="3">No user</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div class="pagination">
			<c:if test="${actionBean.totalPage>1}">
				<c:if test="${actionBean.page!=1}">
					<a href="user">&lsaquo; First</a>
					<c:if test="${actionBean.page==2}">
						<a href="user">&lsaquo; Prev</a>
					</c:if>
					<c:if test="${actionBean.page>2}">
						<a href="user?goTo&page=${actionBean.page-1}"><span>&lsaquo; Prev</span></a>
					</c:if>
				</c:if>
				
				<c:if test="${actionBean.page==1}">
					<strong>1</strong>
				</c:if>
				<c:if test="${actionBean.page!=1}">
					<a href="user">1</a>
				</c:if>
				
				<c:forEach var="i" begin="2" end="${actionBean.totalPage}">
					<c:if test="${actionBean.page==i}">
						<strong>${i}</strong>
					</c:if>
					<c:if test="${actionBean.page!=i}">
						<a href="user?goTo&page=${i}">${i}</a>
					</c:if>
				</c:forEach>
				
				<c:if test="${actionBean.page!=actionBean.totalPage}">
					<a href="user?goTo&page=${actionBean.page+1}">Next &rsaquo;</a>
					<a href="user?goTo&page=${actionBean.totalPage}">Last &rsaquo;</a>
				</c:if>
			</c:if>
			<div class="clearer"></div>
		</div>
	</stripes:layout-component>
</stripes:layout-render>