package com.naver.helloworld.basiclambda;

import java.util.Arrays;
import java.util.List;

public class CustomFunctionReference {
	@FunctionalInterface
	public static interface StringAction {
	    void execute(String str);
//	    void anotherExecute(); // 주석을 제거하면 컴파일 에러
	}
	
	public static void main(String[] args) {
		List<String> alphabet = Arrays.asList("a","b","c");
		
		StringAction f1 = (String s) ->  System.out.println ("!" + s);
		StringAction f2 =  s ->  System.out.println ("!!" + s);
		StringAction f3 = System.out::println;

		doAction(alphabet, f1);
		doAction(alphabet, f2);
		doAction(alphabet, s ->  System.out.println ("!!!" + s));
		doAction(alphabet, f3);
		doAction(alphabet, System.out::println);
	}

	private static void doAction(List<String> list, StringAction action) {
		list.forEach(action::execute);
	}
}
