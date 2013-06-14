package com.shihweihuang;

import java.util.Stack;

/**
 * Visit a calculator and print out the expression in pre-fix form
 * using an in-fix to pre-fix algorithm
 * @author shihweihuang
 * 
 */
public class PreFixVisitor implements Visitor {
	Stack<Operator> opStack = new Stack<Operator>();
	Stack<String> noStack = new Stack<String>();

	public PreFixVisitor() {
		//the in-fix to pre-fix algorithm need to add a pair of parentheses at begin and end of the expression
		opStack.push(new Operator("("));
	}

	@Override
	public void visit(Number number) {
		noStack.push(number.toString());
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
				String no2 = noStack.pop();
				String no1 = noStack.pop();
				String op = opStack.pop().toString();
				noStack.push(op + " " + no1 + " " + no2);
			}
			opStack.pop();
			return;
		}

		if (opStack.peek().getPrecedence() < operator.getPrecedence()) {
			opStack.push(operator);
		} else if (opStack.peek().getPrecedence() >= operator.getPrecedence()) {
			while (!opStack.isEmpty()
					&& opStack.peek().getPrecedence() >= operator.getPrecedence()) {
				String no2 = noStack.pop();
				String no1 = noStack.pop();
				String op = opStack.pop().toString();
				noStack.push(op + " " + no1 + " " + no2);
			}
			opStack.push(operator);
		}
	}

	@Override
	public void visit(Calculator calculator) {
		for (Visitable e : calculator.getElements()) {
			e.accept(this);
		}
		Visitable lastOp = new Operator(")");
		lastOp.accept(this);

		while (!noStack.isEmpty()) {
			System.out.print(noStack.pop());
		}
	}

}
