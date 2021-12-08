package com.fichajespi.specifications.common;

import java.text.MessageFormat;

public class SpecificationHelper {

	public static String contains(String expression) {
		return MessageFormat.format("%{0}%", expression);
	}

}
