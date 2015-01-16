package com.naver.helloworld.resort;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.naver.helloworld.resort.service.JinqResort;
import com.naver.helloworld.resort.service.ResortService;

@SpringBootApplication
public class JinqResortRun {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JinqResortRun.class);
		ResortService service = context.getBean(JinqResort.class);
		List<String> names = service.findGuestNamesOfCompany("naver");
		System.out.println(names);
		context.close();
	}
}
