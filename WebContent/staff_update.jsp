<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">	
		<h4>Edit Data Staff</h4>
		<stripes:form id="add_staff" action="/action/staff" enctype="multipart/form-data" method="post">
						
			<div id="container">
			
						<div id="info_container">
							<div id="personal" class="info">
								<ul>
									<li>
										<stripes:label for="stf_id">Staff ID:</stripes:label>
										<stripes:text class="input_text" id="stf_id" name="staffBean.idStaff" value="${actionBean.staffBean.idStaff}"></stripes:text>
										
									</li>
									<li>
										<stripes:label for="name">Full name:</stripes:label>
										<stripes:text class="input_text" id="name" name="staffBean.staffName" value="${actionBean.staffBean.staffName}"/>
										
									</li>	
									<li>
										<c:if test="${actionBean.staffBean.photoFilename==null}">
										<img alt="" src="uploads/small/default.png">
											
										</c:if>
										<c:if test="${actionBean.staffBean.photoFilename!=null}">
											<img alt="" src="uploads/small/${actionBean.staffBean.photoFilename}">
										</c:if>
				
											Upload photo:
										<stripes:file name="photoFile" class="input_file" size="10px"/>
					
									</li>
									<li>
										<stripes:label for="birth">Birth Date(mm-dd-yyyy):</stripes:label>
										<input type="text" class="input_text" id="birth" name="staffBean.birthDate" value="${actionBean.staffBean.birthDateString}"/>
										
									</li>					
									<li>
										<stripes:label for="birth">Gender:</stripes:label>
										<c:if test="${actionBean.staffBean.gender=='0'}">
											<span class="radio">
												<input type="radio" id="Male" value="0" name="staffBean.gender" checked="checked"/>
												<stripes:label for="Male">Male</stripes:label>

											</span>
											<span class="radio">
												<input type="radio" id="Female" value="1" name="staffBean.gender"/>
												<stripes:label for="Female">Female</stripes:label>
											</span>
										</c:if>
										<c:if test="${actionBean.staffBean.gender=='1'}">
											<span class="radio">
												<input type="radio" id="Male" value="0" name="staffBean.gender"/>
												<stripes:label for="Male">Male</stripes:label>
											</span>
											<span class="radio">
												<input type="radio" id="Female" value="1" name="staffBean.gender" checked="checked"/>
												<stripes:label name="Female">Female</stripes:label>
											</span>
										</c:if>		
											<c:if test="${actionBean.staffBean.gender == null}">
											<span class="radio">
												<input type="radio" id="Male" value="0" name="staffBean.gender"/>
												<stripes:label for="Male">Male</stripes:label>

											</span>
											<span class="radio">
												<input type="radio" id="Female" value="1" name="staffBean.gender"/>
												<stripes:label for="Female">Female</stripes:label>
											</span>
										</c:if>											

										<div class="clearer"/>
									</li>
															
								</ul>
								<ul>
									<li>
										<stripes:label for="home_adrs">Home address:</stripes:label>
										<stripes:textarea class="input_text" id="home_adrs" name="staffBean.homeAddress" value="${actionBean.staffBean.homeAddress}"/>
										
									</li>
									<li>
										<stripes:label for="curr_adrs">Current Address:</stripes:label>
										<stripes:textarea class="input_text" id="curr_adrs" name="staffBean.currentAddress" value="${actionBean.staffBean.currentAddress}"/>
									</li>
									<li>
										<stripes:label for="phone">Phone number:</stripes:label>
										<stripes:text class="input_text" id="phone" name="staffBean.phoneNumber" value="${actionBean.staffBean.phoneNumber}"/>
										
									</li>
									<li>
										<stripes:label for="mobile">Mobile number:</stripes:label>
										<stripes:text class="input_text" id="mobile" name="staffBean.mobileNumber" value="${actionBean.staffBean.mobileNumber}"/>
										
									</li>
									<li>
										<stripes:label for="unv">University:</stripes:label>
										<stripes:select id="unv" name="staffBean.univId" value="${actionBean.staffBean.univId}">
											<c:choose>
												<c:when test="${actionBean.unvList!=null}">										
													<c:forEach items="${actionBean.unvList}" var="unvItem">		
														<c:choose>
															<c:when test="${unvItem.univId==actionBean.staffBean.univId}">
																<option value="${unvItem.univId}" selected>${unvItem.univName}</option>
															</c:when>
															<c:otherwise>
																<option value="${unvItem.univId}">${unvItem.univName}</option>
															</c:otherwise>
														</c:choose>										
													</c:forEach>																			
												</c:when>
												<c:otherwise>
													<stripes:option value="0">-- No University --</stripes:option>
												</c:otherwise>
											</c:choose>
										</stripes:select>
									</li>
								</ul>

								<ul>
									<li>
										<stripes:label for="join">Join Date(mm-dd-yyyy):</stripes:label>
										<input type="text" class="input_text" id="join" name="staffBean.joinDate" value="${actionBean.staffBean.joinDateString}"/>
									</li>
									<li>
										<stripes:label for="batch">Batch number:</stripes:label>
										<c:if test="${actionBean.staffBean.batchNumber!=0}">
											<stripes:text class="input_text" id="batch" name="staffBean.batchNumber" value="${actionBean.staffBean.batchNumber}"/>
										
										</c:if>
										<c:if test="${actionBean.staffBean.batchNumber==0}">
											<stripes:text class="input_text" id="batch" name="staffBean.batchNumber" value=""/>
										
										</c:if>
									</li>
								</ul>
								<ul>
									<li>
										<stripes:label for="sts">Status:</stripes:label>
										<stripes:select id="sts" name="staffBean.statusId" value="${actionBean.staffBean.statusId}">
											<c:choose>
												<c:when test="${actionBean.stsList!=null}">
													<c:forEach items="${actionBean.stsList}" var="stsItem">											
														<c:choose>
															<c:when test="${stsItem.statusId==actionBean.staffBean.statusId}">
																<option value="${stsItem.statusId}" selected>${stsItem.statusName}</option>
															</c:when>
															<c:otherwise>
																<option value="${stsItem.statusId}">${stsItem.statusName}</option>
															</c:otherwise>
														</c:choose>	
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
											<input type="text" class="input_text" id="join" name="staffBean.resignDate" value="${actionBean.staffBean.resignDateString}"/>
										
									</li>
									
									<li>
										<stripes:label for="pos">Position:</stripes:label>
										<stripes:select id="pos" name="staffBean.positionId" value="${actionBean.staffBean.positionId}">
											<c:choose>
												<c:when test="${actionBean.posList!=null}">
													<c:forEach items="${actionBean.posList}" var="posItem">											
														<c:choose>
															<c:when test="${posItem.positionId==actionBean.staffBean.positionId}">
																<option value="${posItem.positionId}" selected>${posItem.position}</option>
															</c:when>
															<c:otherwise>
																<option value="${posItem.positionId}">${posItem.position}</option>
															</c:otherwise>
														</c:choose>		
													</c:forEach>
												</c:when>
												<c:otherwise>
													<stripes:option value="0">-- No Position --</stripes:option>
												</c:otherwise>
											</c:choose>
										</stripes:select>
									</li>									 
									<li>
										<p>Skill</p>		
									
											<c:forEach items="${actionBean.listSkillStaff }" var="listSkillStaff">
													<input type="checkbox" name="listSkillChecked" value="${listSkillStaff.skillId}" checked></input>
													<stripes:label for="${listSkillStaff.skillId}">${listSkillStaff.skillName}</stripes:label><br/>
											</c:forEach>	
											<c:forEach items="${actionBean.listNotSkillStaff }" var="listNotSkillStaff">
												<input type="checkbox" name="listSkillChecked" value="${listNotSkillStaff.skillId}"/>
												<stripes:label for="${listNotSkillStaff.skillId}">${listNotSkillStaff.skillName}</stripes:label>
												<br/>
											</c:forEach>									
									</li>
								</ul>
							</div>
						</div>			
						<div id="controls">
							<stripes:hidden name="staffBean.staffId" value="${actionBean.staffBean.staffId}"/>
							<stripes:submit name="doUpdate" value="Update"/>
							<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"/>
						</div>	
					</div>
						
				</stripes:form>
			</stripes:layout-component>
		</stripes:layout-render>		