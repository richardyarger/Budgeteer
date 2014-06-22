package com.rich

class AccountType {

	String name
	
    static constraints = {
		name(nullable:false,blank:false, minSize:1,maxSize:50)
    }
	
	public String toString(){
		return name
	}
}
