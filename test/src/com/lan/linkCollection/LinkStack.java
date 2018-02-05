package com.lan.linkCollection;

import java.util.Iterator;

public class LinkStack<T> implements Iterable<T> {
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
	public void push(T t) {
		Node oldFirst = first;
		first = new Node();
		first.item = t;
		first.next = oldFirst;
		N++;
	}
	public T pop() {
		T item = first.item;
		first = first.next;
		N--;
		return item;
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
		LinkStack<String> linkStack = new LinkStack<>();
		linkStack.push("a");
		linkStack.push("b");
		linkStack.push("c");
		for (String string : linkStack) {
			System.out.println(string);
		}
	}
}
