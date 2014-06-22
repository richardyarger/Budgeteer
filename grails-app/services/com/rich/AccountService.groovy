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
				sum "amount"
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
