package com.naver.helloworld.resort;

public class ThisDifference {
	public static void main(String[] args) {
		new ThisDifference().print();
		
	}
	public void print() {
		Runnable anonClass = new Runnable(){
			@Override
			public void run() {
				verifyRunnable(this);
			}
		};
		
		anonClass.run();
		
		Runnable labmda= () -> verifyRunnable(this);
		labmda.run();
	}
	
	private void verifyRunnable(Object obj) {
		System.out.println(obj instanceof Runnable);
	}
}
