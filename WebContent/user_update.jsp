<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		<h4>Edit Data User</h4>
		<stripes:form id="edit_user" action="/action/user" method="post">
			<ul>
			
				<li>
					<stripes:label for="username">Username</stripes:label>
					<stripes:text class="input_text" id="username" name="name" value="${actionBean.userBean.userName}"></stripes:text>
					<stripes:errors field="name"></stripes:errors>
				</li>
				<li>
					<stripes:label for="email">Email</stripes:label>
					<stripes:text class="input_text" id="email" name="email" value="${actionBean.userBean.email}"></stripes:text>
					<stripes:errors field="email"></stripes:errors>
				</li>
				<li>
					<c:set var="userRule" value="${actionBean.userBean.rule}"></c:set>
					<stripes:label for="rule">Rule (${userRule})</stripes:label>
					<stripes:select id="rule" name="rule">
						<c:choose>
							<c:when test="${userRule == 'admin'}">
								<stripes:option value="${userRule}" selected="">Administrator</stripes:option>
								<stripes:option value="user">User</stripes:option>
							</c:when>
							<c:otherwise>
								<stripes:option value="admin">Administrator</stripes:option>
								<stripes:option value="${userRule}" selected="">User</stripes:option>
							</c:otherwise>
						</c:choose>
						<c:if test="${userRule == 'user'}">
						<stripes:option value="admin">Administrator</stripes:option>
						<stripes:option value="${userRule}" selected="">User</stripes:option>
						</c:if>
					</stripes:select>
				</li>
			
				<li>
					<stripes:label for="password">New password</stripes:label>
					<stripes:password class="input_password" id="password" name="password" value=""></stripes:password>
					<stripes:errors field="password"></stripes:errors>
				</li>
				<li>
					<stripes:label for="password2">Confirm new password</stripes:label>
					<stripes:password class="input_password" id="password2" name="password2" value=""></stripes:password>
					<stripes:errors field="password2"></stripes:errors>
				</li>
			</ul>
			<div class="clearer"></div>
		
			<stripes:hidden name="uid" value="${actionBean.userBean.userId}"></stripes:hidden>
			<stripes:submit name="doUpdate" value="Update"></stripes:submit>
			<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>		
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>