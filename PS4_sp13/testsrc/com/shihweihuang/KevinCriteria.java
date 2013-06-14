package com.shihweihuang;

public class KevinCriteria implements Criteria<Customer> {

	@Override
	public boolean accept(Customer item) {
		if (item != null && item.getName().contains("Kevin")){
			return true;
		}
		return false;
	}

}
