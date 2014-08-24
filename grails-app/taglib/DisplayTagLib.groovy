class DisplayTagLib {
    //static defaultEncodeAs = 'html'
    //static encodeAsForTags = [tagName: 'raw']
	
	/**
	 * Subtract one value from another and show the difference.
	 * If greater than or equal to 0, the difference will be green, else red
	 * 
	 * @attr value1 REQUIRED the value to subtract from
     * @attr value2 REQUIRED the value to subtract
	 */
	def diff = { attrs, body ->
		def diff = attrs.value1 - attrs.value2
		if(diff > 0)
			out << '<p style=\"color:red\">'
		else
			out << '<p style=\"color:green\">'
		out << diff
		out << '</p>'
	}
	
	/**
	 * Subtract one value from another and show the difference.
	 * If greater than or equal to 0, the difference will be green, else red
	 *
	 * @attr value1 REQUIRED the value to subtract from
	 * @attr value2 REQUIRED the value to subtract
	 */
	def dollarDiff = { attrs, body ->
		def diff = attrs.value1 - attrs.value2
		if(diff > 0)
			out << '<p style=\"color:red\">'
		else
			out << '<p style=\"color:green\">'
		out << diff/100
		out << '</p>'
	}
	
	def dollar = { attrs, body ->
		out << (attrs.amount)/100
	}
}
