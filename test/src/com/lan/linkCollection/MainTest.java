package com.lan.linkCollection;

public class MainTest {
	public static void main(String[] args) {
		Node frist = new Node();
		Node second = new Node();
		frist.item = "a";
		frist.next = second;
		second.item = "b";
		second.next = null;
//		System.out.println(frist.item);
//		frist = frist.next;
//		System.out.println(frist.item);
		for (Node i = frist; i != null; i = i.next) {
			System.out.println(i.item);
		}
	}
}
