<!-- Add support for Spring Form tags -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Adds support for JSTL core tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>List Customers</title>
	
	<!-- reference our style sheet -->
	<!-- pageContext.request.contextPath gives the name of the app -->
	
	<link type="text/css" rel="stylesheet" 
		href="${pageContext.request.contextPath}/resources/css/style.css" />
	
	
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		
		<div id="content">
			
			<!-- Add Customer button -->
			
			<!-- 
			the onclick calls the Spring controller mapping 
			the class attribute is the CSS style
			-->
			<input type="button" value="Add Customer"			
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button"
			/>
			
			<!-- Add search box -->
			<form:form action="search" method="POST">
				Search customer: <input type="text" name="theSearchName" />
				
				<input type="submit" value="Search" class="search-button"/>
			</form:form>
			
			
			<!-- add our HTML table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>			
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${customers}">
					
					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<!-- param will be the value after the ? in the url -->
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<!-- param will be the value after the ? in the url -->
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					
					<tr>
						<td> ${tempCustomer.firstName} </td>
						<td> ${tempCustomer.lastName} </td>
						<td> ${tempCustomer.email} </td>
						
						<td>							
							
							<!-- update button -->
							<input type="button" value="Update"
							onclick="window.location.href='${updateLink}'; return false;"
							class="update-button" /> 
							
							<!-- delete button -->
							<!-- onclick JavaScript to prompt the user -->
							<input type="button" value="Delete"
							onclick="window.location.href='${deleteLink}'; if (!(confirm('Are you sure you want to delete this customer?'))) return false;"
							class="delete-button" />							
							
						</td>
						
					</tr>
					
				</c:forEach>
				
			</table>
		
		</div>
	
	
	</div>
	
</body>
</html>