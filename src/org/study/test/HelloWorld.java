package org.study.test;

public class HelloWorld {
	
	
	
	public static void main(String[] args) {
		Object[] items = new Object[10];
		Integer i = 0;
		do {
			items[i++] = i;
		}while(i<10);
		
		items[0] = null;
		
		System.out.println(items.toString());

	}
}
