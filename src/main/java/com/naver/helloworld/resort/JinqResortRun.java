package com.naver.helloworld.resort;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.naver.helloworld.resort.service.JinqResort;
import com.naver.helloworld.resort.service.ResortService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class JinqResortRun {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JinqResortRun.class);
		ResortService service = context.getBean(JinqResort.class);
		List<String> names = service.findGuestNamesOfCompany("naver");
		System.out.println(names);
		context.close();
	}
}
