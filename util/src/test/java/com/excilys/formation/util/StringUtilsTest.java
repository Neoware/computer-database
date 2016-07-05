package com.excilys.formation.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testIsNullOrEmptyWithNullString() {
		String string = null;
		assertTrue(StringUtils.isNullOrEmpty(string));
	}

	@Test
	public void testIsNullOrEmptyWithEmptyString() {
		String string = "";
		assertTrue(StringUtils.isNullOrEmpty(string));
	}

	@Test
	public void testIsNullOrEmptyWithFilledString() {
		String string = "Hello world !";
		assertFalse(StringUtils.isNullOrEmpty(string));
	}
}
