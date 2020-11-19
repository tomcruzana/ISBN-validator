package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class StockManagementTests {

	ExternalISBNDataService testWebService;
	StockManager stockManager;
	ExternalISBNDataService testDatabaseService;

	@Before
	public void setup() {
		testWebService = mock(ExternalISBNDataService.class); // Mockito creates a dummy class that is an implementation of this interface
		stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		testDatabaseService = mock(ExternalISBNDataService.class);
		stockManager.setDatabaseService(testDatabaseService);
	}

	/*
	 * test stubs are programs that simulate the behaviours of software components
	 * (or modules) that a module undergoing tests depends on. Test stubs are mainly
	 * used in incremental testing's top-down approach. Stubs are computer programs
	 * that act as temporary replacement for a called basin module and give the same
	 * output as the actual product or software.more here:
	 * https://en.wikipedia.org/wiki/Test_stub
	 */

	@Test
	public void testCanGetACorrectLocatorCode() {

		/*
		 * Note about the author about the interface below: ExternalISBNDataService test
		 * = new ExternalISBNDataService();
		 * 
		 * This won't work because it's an interface, and you can't create a new
		 * instance of an interface - you need to create an instance of something that
		 * implements the interface. That's why in the code we create a stub
		 * implementation (see the video around 10 mins 40 secs) - we don't put a semi
		 * colon at the end of the line but instead provide a code block with an
		 * implementation.
		 * 
		 * The interface below is not an instantiation.
		 * 
		 * 
		 * ExternalISBNDataService testWebService = new ExternalISBNDataService() {
		 * 
		 * @Override public Book lookup(String isbn) { return new Book(isbn,
		 * "Of Mice And Men", "J. Steinbeck"); } };
		 * 
		 * ExternalISBNDataService testdatabaseService = new ExternalISBNDataService() {
		 * 
		 * @Override public Book lookup(String isbn) { return null; } };
		 */

		// Refactored version of the interface implementation above using Mockito
		// ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);
		when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice And Men", "J. Steinbeck"));
		when(testDatabaseService.lookup(anyString())).thenReturn(null);

		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	@Test
	public void databaseIsUsedIfDataIsPresent() {

		// If you want Mockito to return custom or non-default values
		when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abs"));

		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);

		// Mockito can ensure whether a mock method is being called with reequired
		// arguments or not. It is done using the verify() method.
		verify(testDatabaseService, times(1)).lookup("0140177396");
		verify(testWebService, times(0)).lookup(anyString());
	}

	@Test
	public void webServiceIsUsedIfDataIsNotPresentInDatabase() {


		// If you want Mockito to return custom or non-default values
		when(testDatabaseService.lookup("0140177396")).thenReturn(null);
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abs"));

		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);

		// Mockito can ensure whether a mock method is being called with reequired
		// arguments or not. It is done using the verify() method.
		verify(testDatabaseService, times(1)).lookup("0140177396");
		verify(testWebService, times(1)).lookup("0140177396");
	}

}
