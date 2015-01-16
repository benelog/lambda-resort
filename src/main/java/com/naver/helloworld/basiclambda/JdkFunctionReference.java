package com.naver.helloworld.basiclambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class JdkFunctionReference {
	public static void main(String[] args) {
		List<String> alphabet = Arrays.asList("a","b","c");

		alphabet.forEach(System.out::println);

		Consumer<String> jdkFunc = System.out::println;
		alphabet.forEach(jdkFunc);
	}
}
