<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />       
        <title>Account Dashboard</title>
        <script>        	
 		</script>
    </head>
    <body>
		<div class="body">
	        <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
	         </g:if>  
	       <h1>Details</h1>
                        
            
			<div class="monthTable">
					<table>
	                    <thead>
	                    	<tr>
	                        	<th>Month</th>
	                        	<th>Account</th>
	                        	<th>Credits</th>
	                        	<th>Debits</th>
	                        	<th>Balance</th>
	                        </tr>	                    
	                    </thead>
	                    <tbody>
	             		<g:each in="${balanceByMonth}" var="item">
			                    <tr>                    	
			                    	<td><%= item.value.month %></td>
			                    	<td><%= item.value.account %></td>
			                    	<td><%= item.value.credits %></td>
			                    	<td><%= item.value.debits %></td>
			                    	<td><%= item.value.balance %></td>
			                    </tr>   
	                    </g:each>         
	                    </tbody>
	                </table>
				</div>
				
			<div class="budgetTable">
					<table>
	                    <thead>
	                    	<tr>
	                        	<th>Budget</th>
	                        	<th>Amount</th>
	                        	<th>Current</th>
	                        </tr>	                    
	                    </thead>
	                    <tbody>
	             		<g:each in="${balanceByBudget}" var="item">
			                    <tr>                    	
			                    	<td><%= item.value.budget %></td>
			                    	<td><%= item.value.amount %></td>
			                    	<td><%= item.value.debits %></td>
			                    </tr>   
	                    </g:each>         
	                    </tbody>
	                </table>
				</div>
        </div>
    </body>
</html>
