package com.shihweihuang;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class CalculatorTest {
	Calculator clac;
	PreFixVisitor prfv;
	InFixVisitor ifv;
	PostFixVisitor pofv;
	EvaluationVisitor efv;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setup() {
		clac = new Calculator();
		prfv = new PreFixVisitor();
		ifv = new InFixVisitor();
		pofv = new PostFixVisitor();
		efv = new EvaluationVisitor();
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUp() {
		System.setOut(null);
	}

	@Test
	public void testInFixVisitor1() {
		clac.setExpression("(9-2*3)*4/(2+1)");
		clac.accept(ifv);
		assertEquals("( 9 - 2 * 3 ) * 4 / ( 2 + 1 ) ",
				outContent.toString());
	}
	
	@Test
	public void testInFixVisitor2() {
		clac.setExpression("1+2*(5-9)+6-6/(4+1)");
		clac.accept(ifv);
		assertEquals("1 + 2 * ( 5 - 9 ) + 6 - 6 / ( 4 + 1 ) ",
				outContent.toString());
	}
	
	@Test
	public void testPreFixVisitor1() {
		clac.setExpression("1+2*(5-9)+6-6/(4+1)");
		clac.accept(prfv);
		assertEquals("- + + 1 * 2 - 5 9 6 / 6 + 4 1",
				outContent.toString());
	}
	
	@Test
	public void testPreFixVisitor2() {
		clac.setExpression("(9-2*3)*4/(2+1)");
		clac.accept(prfv);
		assertEquals("/ * - 9 * 2 3 4 + 2 1",
				outContent.toString());
	}
	
	@Test
	public void testPostFixVisitor1() {
		clac.setExpression("1+2*(5-9)+6-6/(4+1)");
		clac.accept(pofv);
		assertEquals("1 2 5 9 - * + 6 + 6 4 1 + / - ",
				outContent.toString());
	}
	
	@Test
	public void testPostFixVisitor2() {
		clac.setExpression("(9-2*3)*4/(2+1)");
		clac.accept(pofv);
		assertEquals("9 2 3 * - 4 * 2 1 + / ",
				outContent.toString());
	}
	
	@Test
	public void testEvaluationVisitor1() {
		clac.setExpression("1+2*(5-9)+6-6/(4-1)");
		clac.accept(efv);
		assertEquals("-3.0 ",
				outContent.toString());
	}
	
	@Test
	public void testEvaluationVisitor2() {
		clac.setExpression("(9-2*3)*4/(2+1)");
		clac.accept(efv);
		assertEquals("4.0 ",
				outContent.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInValidExpression1(){
		clac.setExpression("1++2*(5-9)+6-6/(4-1)");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInValidExpression2(){
		clac.setExpression("1+2*(5-9)+6-6/(4-1)-");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInValidExpression3(){
		clac.setExpression("/1+2*(5-9)+6-6/(4-1)");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInValidExpression4(){
		clac.setExpression("pqs+2*(5-9)+6-6/(4-1)");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInValidExpression5(){
		clac.setExpression("9+2*((5-9)+6-6/(4-1)");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInValidExpression6(){
		clac.setExpression("8.5+2*((5-9)+6-6/(4-1)");
	}
}
