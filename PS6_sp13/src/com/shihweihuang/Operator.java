package com.shihweihuang;

/**
 * An element that is an Operator, can be visit by a visitor
 * 
 * @author shihweihuang
 * 
 */
public class Operator implements Visitable {
	private OperatorType operatorType;
	private String stringValue;
	private int precedence;
	private ElementType elementType;

	public Operator(String stringValue) {
		this.stringValue = stringValue;
		elementType = ElementType.OPERATOR;
		if (stringValue.equals("*")) {
			precedence = 3;
			operatorType = OperatorType.TIMES;
		} else if (stringValue.equals("/")) {
			precedence = 3;
			operatorType = OperatorType.DEVIDE;
		} else if (stringValue.equals("+")) {
			precedence = 2;
			operatorType = OperatorType.PLUS;
		} else if (stringValue.equals("-")) {
			precedence = 2;
			operatorType = OperatorType.MINUS;
		} else {
			elementType = ElementType.PARENS;
		}
	}

	public int getPrecedence() {
		return precedence;
	}

	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return stringValue;
	}

	@Override
	public ElementType getType() {
		return elementType;
	}
}
