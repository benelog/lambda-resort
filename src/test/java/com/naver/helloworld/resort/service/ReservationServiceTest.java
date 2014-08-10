package com.naver.helloworld.resort.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.MemoryRepository;
import com.naver.helloworld.resort.repository.GuestRepository;

public class ReservationServiceTest  {
	GuestRepository repositry = new MemoryRepository();
	
	@Test
	public void classjava(){
		assertImpl(new ClassicJavaService(repositry));
	}

	@Test
	public void modernJava(){
		assertImpl(new ModernJavaService(repositry));
	}

	@Test
	public void bolt(){
		assertImpl(new BoltImpl(repositry));
	}	

	@Test
	public void op4j(){
		assertImpl(new Op4JService(repositry));
	}	

	@Test
	public void guava(){
		assertImpl(new GuavaService(repositry));
	}

	@Test
	public void lambdaj(){
		assertImpl(new LambdaJService(repositry));
	}

	@Test
	public void functionalJava(){
		assertImpl(new FunctionalJavaService(repositry));
	}
	
	private void assertImpl(ReservationService service) {
		repositry.save(
			new Guest(1, "jsh", "naver", 15),
			new Guest(2, "hny", "daum", 10),
			new Guest(3, "chy", "naver", 5)
		);
		
		List<String> names = service.findGuestNamesOfCompany("naver");
		assertThat(names).isEqualTo(Arrays.asList("chy","jsh"));
	}
}
