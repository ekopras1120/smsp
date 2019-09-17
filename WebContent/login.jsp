<%@taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Resources Management System</title>		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		
		<style>
			#main_login{
				position:fixed;
				top:310px; 
				left:860px;
			}
			#login_text{
				color:#c0d8db;
			}
			body{	
				background-color:#a2b9bc;	
			}
		</style>
	</head>
	<body>
		<div id="menubar">&nbsp;</div>
		<div id="wrapper" >
			<div id="main_login">
				<div class="ajax">
					<h3 id="login_text">Login</h3>
					<stripes:form action="/action/user" method="post">
						<div class="form-group">
							<label for="exampleInputEmail1">Username</label>
							<input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Username" name="loginName"/>
							<small id="emailHelp" class="form-text text-muted">Please fill with your username or your email.</small>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Password</label>
							<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="loginPwd">					
						</div>		
						<stripes:submit name="doLogin" value="Login" class="btn btn-info"></stripes:submit>		
						
								
					</stripes:form>
					
					
				</div>
			</div>		
		</div>
	</body>
</html>