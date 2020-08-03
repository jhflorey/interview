package com.hps.luhn;

import java.math.BigInteger;

/**
 * @see https://en.wikipedia.org/wiki/Luhn_algorithm#Description
 */
public class Luhn {

	/**
	 * TODO
	 * 
	 * Accepts a card number and determines if the card number is a valid number
	 * with respect to the Luhn algorithm.
	 * 
	 * @param cardNumber
	 *            the card number
	 * 
	 * @return true if the card number is valid according to the Luhn algorithm,
	 *         false if not
	 */
	public boolean isValidLuhn(BigInteger cardNumber) {
		return cardNumber.mod(BigInteger.TEN).intValue() == generateCheckDigit(cardNumber.divide(BigInteger.TEN));
	}

	/**
	 * Accepts a partial card number (excluding the last digit) and generates
	 * the appropriate Luhn check digit for the number.
	 * 
	 * @param cardNumber
	 *            the card number (not including a check digit)
	 * 
	 * @return the check digit
	 */
	public int generateCheckDigit(BigInteger cardNumber) {
		boolean doubleDigit = true;
		int sum = 0;
		while (cardNumber.compareTo(BigInteger.ZERO) > 0) {
			// starting from the right (rightmost is the unknown check digit)
			long digit = cardNumber.mod(BigInteger.TEN).longValue();

			if (doubleDigit) { // double the value of every second digit
				digit *= 2;

				// if two digits, use the sum of the digits
				// the condition must be larger or equal 10
				if (digit >= 10) {
					digit = digit / 10 + digit % 10; 
				}
			}
			doubleDigit = !doubleDigit;

			sum += digit;

			cardNumber = cardNumber.divide(BigInteger.TEN); // remaining digits to the left
		}

		return sum * 9 % 10;
	}

	/**
	 * TODO
	 * 
	 * Accepts two card numbers representing the starting and ending numbers of
	 * a range of card numbers and counts the number of valid Luhn card numbers
	 * that exist in the range, inclusive.
	 * 
	 * @param startRange
	 *            the starting card number of the range (may not be valid luhn)
	 * @param endRange
	 *            the ending card number of the range (may not be a valid luhn)
	 * 
	 * @return the number of valid Luhn card numbers in the range, inclusive
	 */
	public int countRange(BigInteger startRange, BigInteger endRange) {
		BigInteger runner = startRange;
		int validLuhn = 0;

		while (runner.compareTo(endRange) <= 0) {
			if (isValidLuhn(runner))
				validLuhn += 1;

			runner = runner.add(BigInteger.ONE);
		}

		return validLuhn;
	}
}
