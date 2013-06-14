package com.shihweihuang;

/**
 * And two given criteria
 * @author shihweihuang
 *
 * @param <T>
 */
public class AndCriteria<T> implements Criteria<T> {
	Criteria<T> first;
	Criteria<T> second;
	
	/**
	 * @param first criteria
	 * @param second criteria
	 */
	public AndCriteria(Criteria<T> first, Criteria<T> second){
		this.first = first;
		this.second = second;
	}
	
	/* (non-Javadoc)
	 * @see com.shihweihuang.Criteria#accept(java.lang.Object)
	 */
	@Override
	public boolean accept(T item) {
		return first.accept(item) && second.accept(item);
	}

}
