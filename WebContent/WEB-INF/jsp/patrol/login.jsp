<%@ include file="/jspheader.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head> 
 <title>
 Login
 </title>
</head>

<body>
	<div>
	 <c:url var="imageURL" value="/images/fearlessfife.jpg"/>
	 <img align="middle" src="${imageURL}" alt="Login" />
	</div>

	<div>
		<p>
 <form method="post" action='<%= response.encodeURL("j_security_check") %>' class="user-input" name='giveMeFocus'>
 <table>
	 <tr>
	  <td><label>Name</label> *</td>
	  <td><input type="text" name="j_username"></td>
	 </tr>
	 <tr>
	  <td><label>Password</label></td>
	  <%-- 'autocomplete' is a non-HTML attribute, supported by some browsers. Prevents prepopulation of passwords.--%>
	  <td><input type="password" name="j_password" autocomplete="false"></td>
	 </tr>
	 <tr>
	  <td colspan="2"><input type="submit" value="Login"></td>
	 </tr>
 </table>
 </form>
</div>


	<%@ include file="/footer.jsp" %>
</body>
</html>
