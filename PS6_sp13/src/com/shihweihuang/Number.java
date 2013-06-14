package com.shihweihuang;

/**
 * An element that is an number, can be visit by a visitor
 * 
 * @author shihweihuang
 * 
 */
public class Number implements Visitable {
	private String stringValue;
	private double value;

	public Number(String stringValue) {
		this.stringValue = stringValue.trim();
		this.value = Double.parseDouble(this.stringValue);
	}

	@Override
	public String toString() {
		return stringValue;
	}

	public double getValue() {
		return value;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public ElementType getType() {
		return ElementType.NUMBER;
	}
}
