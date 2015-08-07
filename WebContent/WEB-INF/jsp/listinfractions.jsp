<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
 
<html>
	<head>
		<title>
		District of Columbia Vehicle Parking and Moving Infractions
		</title>
	</head>
	<body>
	  <div style="background-color:black;color:white;text-align:center;padding:5px;font-size:150%">
			Metropolitan Police Department
	  </div>
  	<%@ include file="/WEB-INF/jsp/searchInfractionsForm.jsp" %>
		<table border="1" width="100%">
			<caption>DC Vehicle Parking and Moving Infractions</caption>
			<tr>
			   <th>Code</th>
			   <th>Description</th>
				<th>Short Description</th>
			   <th>Fine</th>
			</tr>
			<%-- Page has been processed by TrafficParkingInfraction Servlet
				application level attribute placed upon servlet init method
				using the ParkingMovingInfractionDAOs
			--%>
			<c:forEach var="aninfraction" items="${lstinfractions}">	
			<tr>
			   
			   <td><c:out value="${aninfraction.code}"/></td>
			   <td><c:out value="${aninfraction.description}"/></td>
			   <td><c:out value="${aninfraction.shortDescription}"/></td>
			   <td><c:out value="${aninfraction.fine}"/></td>
			</tr>
			</c:forEach>
		</table>
 	<%@ include file="/footer.jsp" %>
	</body>
</html>