package com.main.java;

public class JavaMain {
	
	public static void testArray(int[] what) {
		if(what.length > 0) {
			System.out.println("one");
		}
		
	}
	
	public static void ha123(String[] ra) {
		if(ra.length > 0) {
			System.out.println("sdaasd");
		}
	}
	
	public static void walksjd() {
		String[] temp = new String[2];
		hao123(temp);
	}
	
	
	public static void hao123(String ...strings) {
		if(strings.length > 0) {
			System.out.println("asdkfasdf");
		}
	}
	
	public static void testClass(Object obj) {
		System.out.println(obj);
	}

}
