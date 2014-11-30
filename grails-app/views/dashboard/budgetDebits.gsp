<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />       
        <title>Debits</title>
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
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
	                        	<th>Category</th>
	                        	<th>Amount</th>
	                        	<th>Budget</th>
	                        	<th>Difference</th>
	                        </tr>	                    
	                    </thead>
	                    <tbody>
	             		<tr>                    	
	                    	<td>${budget.name}</td>
	                    	<td><g:dollar amount='${debitTotal}'/></td>
	                    	<td><g:dollar amount='${budget.pennies}'/></td>
	                    	<td><g:dollarDiff value1="${debitTotal}" value2="${budget.pennies}"/></td>
	                    </tr>   
	                    </tbody>
	                </table>
				</div>
            
           <%
			    def descColumns = [['string', 'Description'], ['number', 'Amount']]
				def catColumns = [['string', 'Category'], ['number', 'Amount']]
				def descData = debitByDescription
			 	def catData = debitByCategory
		   %>
	       <gvisualization:pieCoreChart elementId="debitByCategoryPieChart" is3D="${true}" columns="${catColumns}" data="${catData}"/>
	       <div id="debitByCategoryPieChart"></div>
	       <gvisualization:pieCoreChart elementId="debitByDescPieChart" is3D="${true}" columns="${descColumns}" data="${descData}"/>
	       <div id="debitByDescPieChart"></div>
	               
			<div class="debitsTable">
					<table>
	                    <thead>
	                    	<tr>
	                    		<th>Date</th>
	                        	<th>Description</th>
	                        	<th>Category</th>
	                        	<th>Amount</th>
	                        </tr>	                    
	                    </thead>
	                    <tbody>
	             		<g:each in="${debits}" var="item">
			                    <tr>                    	
			                    	<td><g:formatDate format='MM/dd/yyyy' date='${item.transactionDate}'/></td>
			                    	<td><%= item.description %></td>
			                    	<td><%= item.type.name %></td>
			                    	<td><g:dollar amount='${item.pennies}'/></td>
			                    </tr>   
	                    </g:each>         
	                    </tbody>
	                </table>
				</div>
			
        </div>
    </body>
</html>
