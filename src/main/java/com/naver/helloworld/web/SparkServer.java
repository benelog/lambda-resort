package com.naver.helloworld.web;

import static spark.Spark.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.naver.helloworld.resort.service.ResortService;

public class SparkServer {
	public static void main(String[] args) {
		get("/guest/company/:company", (request, response) -> {
			String company = request.params(":company");
			return "No guests from " + company;
		});
	}
}
