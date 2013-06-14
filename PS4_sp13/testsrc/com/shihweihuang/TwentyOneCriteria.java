package com.shihweihuang;

public class TwentyOneCriteria implements Criteria<Customer> {

	@Override
	public boolean accept(Customer item) {
		if (item != null && item.getAge() >= 21){
			return true;
		}
		return false;
	}

}
