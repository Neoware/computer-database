package com.excilys.formation.util;

import java.util.Collection;

public class ArrayUtils {

	public static <E> void printArray(Collection<E> collection) {
		for (E element : collection) {
			System.out.println(element);
		}
	}
}
