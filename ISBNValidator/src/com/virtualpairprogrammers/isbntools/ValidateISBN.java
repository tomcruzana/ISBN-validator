package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {

	public boolean checkISBN(String isbn) {

		// check if isbn digits is 13
		if (isbn.length() == 13) {
			int total = 0;

			for (int i = 0; i < 13; i++) {
				if (i % 2 == 0) { // check if even or odd
					total += Character.getNumericValue(isbn.charAt(i)); // if even
				} else {
					total += Character.getNumericValue(isbn.charAt(i)) * 3; // if odd
				}
			}
			return (total % 10 == 0) ? true : false; // check if divisible by 10

		} else {
			// check if isbn digits is 10
			if (isbn.length() != 10) {
				throw new NumberFormatException("ISBN numbers must be 10 digits long.");
			}

			int total = 0;

			for (int i = 0; i < 10; i++) {
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
				total += Character.getNumericValue(isbn.charAt(i)) * (10 - i); // convert the charAt to an int numeric
																				// value and multiply by 10 - 1

			}

			return (total % 11 == 0) ? true : false; // check if divisible by 11
		}

	}

}
