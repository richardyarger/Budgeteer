package com.rich

class Budget {

	String name
	double amount
	
    static constraints = {
		name(blank:false, nullable:false, minSize:1, maxSize:50)
		amount(blank:false, nullable:false)
    }
	
	public String toString(){
		return name
	}
}
