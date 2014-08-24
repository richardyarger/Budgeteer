package com.rich

class Budget {

	String name
	double amount
	Long pennies
	
    static constraints = {
		name(blank:false, nullable:false, minSize:1, maxSize:50)
		amount(blank:false, nullable:false)
		pennies(nullable:true)
    }
	
	public String toString(){
		return name
	}
	
	public void setAmount(double amount){
		this.amount = amount
		this.pennies = amount*100
	}
}
