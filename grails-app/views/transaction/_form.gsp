<%@ page import="com.rich.Transaction" %>



<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="transaction.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" maxlength="50" required="" value="${transactionInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'transactionDate', 'error')} required">
	<label for="transactionDate">
		<g:message code="transaction.transactionDate.label" default="Transaction Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="transactionDate" precision="day"  value="${transactionInstance?.transactionDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'account', 'error')} required">
	<label for="account">
		<g:message code="transaction.account.label" default="Account" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="account" name="account.id" from="${com.rich.Account.list()}" optionKey="id" required="" value="${transactionInstance?.account?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'checkNumber', 'error')} ">
	<label for="checkNumber">
		<g:message code="transaction.checkNumber.label" default="Check Number" />
		
	</label>
	<g:field name="checkNumber" type="number" value="${transactionInstance.checkNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="transaction.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="type" name="type.id" from="${com.rich.Category.list()}" optionKey="id" required="" value="${transactionInstance?.type?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="transaction.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" value="${fieldValue(bean: transactionInstance, field: 'amount')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'isCredit', 'error')} ">
	<label for="isCredit">
		<g:message code="transaction.isCredit.label" default="Is Credit" />
		
	</label>
	<g:checkBox name="isCredit" value="${transactionInstance?.isCredit}" />
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'isFee', 'error')} ">
	<label for="isFee">
		<g:message code="transaction.isFee.label" default="Is Fee" />
		
	</label>
	<g:checkBox name="isFee" value="${transactionInstance?.isFee}" />
</div>

