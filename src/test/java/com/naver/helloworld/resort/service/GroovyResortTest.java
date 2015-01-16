package com.naver.helloworld.resort.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;
import com.naver.helloworld.resort.repository.MemoryRepository;

public class GroovyResortTest  {
	GuestRepository repository = new MemoryRepository();

	@Test
	public void groovy(){
		assertImpl(new GroovyResort(repository));
	}
	
	private void assertImpl(ResortService service) {
		repository.save(
			new Guest(1, "jsh", "Naver", 15),
			new Guest(2, "hny", "Line", 10),
			new Guest(3, "chy", "Naver", 5)
		);
		
		List<String> names = service.findGuestNamesByCompany("Naver");
		assertThat(names).isEqualTo(Arrays.asList("chy","jsh"));
	}
}
