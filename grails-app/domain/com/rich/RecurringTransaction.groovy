package com.rich

import java.util.Date;

class RecurringTransaction {

	Date startDate
	Date endDate
	String name
	int dayOfMonth
	Category type
	double amount
	boolean isCredit
	Account account
	
    static constraints = {
		account(nullable:false)
		name(nullable:false,blank:false, minSize:1, maxSize:50)
		type(nullable:false)
		amount(nullable:false)
		isCredit(nullable:false)
		dayOfMonth(nullable:false)
		startDate(nullable:false)
		endDate(nullable:true)
    }
}
