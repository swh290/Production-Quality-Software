package com.shihweihuang;

import java.util.Stack;

/**
 * Visit a calculator and print out the expression in post-fix form
 * using an in-fix to post-fix algorithm
 * @author shihweihuang
 * 
 */
public class PostFixVisitor implements Visitor {
	Stack<Operator> opStack = new Stack<Operator>();

	@Override
	public void visit(Number number) {
		System.out.print(number.toString() + " ");
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
				System.out.print(op.toString() + " ");
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
				System.out.print(op.toString() + " ");
			}
			opStack.push(operator);
		}
	}

	@Override
	public void visit(Calculator calculator) {
		for (Visitable e : calculator.getElements()) {
			e.accept(this);
		}
		while (!opStack.isEmpty()) {
			System.out.print(opStack.pop().toString() + " ");
		}
	}

}
