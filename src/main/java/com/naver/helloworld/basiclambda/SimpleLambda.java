package com.naver.helloworld.basiclambda;

public class SimpleLambda {
	public static void main(String[] args) {
		Runnable labmda= () -> System.out.println(1);
		labmda.run();
	}
}
