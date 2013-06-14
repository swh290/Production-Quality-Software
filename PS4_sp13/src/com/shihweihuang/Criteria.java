package com.shihweihuang;

public interface Criteria<T> {
	/**
	 * @param item
	 * @return true if item pass the criteria test
	 */
	boolean accept(T item);
}
