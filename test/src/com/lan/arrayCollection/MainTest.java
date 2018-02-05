package com.lan.arrayCollection;

public class MainTest {
	public static void main(String[] args) {
		FixedStack<String> fixedStack = new FixedStack<>(10);
		fixedStack.push("a");
		fixedStack.push("b");
		fixedStack.push("c");
		fixedStack.push("d");
		System.out.println(fixedStack.size());
		for (String string : fixedStack) {
			System.out.println(string);
		}
	}
}
