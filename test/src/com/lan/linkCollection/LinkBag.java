package com.lan.linkCollection;

import java.util.Iterator;

public class LinkBag<T> implements Iterable<T>{
	private Node first;
	private int N;
	private class Node {
		T item;
		Node next;
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void add(T t) {
		Node oldFirst = first;
		first = new Node();
		first.item = t;
		first.next = oldFirst;
		N++;
	}
	public void deleteNode(int k) {
		Node current = first;
		if (k == 1) {
			first = first.next;
		}else {
			Node after = first;
			for (int i = 0; i <= N - k; i++) {
				while (after != null) {
					if (after.next == null) {
						after = null;
						break;
					}
					if (after.next.next == null) {
						after.next = null;
						break;
					}
					after = after.next;
				}
			}
			Node before = first;
			for (int i = 1; i < k; i++) {
				before = before.next;
			}
			first.next = before;
		}
	}
	public void deleteLastNode() {
		Node current = first;
		
			while (current != null) {
				if (current.next == null) {
					first = null;
					break;
				}
				if (current.next.next == null) {
					current.next = null;
					break;
				}
				current = current.next;
			}
	}
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}
	private class ListIterator implements Iterator<T> {
		private Node current = first;
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
	public static void main(String[] args) {
		LinkBag<String> linkBag = new LinkBag<>();
		linkBag.add("a");
		linkBag.add("b");
		linkBag.add("c");
		linkBag.deleteNode(3);
		for (String string : linkBag) {
			System.out.println(string);
		}
	}
}
