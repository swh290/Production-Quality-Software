package com.shihweihuang;

public class CriteriaFactory<T> {
	/**
	 * @param first criteria
	 * @param second criteria
	 * @return new criteria that fulfill first and second criteria
	 */
	public AndCriteria<T> and(Criteria<T> first, Criteria<T> second){
		return new AndCriteria<T>(first, second);
	}
	
	/**
	 * @param first criteria
	 * @param second criteria
	 * @return new criteria that fulfill first or second criteria
	 */
	public OrCriteria<T> or(Criteria<T> first, Criteria<T> second){
		return new OrCriteria<T>(first, second);
	}
	
	/**
	 * @param criteria
	 * @return new criteria that fulfill negation of criteria
	 */
	public NotCriteria<T> not(Criteria<T> criteria){
		return new NotCriteria<T>(criteria);
	}
}
