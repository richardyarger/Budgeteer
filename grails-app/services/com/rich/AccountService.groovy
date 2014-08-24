package com.rich

import grails.transaction.Transactional

@Transactional
class AccountService {

    def creditsByDate(Date startDate, Date endDate=new Date(), Account accountParam=null, Category categoryParam=null, Budget budgetParam=null) {
		return transactionQuery(startDate, endDate, null, true, accountParam, categoryParam, budgetParam)
    }
	
    def debitsByDate(Date startDate, Date endDate=new Date(), Account accountParam=null, Category categoryParam=null, Budget budgetParam=null) {
    	return transactionQuery(startDate, endDate, null, false, accountParam, categoryParam, budgetParam)
	}
	
	def transactionQuery(Date startDate, Date endDate=new Date(), String[] groupBy, Boolean isCredit=null, Account accountParam=null, Category categoryParam=null, Budget budgetParam=null) {
		def c = Transaction.createCriteria()
		def currentMonthOut = c.get {
			and {
				between "transactionDate", startDate, endDate
				if(isCredit != null)
					eq "isCredit", isCredit
				if(accountParam)
					eq "account", accountParam
				if(categoryParam)
					eq "category", categoryParam
				if(budgetParam){
					type {
						eq 'budget', budgetParam
					}
				}
			}
			projections {
				groupBy?.each{
					groupProperty(it)
				}
				sum "pennies"
			}
		}
	}
	
	def debitsByBudget(Date startDate, Date endDate=new Date()) {
		def c = Transaction.createCriteria()
		def currentMonthOut = c.list {
			createAlias("type","category")
			createAlias("type.budget","typebudget")
			and {
				eq "isCredit", false
				between "transactionDate", startDate, endDate
			}
			projections {
				groupProperty('category.budget')
				sum 'pennies'
				property 'typebudget.pennies'
				property 'typebudget.id'
			}
		}
	}
	
	def budgetDebits(def budgetId, Date startDate, Date endDate=new Date()) {
		def c = Transaction.createCriteria()
		def currentMonthOut = c.list {
			and {
				eq 'isCredit', false
				between 'transactionDate', startDate, endDate
				type{
					eq 'budget.id', budgetId
				}
			}
		}
	}
	
//	def debitsByDate(Date startDate, Date endDate, Account accountParam, Budget budgetParam) {
//		def c = Transaction.createCriteria()
//		def currentMonthOut = c.get {
//			and {
//				eq "isCredit", false
//				between "transactionDate", startDate, endDate
//				eq "account", accountParam
//				type {
//					eq 'budget', budgetParam
//				}
//			}
//			projections {
//				sum "amount"
//			}
//		}
//	}
}
