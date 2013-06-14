package com.shihweihuang;

public interface Visitor {
	void visit(Number number);
	void visit(Operator operator);
	void visit(Calculator calculator);
}
