
<footer>
  
			<center>
				<section><a href="mailto:mpd@karabelas.com"><span>Contact Me</span></a>|| 
					<c:if test="${empty loggedin}">	
							<a href="${pageContext.request.contextPath}/index.jsp"><span>Create an Account</span></a>|
					</c:if>
					<a href="${pageContext.request.contextPath}/index.jsp"><span>Site Map</span></a>		

					<c:if test ="${not empty loggedin}">				
				           |<a title="Sign Out Link" href="${pageContext.request.contextPath}/index.jsp?signout=true">Sign Out</a>
					</c:if>	
					</section>
				
					<script type="text/javascript" src="http://www.brainyquote.com/link/quotebr.js"></script>
						 			
			</center>
				
</footer>  