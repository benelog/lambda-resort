package com.naver.helloworld.resort.service;

import static com.insightfullogic.lambdabehave.Suite.*;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;
import com.naver.helloworld.resort.repository.MemoryRepository;

@RunWith(JunitSuiteRunner.class)
public class ResortServiceSpec {{
	GuestRepository repository = new MemoryRepository();
	ResortService service = new ModernJavaResort(repository);
	
	describe("ResortService with modern Java", it -> {
		it.isSetupWith(() -> {
			repository.save(
					new Guest(1, "jsh", "naver", 15),
					new Guest(2, "hny", "daum", 10),
					new Guest(3, "chy", "naver", 5)
				);
		
		});
		it.isConcludedWith(repository::deleteAll);
		
		it.should("find names of guests by company ", expect -> {
			List<String> names = service.findGuestNamesByCompany("naver");			
			expect.that(names).isEqualTo(Arrays.asList("chy","jsh"));
		});
	});
}}
