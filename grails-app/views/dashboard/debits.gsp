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
	                        </tr>	                    
	                    </thead>
	                    <tbody>
	             		<g:each in="${debits}" var="item">
			                    <tr>                    	
			                    	<td><%= item.value.category %></td>
			                    	<td><%= item.value.amount %></td>
			                    </tr>   
	                    </g:each>         
	                    </tbody>
	                </table>
				</div>
			
        </div>
    </body>
</html>
