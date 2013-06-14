package com.shihweihuang;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator warper that takes an iterator and criteria as parameter. Can
 * skip all values that do not satisfy a specified criteria
 * 
 * @author shihweihuang
 * 
 * @param <E>
 */
public class FilterIterator<E> implements Iterator<E> {

	Iterator<E> iterator;
	Criteria<E> criteria;
	boolean hasItemToReturn = false;
	E item = null;

	public FilterIterator(Iterator<E> iterator, Criteria<E> criteria) {
		this.iterator = iterator;
		this.criteria = criteria;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (hasItemToReturn) {
			return true;
		} else {
			while (iterator.hasNext()) {
				item = iterator.next();
				boolean pass = criteria.accept(item);
				if (pass) {
					hasItemToReturn = true;
					return true;
				} else {
					hasItemToReturn = false;
					item = null;
				}
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public E next() {
		if (!hasItemToReturn) {
			if (this.hasNext()) {
				hasItemToReturn = false;
				return item;
			} else {
				throw new NoSuchElementException();
			}
		} else {
			hasItemToReturn = false;
			return item;
		}

	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
