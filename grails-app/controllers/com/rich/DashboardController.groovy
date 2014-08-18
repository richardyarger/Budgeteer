package com.rich

class DashboardController {

	AccountService accountService
	int monthStartDay = 20
	String BUDGET_DATE_FORMAT = 'MM/dd'
	
    def index() {
		Account account = Account.findById(1)
		def balanceByMonth = [:]
		
		(0..2).each{ idx ->
			Calendar today = Calendar.getInstance()
			today.clearTime()
			today.add(Calendar.MONTH, -idx)
			today.set(Calendar.DAY_OF_MONTH, 20)
			Date startDate = today.time
			startDate.clearTime()
			def month = startDate.format("MMM yyyy")
			today.add(Calendar.MONTH, -1)
			startDate = today.time
			
			today.add(Calendar.MONTH, 1)
			today.add(Calendar.MILLISECOND, -1)
			Date endDate = today.time
			println "$startDate to $endDate"
			
			def credits = accountService.creditsByDate(startDate, endDate, account) ?: 0
			def debits = accountService.debitsByDate(startDate, endDate, account) ?: 0
			def balance = account.startingBalance + credits - debits
			
			balanceByMonth.put month, [month:month,account:account.name,credits:credits,debits:debits,balance:balance]
		}
		
		def balanceByBudget = [:]
		Calendar today = Calendar.getInstance()
		today.clearTime()
		today.add(Calendar.MONTH, -1)
		today.set(Calendar.DAY_OF_MONTH, 20)
		Date startDate = today.time
		startDate.clearTime()
		println startDate
		println BUDGET_DATE_FORMAT
		def budgetStartDate = startDate.format(BUDGET_DATE_FORMAT)
		
		today.add(Calendar.MONTH, 1)
		today.add(Calendar.MILLISECOND, -1)
		Date endDate = today.time
		def budgetEndDate = endDate.format(BUDGET_DATE_FORMAT)
		
		Budget[] budgets = Budget.list()
		budgets.each{ budget ->
			def debits = accountService.debitsByDate(startDate, endDate, account, null, budget) ?: 0
			balanceByBudget.put(budget.name, [budget:budget.name,amount:budget.amount,debits:debits])
		}
		render(view:'show', model:[balanceByMonth:balanceByMonth,balanceByBudget:balanceByBudget,
			budgetStart:budgetStartDate,budgetEnd:budgetEndDate])
	}
	
	
	def debits() {
		def startDate = params.startDate
		def endDate = params.endDate
		println "Debits from $startDate to $endDate"
		
		def debits = accountService.debitsByDate(startDate, endDate) ?: 0
		
		render(view:'debits', model:[debits:debits, startDate:startDate, endDate:endDate])
	}
}
