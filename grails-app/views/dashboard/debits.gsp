<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />       
        <title>Debits</title>
        <script>        	
 		</script>
    </head>
    <body>
		<div class="body">
	        <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
	         </g:if>  
	       <h1>Debits from ${startDate} to ${endDate}</h1>	
                        
			<div class="debitsTable">
					<table>
	                    <thead>
	                    	<tr>
	                        	<th>Category</th>
	                        	<th>Amount</th>
	                        	<th>Budget</th>
	                        	<th>Difference</th>
	                        </tr>	                    
	                    </thead>
	                    <tbody>
	             		<g:each in="${debits}" var="item">
			                    <tr>                    	
			                    	<td><%= item[0] %></td>
			                    	<td><g:link action="budgetDebitsByDate" params="[month:"${monthIdx}", budget:"${item[3]}"]" ><g:dollar amount='${item[1]}'/></g:link></td>
			                    	<td><g:dollar amount='${item[2]}'/></td>
			                    	<td><g:dollarDiff value1="${item[1]}" value2="${item[2]}"/></td>
			                    </tr>   
	                    </g:each>         
	                    </tbody>
	                </table>
				</div>
			
        </div>
    </body>
</html>
