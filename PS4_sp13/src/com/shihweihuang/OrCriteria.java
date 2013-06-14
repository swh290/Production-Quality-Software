package com.shihweihuang;

/**
 * Or two given criteria
 * @author shihweihuang
 *
 * @param <T>
 */
public class OrCriteria<T>implements Criteria<T> {
	Criteria<T> first;
	Criteria<T> second;
	
	public OrCriteria(Criteria<T> first, Criteria<T> second){
		this.first = first;
		this.second = second;
	}
	
	@Override
	public boolean accept(T item) {
		return first.accept(item) || second.accept(item);
	}
}
