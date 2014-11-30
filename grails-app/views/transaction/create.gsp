<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />       
        <title>Create Transaction</title>
        <script>        	
 		</script>
    </head>
    <body>
		<div id="create-transaction" class="content create" role="main">
		<g:if test="${flash.message}">
           <div class="message">${flash.message}</div>
        </g:if>  
	    <g:hasErrors bean="${transactionInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${transactionInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</g:hasErrors> 
	    <h1>Create Transaction</h1>
		
		<form action="/Budgeteer/transaction/save" method="post" >
			<fieldset class="form">
				
				<div class="fieldcontain  required">
					<label for="description">
						Description
						<span class="required-indicator">*</span>
					</label>
					<g:field type="text" name="description" required="true" maxlength="50" autofocus="true"/> 
				</div>
				
				<div class="fieldcontain  required">
					<label for="transactionDate">
						Transaction Date
						<span class="required-indicator">*</span>
					</label>
					<%--<g:field type="date" name="transactiondate" required="true" value="${new Date()}"/>--%> 
					<g:datePicker name="transactionDate" value="${new Date()}" precision="day"/>
				</div>
				
				
				<div class="fieldcontain  required">
					<label for="account">
						Account
						<span class="required-indicator">*</span>
					</label>
					<g:select name="account"
					          from="${com.rich.Account.list()}"
					          optionKey="id"
					          optionValue="name" /> 
				</div>
				
				<div class="fieldcontain ">
					<label for="checkNumber">
						Check Number
					</label>
					<g:field type="number" name="checkNumber"/> 
				</div>
				
				<div class="fieldcontain  required">
					<label for="type">
						Type
						<span class="required-indicator">*</span>
					</label>
					<g:select name="type"
					          from="${com.rich.Category.list()}"
					          optionKey="id"
					          optionValue="name" /> 
				</div>
				
				<div class="fieldcontain  required">
					<label for="amount">
						Amount
						<span class="required-indicator">*</span>
					</label>
					<g:field type="null" name="amount" value="0" required="true"/> 
				</div>
				
				<div class="fieldcontain ">
					<label for="isCredit">
						Is Credit
					</label>
					<g:field type="checkbox" name="isCredit"/> 
				</div>
				
				<div class="fieldcontain ">
					<label for="isFee">
						Is Fee
					</label>
					<g:field type="checkbox" name="isFee"/> 
				</div>
				
				
			</fieldset>
			<fieldset class="buttons">
				<input type="submit" name="create" class="save" value="Create" id="create" />
			</fieldset>
		</form>  
		</div> 
  	</body>
</html>