package com.shihweihuang;

/**
 * Negation of given criteria
 * @author shihweihuang
 *
 * @param <T>
 */
public class NotCriteria<T> implements Criteria<T> {
	Criteria<T> criteria;
	
	public NotCriteria(Criteria<T> criteria){
		this.criteria = criteria;
	}
	
	/* (non-Javadoc)
	 * @see com.shihweihuang.Criteria#accept(java.lang.Object)
	 */
	@Override
	public boolean accept(T item) {
		return !criteria.accept(item);
	}
}
