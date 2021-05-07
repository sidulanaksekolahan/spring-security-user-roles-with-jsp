<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<title>Irfan Company Home Page</title>
</head>
<body>

	<h2>Irfan Company Home Page</h2>
	<hr>
	
	<p>Welcome to the Home Page</p>
	
	<hr>
	
	<!-- Display user name and role -->
	<p>
		User: <security:authentication property="principal.username"/>
		<br><br>
		<!-- By default, spring security uses "ROLE_" prefix and this is configurable -->
		Role(s): <security:authentication property="principal.authorities"/>
	</p>
	
	<!-- Only show this to MANAGER role -->
	<security:authorize access="hasRole('MANAGER')"> 
		<!-- Add a link to point to /leaders, this is for the managers -->
		<p>
			<a href="${ pageContext.request.contextPath }/leaders">Leadership Meeting</a>
			(Only for Manager peeps)
		</p>
	</security:authorize>
	
	<!-- Only show this to ADMIN role -->
	<security:authorize access="hasRole('ADMIN')">
		<!-- Add a link to point to /systems, this is for the admins -->
		<p>
			<a href="${ pageContext.request.contextPath }/systems">IT Systems Meeting</a>
			(Only for Admin peeps)
		</p>
	</security:authorize>
	
	<hr>
	
	<!-- Add a logout button -->
	<form:form
		action="${ pageContext.request.contextPath }/perform_logout"
		method="POST">
		
		<input type="submit" value="Logout" />
		
	</form:form>

</body>
</html>