package com.shihweihuang;

public class InFixVisitor implements Visitor {

	@Override
	public void visit(Number number) {
		System.out.print(number.toString() + " ");
	}

	@Override
	public void visit(Operator operator) {
		System.out.print(operator.toString() + " ");
	}

	@Override
	public void visit(Calculator calculator) {
		for (Visitable e : calculator.getElements()){
			e.accept(this);
		}
		
	}

}
