<form method="get" action="${pageContext.request.contextPath}/listinfractions.jsp">
<fieldset>
			<legend>Search Infractions</legend>
			<table width="32%">					
				<tr>
					<th style="text-align:left;">Containing Words:</th>					
				</tr>
				<tr id="searchbydescription">
					 <td colspan="4">			
						<input type="text" id="searchdescription" name="description" maxlength="25" length="5" value="${param.description}"/>
						<script type="text/javascript">document.getElementById('searchdescription').focus();</script>
						<b>
							<input type="radio" name="sgroup" value="any" <c:if test = "${param.sgroup == 'any'}"> checked </c:if>>Any
							<input type="radio" name="sgroup" value="all"<c:if test = "${param.sgroup == 'all'}"> checked</c:if>>All
						<input type="radio" name="sgroup" value="exact" <c:if test = "${param.sgroup == 'exact'}"> checked </c:if>>Exactly
						</b>
					</td>
				</tr>			
										
				<tr>
					<td>
						<input name="submit" type="submit" value="Go Fetch" />
							<c:if test = "${not empty providesearchcriteria}" >
								<br /><span style="text-align:left" class="warning">${providesearchcriteria}</span>
							</c:if>
					</td>					
				</tr>	
			</table>
		</fieldset>
</form>