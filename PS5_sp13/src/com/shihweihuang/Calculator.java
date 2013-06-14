package com.shihweihuang;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A calculator that takes a string expression and can print out in-fix,
 * pre-fix, post-fix notation or evaluation value using different kinds of
 * visitors
 * 
 * @author shihweihuang
 * 
 */
/**
 * @author shihweihuang
 * 
 */
public class Calculator implements Visitable {

	private String expression;
	List<Visitable> elements = new ArrayList<Visitable>();

	public Calculator() {

	}

	/**
	 * Where Calculator take an in-fix expression and check validity of it
	 * parse it into a list of tokens
	 * @param expression
	 *          String form in-fix expression
	 * @throws IllegalArgumentException
	 *           if get an invalid expression
	 */
	public void setExpression(String expression) {
		this.expression = expression;
		elements.clear();
		parseExpression();

	}

	/*
	 * parse expression into a list of tokens
	 */
	private void parseExpression() {
		int i = 0;
		while (i < expression.length()) {
			String ch = expression.substring(i, i + 1);
			if (isOperator(ch)) {
				elements.add(new Operator(ch));
				i++;
			} else {
				int startPosition = i;
				int endPosition = i + 1;
				if (endPosition == expression.length()) {
					String stringValue = expression.substring(startPosition, endPosition);
					if (!stringValue.trim().equals("")) {
						elements.add(new Number(stringValue));
					}
					i = endPosition;
					continue;
				}
				String nu = expression.substring(i + 1, i + 2);
				while (!isOperator(nu) && i + 2 <= expression.length()) {
					endPosition = i + 2;
					i++;
					if (i + 2 <= expression.length()) {
						nu = expression.substring(i + 1, i + 2);
					}
				}

				String stringValue = expression.substring(startPosition, endPosition);
				if (!stringValue.trim().equals("")) {
					elements.add(new Number(stringValue));
				}
				i = endPosition;
			}
		}

		boolean isGoodExpression = checkExpression();
		if (!isGoodExpression) {
			expression = "";
			elements.clear();
			throw new IllegalArgumentException();
		}
	}

	/*
	 * check if a character is an operator
	 */
	private boolean isOperator(String ch) {
		if (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/")
				|| ch.equals("(") || ch.equals(")")) {
			return true;
		}
		return false;
	}

	/*
	 * check if an expression valid or not
	 */
	private boolean checkExpression() {
		Pattern regex = Pattern.compile("^[0-9+\\.\\-*/()\\ ]+$", Pattern.COMMENTS);
		Matcher matcher = regex.matcher(expression);
		if (!matcher.find()) {
			return false;
		}
		if (!checkParens(expression)) {
			return false;
		}
		if (!checkGrammer(elements)) {
			return false;
		}
		return true;
	}

	/*
	 * check simple grammer of expression
	 */
	private boolean checkGrammer(List<Visitable> elements) {
		Visitable first = elements.get(0);
		Visitable last = elements.get(elements.size() - 1);
		if (first.getType() == ElementType.OPERATOR
				|| last.getType() == ElementType.OPERATOR) {
			return false;
		}
		ArrayList<Visitable> newList = new ArrayList<Visitable>(elements);
		for (int i = 0; i < newList.size(); i++) {
			Visitable e = newList.get(i);
			if (e.getType() == ElementType.PARENS) {
				newList.remove(i);
			}
		}
		for (int i = 0; i < newList.size() - 1; i++) {
			Visitable e1 = newList.get(i);
			Visitable e2 = newList.get(i + 1);
			if (e1.getType() == e2.getType()) {
				return false;
			}
		}
		return true;
	}

	/*
	 * check if parentheses are in pair
	 */
	private boolean checkParens(String expression) {
		int openParens = 0;
		int closeParens = 0;
		for (int i = 0; i < expression.length(); i++) {
			if (expression.substring(i, i + 1).equals("(")) {
				openParens++;
			}
			if (expression.substring(i, i + 1).equals(")")) {
				closeParens++;
			}
		}
		if (openParens == closeParens) {
			return true;
		} else {
			return false;
		}
	}

	public List<Visitable> getElements() {
		return elements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shihweihuang.Element#accept(com.shihweihuang.Visitor)
	 */
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public ElementType getType() {
		return null;
	}
}
