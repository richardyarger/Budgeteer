package com.rich

class DashboardController {

	AccountService accountService
	
    def index() {
		Account account = Account.findById(1)
		def balanceByMonth = [:]
		
		(0..2).each{ idx ->
			Calendar today = Calendar.getInstance()
			today.clearTime()
			today.add(Calendar.MONTH, -idx)
			today.set(Calendar.DAY_OF_MONTH, 1)
			Date startDate = today.time
			startDate.clearTime()
			
			today.add(Calendar.MONTH, 1)
			today.add(Calendar.MILLISECOND, -1)
			Date endDate = today.time
			println "$startDate to $endDate"
			
			def credits = accountService.creditsByDate(startDate, endDate, account) ?: 0
			def debits = accountService.debitsByDate(startDate, endDate, account) ?: 0
			def balance = account.startingBalance + credits - debits
			def month = startDate.format("MMM yyyy")
			balanceByMonth.put month, [month:month,account:account.name,credits:credits,debits:debits,balance:balance]
		}
		
		def balanceByBudget = [:]
		Calendar today = Calendar.getInstance()
		today.clearTime()
		today.add(Calendar.MONTH, -0)
		today.set(Calendar.DAY_OF_MONTH, 1)
		Date startDate = today.time
		startDate.clearTime()
		
		today.add(Calendar.MONTH, 1)
		today.add(Calendar.MILLISECOND, -1)
		Date endDate = today.time
		Budget[] budgets = Budget.list()
		budgets.each{ budget ->
			def debits = accountService.debitsByDate(startDate, endDate, account, null, budget) ?: 0
			balanceByBudget.put(budget.name, [budget:budget.name,amount:budget.amount,debits:debits])
		}
		render(view:'show', model:[balanceByMonth:balanceByMonth,balanceByBudget:balanceByBudget])
	}
	
}
