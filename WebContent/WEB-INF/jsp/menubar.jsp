
	<c:if test="${not empty param.signout}">
		<c:remove var="loggedin" scope="session" />	
		<c:remove var="user" scope="session" />	
	</c:if>

      <div class="tabs">
        <ul>   
			<!-- there is a CSS class that should be assigned class="active" to one of the items <li class="active">.
			 We also want to provide HOME Link 
			-->				
	<%-- 	<c:out value="${pageContext.request.requestURI}" />      --%>
			<section>
			 	<a title="DC Traffic Infractions" href="${pageContext.request.contextPath}/listinfractions.jsp">DC Traffic Infractions</a>
			</section>	
	</div>	
	


