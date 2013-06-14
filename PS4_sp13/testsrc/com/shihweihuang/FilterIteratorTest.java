package com.shihweihuang;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class FilterIteratorTest {

	List<Customer> list = new ArrayList<Customer>();
	Iterator<Customer> iterator;
	CriteriaFactory<Customer> criteriaFactory = new CriteriaFactory<Customer>();
	Criteria<Customer> twentyOneCriteria = new TwentyOneCriteria();
	Criteria<Customer> kevinCriteria = new KevinCriteria();
	Criteria<Customer> tallCriteria = new TallCriteria();
	Criteria<Customer> thinCriteria = new ThinCriteria();

	@Before
	public void setup() {
		list.add(new Customer("Jacky Chen", "0123", 15, 7.1, 180));
		list.add(new Customer("Kevin Smith", "0345", 16, 8, 98));
		list.add(new Customer("Jimmy Jordan", "2354", 17, 5.5, 120));
		list.add(new Customer("Kobe Bryant", "6562", 87, 6.5, 130));
		list.add(new Customer("Kevin Obama", "6547", 44, 6.8, 188));
		list.add(new Customer("Alex James", "9786", 9, 3.9, 200));
		list.add(new Customer("James Bond", "2345", 28, 4.5, 143));
		list.add(new Customer("Monkey D Lufy", "1111", 65, 7.1, 132));
		list.add(new Customer("Kevin", "5345", 36, 7.6, 199));
		iterator = list.iterator();
	}

	@After
	public void cleanUp() {
		list.clear();
		iterator = null;
	}

	@Test
	public void testOneCriteria() {
		iterator = new FilterIterator<Customer>(iterator, new TwentyOneCriteria());
		Customer expect = new Customer("Kobe Bryant", "6562", 87, 6.5, 130);
		assertEquals(expect, iterator.next());
		expect = new Customer("Kevin Obama", "6547", 44, 6.8, 188);
		assertEquals(expect, iterator.next());
	}

	@Test
	public void testNotCriteria() {
		Criteria<Customer> criteria = criteriaFactory.not(twentyOneCriteria);
		iterator = new FilterIterator<Customer>(iterator, criteria);
		Customer expect = new Customer("Jacky Chen", "0123", 15, 7.1, 180);
		assertEquals(expect, iterator.next());
		expect = new Customer("Kevin Smith", "0345", 16, 8, 98);
		assertEquals(expect, iterator.next());
		iterator.next();
		iterator.next();
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testAndCriteria() {
		Criteria<Customer> criteria = criteriaFactory.and(twentyOneCriteria,
				tallCriteria);
		iterator = new FilterIterator<Customer>(iterator, criteria);
		Customer expect = new Customer("Kobe Bryant", "6562", 87, 6.5, 130);
		assertEquals(expect, iterator.next());
		expect = new Customer("Kevin Obama", "6547", 44, 6.8, 188);
		assertEquals(expect, iterator.next());
		assertTrue(iterator.hasNext());
	}

	@Test
	public void testOrCriteria() {
		Criteria<Customer> criteria = criteriaFactory.or(kevinCriteria,
				thinCriteria);
		iterator = new FilterIterator<Customer>(iterator, criteria);
		Customer expect = new Customer("Kevin Smith", "0345", 16, 8, 98);
		assertEquals(expect, iterator.next());
		expect = new Customer("Jimmy Jordan", "2354", 17, 5.5, 120);
		assertEquals(expect, iterator.next());
		expect = new Customer("Kobe Bryant", "6562", 87, 6.5, 130);
		assertEquals(expect, iterator.next());
		expect = new Customer("Kevin Obama", "6547", 44, 6.8, 188);
		assertEquals(expect, iterator.next());
		expect = new Customer("James Bond", "2345", 28, 4.5, 143);
		assertEquals(expect, iterator.next());
		assertTrue(iterator.hasNext());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testUnsupportedOperationException(){
		Criteria<Customer> criteria = criteriaFactory.or(kevinCriteria,
				thinCriteria);
		iterator = new FilterIterator<Customer>(iterator, criteria);
		iterator.next();
		iterator.remove();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testNoSuchElementException(){
		Criteria<Customer> criteria = criteriaFactory.and(kevinCriteria,
				thinCriteria);
		iterator = new FilterIterator<Customer>(iterator, criteria);
		iterator.next();
		iterator.next();
	}
	
	@Test
	public void testRandomCriteria() {
		Criteria<Customer> criteria = criteriaFactory.or(kevinCriteria,
				thinCriteria);
		criteria = criteriaFactory.not(criteria);
		iterator = new FilterIterator<Customer>(iterator, criteria);
		Customer expect = new Customer("Jacky Chen", "0123", 15, 7.1, 180);
		assertEquals(expect, iterator.next());
		expect = new Customer("Alex James", "9786", 9, 3.9, 200);
		assertEquals(expect, iterator.next());
		assertFalse(iterator.hasNext());
	}

}
