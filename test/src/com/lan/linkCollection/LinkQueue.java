package com.lan.linkCollection;

public class LinkQueue<T> {
	public Node first;
	public Node last;
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
	
	//入列
	public void enqueue(T t) {
		Node oldLast = last;
		last = new Node();
		last.item = t;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldLast.next = last;
		}
		N++;
	}
	//出列
	public T dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("LinkQueue is empty");
		}
		T item = first.item;
		first = first.next;
		if (N == 1) {
			last = first;
		}
		N--;
		return item;
	}
	
	public static void main(String[] args) {
		LinkQueue<String> linkQueue = new LinkQueue<>();
//		linkQueue.enqueue("a");
//		linkQueue.enqueue("b");
		System.out.println(linkQueue.size());
		System.out.println(linkQueue.dequeue());
		System.out.println(linkQueue.last);
		System.out.println(linkQueue.size());
	}
}
