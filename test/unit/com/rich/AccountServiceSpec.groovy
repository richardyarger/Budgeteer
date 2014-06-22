package com.rich

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AccountService)
@Mock([Account, Budget, Category, Transaction])
class AccountServiceSpec extends Specification {

	def accountService
	
    def setup() {
		accountService = new AccountService()
    }

    def cleanup() {
    }

    void "test something"() {
//		when:'There is a $500 credit'
//		//Budget budget1 = new Budget(name:'budget1',amount:2500)
//		//Category category1 = new Category(name:'category1',budget:budget1,isFsa:false)
//		AccountType accountType1 = new AccountType(name:'accountType1')
//		Account account1 = new Account(name:'account1',type:accountType1,startingBalance:'1000')
//		Transaction trans1 = new Transaction(account:account1,amount:500,
//			description:'deposit 500',isCredit:true,transactionDate:(new Date() - 6))
//		
//		then:'creditsByDate should return $500'
//		Date startDate = new Date() - 7
//		assertEquals 500, accountService.creditsByDate(startDate, new Date(), account1)
		
    }
}
