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
	       <h1>${budget.name} Debits from ${startDate} to ${endDate}</h1>	
                        
			<div class="debitsTable">
					<table>
	                    <thead>
	                    	<tr>
	                    		<th>Date</th>
	                        	<th>Description</th>
	                        	<th>Category></th>
	                        	<th>Amount</th>
	                        </tr>	                    
	                    </thead>
	                    <tbody>
	             		<g:each in="${debits}" var="item">
			                    <tr>                    	
			                    	<td><g:dateFormat format='MM/dd/yyyy' date='${item.transactionDate}'/></td>
			                    	<td><%= item.description %></td>
			                    	<td><%= item.type.name %></td>
			                    	<td><%= item.amount %></td>
			                    </tr>   
	                    </g:each>         
	                    </tbody>
	                </table>
				</div>
			
        </div>
    </body>
</html>
