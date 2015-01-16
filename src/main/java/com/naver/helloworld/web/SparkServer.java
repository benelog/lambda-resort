package com.naver.helloworld.web;

import static spark.Spark.*;

public class SparkServer {
	public static void main(String[] args) {
		get("/guest/company/:company", (request, response) -> {
			String company = request.params(":company");
			return "No guests from " + company;
		});
	}
}
