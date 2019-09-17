<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">

		<h4>Staff</h4>
	<!--	<c:if test="${sessionScope.smspSession.rule == 'admin'}">
		 	<a class="button modal" href="staff?newEntry"><span><span class="new_btn"></span>New entry</span></a> 
		
		</c:if>-->
		
			<a href="staff?newEntry">Add Staff</a>
		
<!--  	<div id="filter_box">
			<stripes:form action="/action/staff" method="post" style="width:100%">
			<table>
				<tr>
					<td align="right" width="25%">
						<stripes:label for="sts">Position</stripes:label>
						<stripes:select id="pos" name="staffBean.posId">
							<c:choose>
								<c:when test="${actionBean.posList!=null}">
									<c:forEach items="${actionBean.posList}" var="posItem">
										<stripes:option value="${posItem.idPos}">${posItem.name}</stripes:option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<stripes:option value="0">No Position</stripes:option>
								</c:otherwise>
							</c:choose>
						</stripes:select>
					</td>
					<td width="45%">&nbsp;</td>
					<td width="30%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">
						<stripes:label for="sts">Status</stripes:label>
						<stripes:select id="sts" name="staffBean.stsId">
						
						
							<stripes:option value="0">Resigned</stripes:option>
							<stripes:option value="1">Current</stripes:option>
					
						</stripes:select>
					</td>
					<td>
						&nbsp;
						<stripes:label for="start">from: </stripes:label>
						<stripes:text class="input_text" id="start" name="staffBean.startDate" size="10"></stripes:text>
						<stripes:label for="end">to: </stripes:label>
						<stripes:text class="input_text" id="end" name="staffBean.endDate" size="10"></stripes:text>
					</td>
					<td>
						<stripes:label for="nameStaff">Staff Name</stripes:label>
						<input type="text" class="input_text" id="nameStaff" name="staffBean.fullName" size="10" value=""/>
						<input type="submit" name="doSearch" value="Go">
					</td>
				</tr>
			</table>
			</stripes:form>
		</div> -->
		<div class="clearer"></div>
		<stripes:messages />
		<table class="table">
			<thead>
				<tr>
					<th scope="col" width="15px">No</th>
					<th scope="col" width="60px">ID</th>
					<th scope="col" width="150px">Name</th>
					<th scope="col" width="50px">Gender</th>
					<th scope="col" width="80px">Birthdate</th>	
					<th scope="col" width="50px">Posisi</th>
					<th scope="col" width="120px">Skill</th>
					<th scope="col" width="200px">JoinDate</th>
					<th scope="col" width="50px">Status</th>
					<th scope="col" width="150px">ResignDate</th>
					<th scope="col" width="50px">Batch</th>
					<th scope="col" width="100px">Phone</th>
					<th scope="col" width="80px">Handphone</th>
					<th scope="col" width="80px">Alumni</th>
					<c:if test="${sessionScope.smspSession.rule == 'admin'}">
					<th scope="col" width="100px">Actions</th>
					</c:if>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:if test="${actionBean.totalRow>0}">
					<c:set var="i"
						value="${(actionBean.limit * (actionBean.page-1))+1}"></c:set>
					<c:forEach items="${actionBean.staffList}" var="item">
						<tr>
							<td scope="row" width="15px">${i}</td>
							<td width="60px">${item.idStaff}</td>
							<td width="150px">${item.staffName}</td>
							<c:if test="${item.gender == 0}">
								<td width="50px">Male</td>
							</c:if>
							<c:if test="${item.gender == 1}">
								<td width="50px">Female</td>
							</c:if>						
							<c:if test="${item.gender == null}">
								<td width="50px"></td>
							</c:if>
							<td width="80px">${item.birthDateString}</td>			
							<td width="50px">${item.position}</td>
							<td width="120px">${item.skills}</td>
							<td width="200px">${item.joinDateString}</td>
							<td width="50px">${item.status}</td>
							<td width="150px">${item.resignDateString}</td>
							<td width="50px">${item.batchNumber}</td>
							<td width="100px">${item.phoneNumber}</td>
							<td width="80px">${item.mobileNumber}</td>
							<td width="80px">${item.univ}</td>
							<td width="100px">
								<a href="staff?update&staffBean.staffId=${item.staffId}">Edit</a>
								&nbsp;|&nbsp;
								<a href="staff?doDelete&staffBean.staffId=${item.staffId}">Delete</a>
							</td>
							
						</tr>
						<c:set var="i" value="${i+1}"></c:set>
					</c:forEach>
				</c:if>

				<c:if test="${actionBean.totalRow==0}">
					<tr>
						<td colspan="5">No staff</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		<div class="pagination">
			<c:if test="${actionBean.totalPage>1}">
				<c:if test="${actionBean.page!=1}">
					<a href="staff">&lsaquo; First</a>
					<c:if test="${actionBean.page==2}">
						<a href="staff">&lsaquo; Prev</a>
					</c:if>
					<c:if test="${actionBean.page>2}">
						<a href="staff?goTo&page=${actionBean.page-1}"><span>&lsaquo;
						Prev</span></a>
					</c:if>
				</c:if>
	
				<c:if test="${actionBean.page==1}">
					<strong>1</strong>
				</c:if>
				<c:if test="${actionBean.page!=1}">
					<a href="staff">1</a>
				</c:if>
	
				<c:forEach var="i" begin="2" end="${actionBean.totalPage}">
					<c:if test="${actionBean.page==i}">
						<strong>${i}</strong>
					</c:if>
					<c:if test="${actionBean.page!=i}">
						<a href="staff?goTo&page=${i}">${i}</a>
					</c:if>
				</c:forEach>
	
				<c:if test="${actionBean.page!=actionBean.totalPage}">
					<a href="staff?goTo&page=${actionBean.page+1}">Next &rsaquo;</a>
					<a href="staff?goTo&page=${actionBean.totalPage}">Last &rsaquo;</a>
				</c:if>
			</c:if>
			<div class="clearer"></div>
		</div>
		
		
	</stripes:layout-component>
</stripes:layout-render>