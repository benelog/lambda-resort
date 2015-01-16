package com.naver.helloworld.resort;

import java.util.function.IntUnaryOperator;

public class FunctionParameterExam {
	public static void main(String[] args) {
		FunctionParameterExam printer = new FunctionParameterExam();
		int base = 7;
		printer.printWeighted(weight -> base * weight, 10);
	}
	public void printWeighted(IntUnaryOperator calc, int weight) {
		System.out.print(calc.applyAsInt(weight));
	}
	/*
	public void print( #int(int) calc, int weight) {
		System.out.print(calc.(weight));
	}
	*/

}
