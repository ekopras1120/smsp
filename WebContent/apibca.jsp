<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
	
		<h4>Balance Information</h4>
		<h5>BCA Account</h5>
	
				<table class="table">
					<thead>
						<tr>
							<th scope="col">No</th>
							<th scope="col">University</th>
							<th scope="col">Address</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<!--  
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
					-->
				</table>

	</stripes:layout-component>
</stripes:layout-render>