<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<stripes:layout-render name="/layout.jsp">
	<stripes:layout-component name="content">
		<h4>New Data User</h4>
		<stripes:form id="add_user" action="/action/user" method="post">
			<stripes:errors globalErrorsOnly="true"></stripes:errors>
			<ul>
				
				<li>
					<stripes:label for="name">Username</stripes:label>
					<stripes:text class="input_text" id="name" name="name" value=""></stripes:text>
					<stripes:errors field="name"></stripes:errors>
				</li>
				<li>
					<stripes:label for="email">Email</stripes:label>
					<stripes:text class="input_text" id="email" name="email" value=""></stripes:text>
					<stripes:errors field="email"></stripes:errors>
				</li>
				<li>
					<stripes:label for="rule">Rule</stripes:label>
					<stripes:select id="rule" name="rule">
						<stripes:option value="admin" selected="">Administrator</stripes:option>
						<stripes:option value="user">User</stripes:option>
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
			<ul>
				<li>
					<stripes:submit name="doInsert" value="Submit"></stripes:submit>
					<stripes:submit name="cancelAction" value="Cancel" onclick="$.fancybox.close();return false;"></stripes:submit>
				</li>
			</ul>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>