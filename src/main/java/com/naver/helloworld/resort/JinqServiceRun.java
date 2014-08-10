package com.naver.helloworld.resort;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.naver.helloworld.resort.service.JinqService;
import com.naver.helloworld.resort.service.ReservationService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class JinqServiceRun {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JinqServiceRun.class);
		ReservationService service = context.getBean(JinqService.class);
		List<String> names = service.findGuestNamesOfCompany("naver");
		System.out.println(names);
		context.close();
	}
}
