package com.shihweihuang;

public interface Visitable {
	/**
	 * Visitor pattern for accepting visitor
	 * @param visitor
	 */
	public void accept(Visitor visitor);
	public ElementType getType();
}
