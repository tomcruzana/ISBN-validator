package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkAValid10digitISBN() {
		//1st logic test
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("1789613477");
		assertTrue("first isbn value test", result);
		
		//2nd identical logic test w/ different isbn
		result = validator.checkISBN("1465475575");	
		assertTrue("second isbn value test", result);
	}
	
	/*Test 13-digit ISBN*/
	@Test
	public void checkAValid13digitISBN() {
		//1st test logic
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781627516181");
		assertTrue(result);
		
		//2nd identical test logic
		result = validator.checkISBN("9780321563842");
		assertTrue(result);
	}
	
	@Test
	public void TenDigitISBNNumbersEndingInAnXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue("isbn with x test", result);
	}
	
	@Test
	public void checkAnInvalid10digitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("1789613476");
		assertFalse(result);
	}
	
	@Test
	public void checkAnInvalid13digitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781627516186");
		assertFalse(result);
	}
	
	//JUnit 4 exception checking
	@Test(expected = NumberFormatException.class)
	public void nineDigitISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("123456789");
	}
	
	@Test(expected = NumberFormatException.class)
	public void nonNumericISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("helloworld");
		
	}
	


}
