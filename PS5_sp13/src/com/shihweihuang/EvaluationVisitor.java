package com.shihweihuang;

import java.util.Stack;

/**
 * Evaluate in-fix expression into result
 * using post-fix notation to evaluate the value
 * @author shihweihuang
 * 
 */
public class EvaluationVisitor implements Visitor {
	Stack<Operator> opStack = new Stack<Operator>();
	Stack<Double> noStack = new Stack<Double>();

	@Override
	public void visit(Number number) {
		noStack.push(number.getValue());
	}

	@Override
	public void visit(Operator operator) {
		if (opStack.isEmpty()) {
			opStack.push(operator);
			return;
		}
		if (operator.toString().equals("(")) {
			opStack.push(operator);
			return;
		}
		if (operator.toString().equals(")")) {
			while (!opStack.isEmpty() && !opStack.peek().toString().equals("(")) {
				Operator op = opStack.pop();
				double no1 = noStack.pop();
				double no2 = noStack.pop();
				double result = evaluate(no1, no2, op.getOperatorType());
				noStack.push(result);
			}
			opStack.pop();
			return;
		}
		Operator opPeek = opStack.peek();
		if (opPeek.toString().equals("(")) {
			opStack.push(operator);
		} else if (opPeek.getPrecedence() < operator.getPrecedence()) {
			opStack.push(operator);
		} else if (opPeek.getPrecedence() >= operator.getPrecedence()) {
			while (!opStack.isEmpty()
					&& opStack.peek().getPrecedence() >= operator.getPrecedence()) {
				Operator op = opStack.pop();
				double no1 = noStack.pop();
				double no2 = noStack.pop();
				double result = evaluate(no1, no2, op.getOperatorType());
				noStack.push(result);
			}
			opStack.push(operator);
		}
	}

	private double evaluate(double no1, double no2, OperatorType op) {

		switch (op) {
		case PLUS:
			return no1 + no2;
		case DEVIDE:
			return no2 / no1;
		case MINUS:
			return no2 - no1;
		case TIMES:
			return no1 * no2;
		default:
			break;

		}
		return 0;
	}

	@Override
	public void visit(Calculator calculator) {
		for (Visitable e : calculator.getElements()) {
			e.accept(this);
		}
		while (!opStack.isEmpty()) {
			Operator op = opStack.pop();
			double no1 = noStack.pop();
			double no2 = noStack.pop();
			double result = evaluate(no1, no2, op.getOperatorType());
			noStack.push(result);
		}
		while (!noStack.isEmpty()) {
			System.out.print(noStack.pop().toString() + " ");
		}
	}

}
