package com.rich

class Category {

	String name
	Budget budget
	boolean isFsa=false
	
    static constraints = {
		name(blank:false, nullable:false, minSize:1, maxSize:50)
		budget(blank:false,nullable:false)
		isFsa(blank:false, nullable:false)
    }
	
	public String toString(){
		return name
	}
}
