<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Resources Management System</title>	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
		
		<style>
			body {
			    background-color: #a2b9bc;
			}
		</style>
	</head>
	<body>		

		<div class="row">
			<div class="col-2">
				<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
					 <a class="nav-link" href="staff">Staff</a> 
					 <a class="nav-link" href="position">Position</a> 
					 <a class="nav-link" href="project">Project</a> 
					 <a class="nav-link" href="skill">Skill</a> 
					 <a class="nav-link" href="university">University</a> 
					 <a class="nav-link" href="status">Status</a> 
					 <a class="nav-link" href="user">User</a> 
					 <a class="nav-link" href="apibca">API BCA</a> 
					 <a class="nav-link" href="user?doLogout">Logout</a> 
				</div>
			</div>
			<div class="col-9">
				<stripes:layout-component name="content"/> 
			</div>
		</div>
		<script>
		function myFunction() {
		    var element = document.getElementById("eko");
		    element.classList.add("active");
		}
		</script>
	</body>
</html>