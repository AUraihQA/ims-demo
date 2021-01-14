package com.qa.ims.utils;

import java.util.Scanner;

public class Utils {

	private Utils() {

	}

	public static String getInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public static Double getInputD() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextDouble();
	}

	public static Long getInputL() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLong();
	}

}
