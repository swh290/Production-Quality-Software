package com.shihweihuang;

public class TallCriteria implements Criteria<Customer> {

	@Override
	public boolean accept(Customer item) {
		if (item != null && item.getHeight() >= 6){
			return true;
		}
		return false;
	}

}
