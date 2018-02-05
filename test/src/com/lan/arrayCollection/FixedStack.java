package com.lan.arrayCollection;

import java.util.Iterator;

public class FixedStack<T> implements Iterable<T>{
	private T[] a;
	private int N;
	public FixedStack(int cap) {
		a = (T[]) new Object[cap];
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void push(T t) {
		a[N++] = t;
	}
	public T pop() {
		T t = a[--N];
		a[N] = null;
		return t;
	}
	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator<T>{
		private int i = N;
		private int j = N;
		@Override
		public boolean hasNext() {
			return j > 0;
		}

		@Override
		public T next() {
			T t = a[i-j];
			j--;
			return t;
		}
		
	}
}