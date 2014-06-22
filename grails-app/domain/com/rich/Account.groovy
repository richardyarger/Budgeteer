package com.rich

class Account {

	String name
	AccountType type
	double startingBalance
	
    static constraints = {
		name(nullable:false,blank:false, minSize:1,maxSize:50)
		type(nullable:false)
		startingBalance(nullable:false)
    }
	
	public String toString(){
		return name
	}
}
