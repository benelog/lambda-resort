package com.naver.helloworld.resort.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;
import com.naver.helloworld.resort.repository.MemoryRepository;

public class KotlinResortTest  {
	GuestRepository repository = new MemoryRepository();

	@Test
	public void kotlin(){
		assertImpl(new KotlinResort(repository));
	}

	@Test
	public void kotlinWithIt(){
		assertImpl(new KotlinAdvancedResort(repository));
	}
	
	private void assertImpl(ResortService service) {
		repository.save(
			new Guest(1, "jsh", "naver", 15),
			new Guest(2, "hny", "daum", 10),
			new Guest(3, "chy", "naver", 5)
		);
		
		List<String> names = service.findGuestNamesByCompany("naver");
		assertThat(names).isEqualTo(Arrays.asList("chy","jsh"));
	}
}
