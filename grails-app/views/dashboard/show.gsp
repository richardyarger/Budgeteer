<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />       
        <title>Account Dashboard</title>
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    </head>
    <body>
    	<div class="body">
	        <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
	         </g:if>  
	       
	       <%
			    def columns = [['string', 'Month'], ['number', 'Credits'], ['number', 'Debits']]
				def data = balanceByMonth.values().collect{[it["monthStr"],(it["credits"]/100), (it["debits"]/100)] }
		   %>
	       <gvisualization:lineCoreChart elementId="monthlyLineChart" columns="${columns}" data="${data}"/>
	       <div id="monthlyLineChart"></div>
	         
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
			                    	<td><%= item.value.monthStr %></td>
			                    	<td><%= item.value.account %></td>
			                    	<td><g:dollar amount="${item.value.credits}"/></td>
			                    	<td><g:link action="debitsByDateBudget" id="${item.value.monthIdx}"><g:dollar amount="${item.value.debits}"/></g:link></td>
			                    	<td><g:dollarDiff value1="${item.value.debits}" value2="${item.value.credits}"/></td>
			                    </tr>   
	                    </g:each>         
	                    </tbody>
	                </table>
				</div>
			<h1>Budget from ${budgetStart} to ${budgetEnd}</h1>	
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
			                    	<td><g:dollar amount='${item.value.amount}'/></td>
			                    	<td><g:dollar amount='${item.value.actual}'/></td>
			                    </tr>   
	                    </g:each>         
	                    </tbody>
	                </table>
				</div>
        </div>
    </body>
</html>
