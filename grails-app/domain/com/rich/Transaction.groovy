package com.rich

import java.util.Date;

import com.rich.Category;

class Transaction {

	Date transactionDate
	String description
	Integer checkNumber
	Category type
	boolean isFee=false
	double amount
	boolean isCredit
	Account account
	Long pennies
	Date dateCreated
	
    static constraints = {
		description(blank:false, nullable:false, minSize:1, maxSize:50)
		transactionDate(blank:false, nullable:false)
		account(nullable:false)
		checkNumber(nullable:true)
		type(blank:false, nullable:false)
		amount(blank:false, nullable:false)
		isCredit(blank:false, nullable:false)
		pennies(display:false)
    }
	
	public void setAmount(double amount){
		println "setAmount($amount)"
		this.amount = amount
		this.pennies = amount*100
	}
}
