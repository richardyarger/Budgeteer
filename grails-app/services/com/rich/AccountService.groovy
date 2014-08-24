package com.rich

import grails.transaction.Transactional

@Transactional
class AccountService {

    def creditsByDate(Date startDate, Date endDate=new Date(), Account accountParam=null, Category categoryParam=null, Budget budgetParam=null) {
		def c = Transaction.createCriteria()
		def currentMonthIn = c.get {
			and {
				eq "isCredit", true
				between "transactionDate", startDate, endDate
				if(accountParam){
					eq "account", accountParam
				}
				if(categoryParam){
					eq "category", categoryParam
				}
				if(budgetParam){
					category{
						eq 'budget', budgetParam
					}
				}
			}
			projections {
				sum "amount"
			}
		}
    }
	
    def debitsByDate(Date startDate, Date endDate=new Date(), Account accountParam=null, Category categoryParam=null, Budget budgetParam=null) {
    	return debitsQuery(startDate, endDate, null, accountParam, categoryParam, budgetParam)
	}
	
	def debitsQuery(Date startDate, Date endDate=new Date(), String[] groupBy, Account accountParam=null, Category categoryParam=null, Budget budgetParam=null) {
		def c = Transaction.createCriteria()
		def currentMonthOut = c.get {
			and {
				eq "isCredit", false
				between "transactionDate", startDate, endDate
				if(accountParam){
					eq "account", accountParam
				}
				if(categoryParam){
					eq "category", categoryParam
				}
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
				sum "amount"
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
				sum 'amount'
				property 'typebudget.amount'
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
