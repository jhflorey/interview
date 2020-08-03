package com.hps.luhn;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class LuhnUnitTest {

	Luhn luhn;

	@Before
	public void setUp() throws Exception {
		luhn = new Luhn();
	}

	@Test
	public void generateCheckDigit() {
		assertEquals(9, luhn.generateCheckDigit(new BigInteger("92739871")));
		assertEquals(0, luhn.generateCheckDigit(new BigInteger("92739875")));
	}

	@Test
	public void isValidLuhn() {
		assertFalse(luhn.isValidLuhn(new BigInteger("927398710")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398711")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398712")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398713")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398714")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398715")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398716")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398717")));
		assertFalse(luhn.isValidLuhn(new BigInteger("927398718")));
		assertTrue(luhn.isValidLuhn(new BigInteger("927398719")));
		assertTrue(luhn.isValidLuhn(new BigInteger("927398750")));
		assertTrue(luhn.isValidLuhn(new BigInteger("4242424242424242")));
		assertTrue(luhn.isValidLuhn(new BigInteger("5555555555554444")));
		assertTrue(luhn.isValidLuhn(new BigInteger("378282246310005")));
		assertTrue(luhn.isValidLuhn(new BigInteger("6011111111111117")));
		assertTrue(luhn.isValidLuhn(new BigInteger("36227206271667")));
		assertTrue(luhn.isValidLuhn(new BigInteger("3566002020360505")));
	}

	@Test
	public void countRange() {
		assertEquals(1, luhn.countRange(new BigInteger("927398710"), new BigInteger("927398720")));
		assertEquals(2, luhn.countRange(new BigInteger("927398710"), new BigInteger("927398730")));
	}

}
