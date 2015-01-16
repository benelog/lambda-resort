package com.naver.helloworld.resort;

import static spark.Spark.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.naver.helloworld.resort.service.ResortService;

@SpringBootApplication
public class ResortServer {
	@Autowired
	private ResortService service;

	public void start() {
		get("/guest/company/:company", (request, response) -> {
			String company = request.params(":company");
			List<String> names = service.findGuestNamesByCompany(company);
			return "Guests from " + company + " : " + names;
		});
	}
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ResortServer.class);
		context.getBean(ResortServer.class).start();
	}
}
