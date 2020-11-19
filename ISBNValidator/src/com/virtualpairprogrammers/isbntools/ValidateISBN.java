package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {

	private static final int LONG_ISBN_MULTIPLIER = 10;
	private static final int SHORT_ISBN_MULTIPLIER = 11;
	private static final int SHORT_ISBN_LENGTH = 10;
	private static final int LONG_ISBN_LENGTH = 13;

	public boolean checkISBN(String isbn) {

		// check if isbn digits is 13
		if (isbn.length() == LONG_ISBN_LENGTH) {
			return isThisAValidLongISBN(isbn);

		}
		// check if isbn digits is 10
		else if (isbn.length() == SHORT_ISBN_LENGTH) {
			return isThisAValidShortISBN(isbn);

		}

		throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long.");

	}

	private boolean isThisAValidShortISBN(String isbn) {
		int total = 0;

		for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
			if (!Character.isDigit(isbn.charAt(i))) {
				// check if last char is X
				if (i == 9 && isbn.charAt(i) == 'X') {
					// This is okay, and we need to add 10 as per ISBN calculation
					total += 10;
					// if index is 9 (which is the last digit AND if the last digit/char is capital
					// X
				} else {
					throw new NumberFormatException("ISBN numbers must be numeric");
				}
			}
			/*
			 * convert the charAt to an int numeric value and multiply by 10 - 1
			 */

			total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);

		}

		return (total % SHORT_ISBN_MULTIPLIER == 0) ? true : false; // check if divisible by 11
	}

	private boolean isThisAValidLongISBN(String isbn) {
		int total = 0;

		for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
			if (i % 2 == 0) { // check if even or odd
				total += Character.getNumericValue(isbn.charAt(i)); // if even
			} else {
				total += Character.getNumericValue(isbn.charAt(i)) * 3; // if odd
			}
		}
		return (total % LONG_ISBN_MULTIPLIER == 0) ? true : false; // check if divisible by 10
	}

}
