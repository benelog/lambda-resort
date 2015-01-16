package com.naver.helloworld.resort;

public class SimpleLamba {
	public static void main(String[] args) {
		Runnable labmda= () -> System.out.println(1);
		labmda.run();
	}
}
