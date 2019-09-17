<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		<h4>New Data Staff</h4>
		<stripes:form id="staff" action="/action/staff" enctype="multipart/form-data" method="post">
			<div class="clearer"></div>
			
			<div id="container">
								
				<div id="info_container">
					<div id="personal" class="info">
						<ul>
							<li>
								<stripes:label for="stf_id">Staff ID:</stripes:label>
								<stripes:text class="input_text" id="stf_id" name="staffBean.idStaff"></stripes:text>
								<stripes:errors field="staffBean.idStaff"></stripes:errors>
							</li>
							<li>
								<stripes:label for="name">Full name:</stripes:label>
								<stripes:text class="input_text" id="name" name="staffBean.staffName"></stripes:text>
								<stripes:errors field="staffBean.staffName"></stripes:errors>
							</li>
							<li>
								<stripes:label for="photo">Upload photo:</stripes:label>
								<stripes:file id="photo" name="photoFile" class="input_file" size="10px"/>
							</li>
							<li>
								<stripes:label for="birth">Birth Date(mm-dd-yyyy):</stripes:label>
								<stripes:text class="input_text" id="birth" name="staffBean.birthDate"></stripes:text>
								
							</li>
							<li>
								<stripes:label for="birth">Gender:</stripes:label>
								<span class="radio">
									<stripes:radio id="gd_m" value="0" name="staffBean.gender"></stripes:radio>
									<stripes:label for="gd_m">Male</stripes:label>
								</span>
								<span class="radio">
									<stripes:radio id="gd_f" value="1" name="staffBean.gender"></stripes:radio>
									<stripes:label for="gd_f">Female</stripes:label>
								</span>
								<div class="clearer"></div>
								<stripes:errors field="staffBean.gender"></stripes:errors>
							</li>
						</ul>
						<ul>
							<li>
								<stripes:label for="home_adrs">Home address:</stripes:label>
								<stripes:textarea class="input_text" id="home_adrs" name="staffBean.homeAddress"></stripes:textarea>
								<stripes:errors field="staffBean.homeAddress"></stripes:errors>
							</li>
							<li>
								<stripes:label for="curr_adrs">Current Address:</stripes:label>
								<stripes:textarea class="input_text" id="curr_adrs" name="staffBean.currentAddress"></stripes:textarea>
								<stripes:errors field="staffBean.currentAddress"></stripes:errors>
							</li>
							<li>
								<stripes:label for="phone">Phone number:</stripes:label>
								<stripes:text class="input_text" id="phone" name="staffBean.phoneNumber"></stripes:text>
								<stripes:errors field="staffBean.phoneNumber"></stripes:errors>
							</li>
							<li>
								<stripes:label for="mobile">Mobile number:</stripes:label>
								<stripes:text class="input_text" id="mobile" name="staffBean.mobileNumber"></stripes:text>
								<stripes:errors field="staffBean.mobileNumber"></stripes:errors>
							</li>
							<li>
								<stripes:label for="unv">University:</stripes:label>
								<stripes:select id="unv" name="staffBean.univId">
									<c:choose>
										<c:when test="${actionBean.unvList!=null}">
											<c:forEach items="${actionBean.unvList}" var="unvItem">
												<stripes:option value="${unvItem.univId}">${unvItem.univName}</stripes:option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<stripes:option value="0">-- No University --</stripes:option>
										</c:otherwise>
									</c:choose>
								</stripes:select>
							</li>
						</ul>
						<div class="clearer"></div>
					</div>
					<div id="additional" class="info">
						<ul>
							<li>
								<stripes:label for="join">Join Date(mm-dd-yyyy):</stripes:label>
								<stripes:text class="input_text" id="join" name="staffBean.joinDate"></stripes:text>
							
							</li>
							<li>
								<stripes:label for="batch">Batch number:</stripes:label>
								<stripes:text class="input_text" id="batch" name="staffBean.batchNumber"></stripes:text>
								
							</li>
						</ul>
						<ul>
							<li>
								<stripes:label for="sts">Status:</stripes:label>
								<stripes:select id="sts" name="staffBean.statusId">
									<c:choose>
										<c:when test="${actionBean.stsList!=null}">
											<c:forEach items="${actionBean.stsList}" var="stsItem">
												<stripes:option value="${stsItem.statusId}">${stsItem.statusName}</stripes:option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<stripes:option value="0">-- No Status --</stripes:option>
										</c:otherwise>
									</c:choose>
								</stripes:select>
							</li>
							<li>
								<stripes:label for="join">Resign Date(mm-dd-yyyy):</stripes:label>
								<stripes:text class="input_text" id="join" name="staffBean.resignDate"></stripes:text>
								<stripes:errors field="staffBean.resignDate"></stripes:errors>
							</li>
							<li>
								<stripes:label for="pos">Position:</stripes:label>
								<stripes:select id="pos" name="staffBean.positionId">
									<c:choose>
										<c:when test="${actionBean.posList!=null}">
											<c:forEach items="${actionBean.posList}" var="posItem">
												<stripes:option value="${posItem.positionId}">${posItem.position}</stripes:option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<stripes:option value="0">-- No Position --</stripes:option>
										</c:otherwise>
									</c:choose>
								</stripes:select>
							</li>
							
						</ul>
						<ul>
						<li>
						<stripes:label for="pos">Skill:</stripes:label><br/>
							<c:choose>
								<c:when test="${actionBean.sklList!=null}">
									<c:forEach items="${actionBean.sklList}" var="sklItem" varStatus="sklLoop">
										<span class="radio">
											<stripes:checkbox id="skl_${sklItem.skillId}" name="listSkillChecked" value="${sklItem.skillId}"></stripes:checkbox>					
											<stripes:label for="skl_${sklItem.skillId}">${sklItem.skillName}</stripes:label>
										</span>
										<div class="clearer"></div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									List Skill Null
								</c:otherwise>
							</c:choose>
						</li>
						</ul>
						<div class="clearer"></div>
					</div>
				</div>
				<div class="clearer"></div>
			</div>		
			
			<div id="top_menu">
				<stripes:submit name="doInsert" value="Submit"></stripes:submit>
				<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
			</div>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>