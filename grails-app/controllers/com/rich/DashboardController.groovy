package com.rich

import java.util.Date;

class DashboardController {

	AccountService accountService
	static final int MONTH_START_DAY = 20
	String BUDGET_DATE_FORMAT = 'MM/dd'
	
    def index() {
		Account account = Account.findById(1)
		def balanceByMonth = [:]
		
		(5..0).each{ idx ->
			Calendar today = Calendar.getInstance()
			
			/* Always show budget from 20th to the 20th */
			if(today.get(Calendar.DAY_OF_MONTH) > (MONTH_START_DAY-1)){
				today.add(Calendar.MONTH, 1)
			}
			
			today.clearTime()
			today.add(Calendar.MONTH, -idx)
			today.set(Calendar.DAY_OF_MONTH, MONTH_START_DAY)
			Date startDate = today.time
			startDate.clearTime()
			def monthStr = startDate.format("MMM yyyy")
			def monthIdx = today.get(Calendar.MONTH)
			today.add(Calendar.MONTH, -1)
			startDate = today.time
			
			today.add(Calendar.MONTH, 1)
			today.add(Calendar.MILLISECOND, -1)
			Date endDate = today.time
			println "$startDate to $endDate"
			
			def credits = accountService.creditsByDate(startDate, endDate, account) ?: 0
			def debits = accountService.debitsByDate(startDate, endDate, account) ?: 0
			def balance = account.startingBalance + credits/100 - debits/100
			
			balanceByMonth.put monthStr, [monthStr:monthStr,monthIdx:monthIdx,account:account.name,credits:credits,debits:debits,balance:balance]
		}
		
		println "balanceByMonth"
		println balanceByMonth
		println "values "+balanceByMonth.values()
		println balanceByMonth.values().collect{[it["monthStr"],it["debits"]] }
		
		def balanceByBudget = [:]
		Calendar today = Calendar.getInstance()
		
		/* Always show budget from 20th to the 20th */
		if(today.get(Calendar.DAY_OF_MONTH) > (MONTH_START_DAY-1)){
			today.add(Calendar.MONTH, 1)
		}
		
		today.clearTime()
		today.add(Calendar.MONTH, -1)
		today.set(Calendar.DAY_OF_MONTH, MONTH_START_DAY)
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
			def actual = accountService.transactionQuery(startDate, endDate, null, null, account, null, budget) ?: 0
			balanceByBudget.put(budget.name, [budget:budget.name,amount:budget.pennies,actual:actual])
		}
		render(view:'show', model:[balanceByMonth:balanceByMonth,balanceByBudget:balanceByBudget,
			budgetStart:budgetStartDate,budgetEnd:budgetEndDate])
	}
	
	
	def debitsByDateBudget() {
		def monthIdx = Integer.valueOf(params.id)
		Calendar debitDay = Calendar.getInstance()
		debitDay.clearTime()
		debitDay.set(Calendar.MONTH, monthIdx)
		debitDay.add(Calendar.MONTH, -1)
		debitDay.set(Calendar.DAY_OF_MONTH, MONTH_START_DAY)
		def startDate = debitDay.time
		def startDateStr = startDate.format(BUDGET_DATE_FORMAT)
		
		debitDay.add(Calendar.MONTH, 1)
		debitDay.add(Calendar.MILLISECOND, -1)
		def endDate = debitDay.time
		def endDateStr = endDate.format(BUDGET_DATE_FORMAT)
		println "Debits from $startDate to $endDate"
		
		//returns array of [budget name, actual, budget amount, budget id] for each budget
		def debitsByBudget = accountService.debitsByBudget(startDate, endDate) ?: 0
		println "debitsByBudget = $debitsByBudget"
		
		render(view:'debits', model:[monthIdx:monthIdx, debits:debitsByBudget, startDate:startDateStr, endDate:endDateStr])
	}
	
	def budgetDebitsByDate() {
		println params
		def monthIdx = Integer.valueOf(params.month)
		
		Budget budget
		if(params.budget){
			def budgetId = Long.valueOf(params.budget)
			budget = Budget.get(budgetId)
		}else if(params.budgetName){
				budget = Budget.findByName(params.budgetName)
		}else{
			flash.error = "No valid budget information was provided"
			redirect(view:'budgetDebits')
		}
		
		Calendar debitDay = Calendar.getInstance()
		debitDay.clearTime()
		debitDay.set(Calendar.MONTH, monthIdx)
		debitDay.add(Calendar.MONTH, -1)
		debitDay.set(Calendar.DAY_OF_MONTH, MONTH_START_DAY)
		def startDate = debitDay.time
		def startDateStr = startDate.format(BUDGET_DATE_FORMAT)
		
		debitDay.add(Calendar.MONTH, 1)
		debitDay.add(Calendar.MILLISECOND, -1)
		def endDate = debitDay.time
		def endDateStr = endDate.format(BUDGET_DATE_FORMAT)
		println "Debits from $startDate to $endDate"
		
		def budgetDebits = accountService.budgetDebits(budget.id, startDate, endDate) ?: 0
		println "budgetDebits = $budgetDebits"
		
		def debitTotal = budgetDebits.sum{ it.pennies }
		println "debitTotal = $debitTotal"
		
		def debitByCategory = []
		def categories = budgetDebits.collect{ it.type } as Set
		categories.each { category ->
			def myDebits = budgetDebits.findAll{ it.type == category }
			def sum = myDebits.sum{ it.pennies }
			debitByCategory << [category, sum/100]
		}
		println "debitByCategory = $debitByCategory"
		
		def debitByDescription = []
		def descriptions = budgetDebits.collect{ it.description } as Set
		descriptions.each { description ->
			def myDebits = budgetDebits.findAll{ it.description == description }
			def sum = myDebits.sum{ it.pennies }
			debitByDescription << [description, sum/100]
		}
		println "debitByDescription = $debitByDescription"
		
		render(view:'budgetDebits', model:[budget:budget, debitTotal:debitTotal, debits:budgetDebits, debitByCategory:debitByCategory, 
			debitByDescription:debitByDescription, startDate:startDateStr, endDate:endDateStr])
	}
}
