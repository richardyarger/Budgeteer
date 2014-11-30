
<%@ page import="com.rich.Transaction" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-transaction" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-transaction" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="description" title="${message(code: 'transaction.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="transactionDate" title="${message(code: 'transaction.transactionDate.label', default: 'Transaction Date')}" />
					
						<th><g:message code="transaction.account.label" default="Account" /></th>
					
						<g:sortableColumn property="checkNumber" title="${message(code: 'transaction.checkNumber.label', default: 'Check Number')}" />
					
						<th><g:message code="transaction.type.label" default="Type" /></th>
					
						<g:sortableColumn property="amount" title="${message(code: 'transaction.amount.label', default: 'Amount')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${transactionInstanceList}" status="i" var="transactionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${transactionInstance.id}">${fieldValue(bean: transactionInstance, field: "description")}</g:link></td>
					
						<td><g:formatDate date="${transactionInstance.transactionDate}" /></td>
					
						<td>${fieldValue(bean: transactionInstance, field: "account")}</td>
					
						<td>${fieldValue(bean: transactionInstance, field: "checkNumber")}</td>
					
						<td>${fieldValue(bean: transactionInstance, field: "type")}</td>
					
						<td>${fieldValue(bean: transactionInstance, field: "amount")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${transactionInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
