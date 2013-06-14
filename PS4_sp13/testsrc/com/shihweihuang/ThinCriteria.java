package com.shihweihuang;

public class ThinCriteria implements Criteria<Customer> {

	@Override
	public boolean accept(Customer item) {
		if (item != null && item.getWeight() < 150){
			return true;
		}
		return false;
	}

}
