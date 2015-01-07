package com.naver.helloworld.resort.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;
import com.naver.helloworld.resort.repository.MemoryRepository;

public class JavaResortTest  {
	GuestRepository repository = new MemoryRepository();
	
	@Test
	public void classjava(){
		assertImpl(new ClassicJavaResort(repository));
	}

	@Test
	public void modernJava(){
		assertImpl(new ModernJavaResort(repository));
	}

	@Test
	public void bolt(){
		assertImpl(new BoltResort(repository));
	}	

	@Test
	public void op4j(){
		assertImpl(new Op4JResort(repository));
	}	

	@Test
	public void guava(){
		assertImpl(new GuavaResort(repository));
	}

	@Test
	public void lambdaj(){
		assertImpl(new LambdaJResort(repository));
	}

	@Test
	public void functionalJava(){
		assertImpl(new FunctionalJavaResort(repository));
	}
	
	@Test
	public void totallyLazy(){
		assertImpl(new TotallyLazyResort(repository));
	}

	@Test
	public void gsCollections(){
		assertImpl(new GsCollectionsResort(repository));
	}
	
	private void assertImpl(ResortService service) {
		repository.save(
			new Guest(1, "jsh", "naver", 15),
			new Guest(2, "hny", "daum", 10),
			new Guest(3, "chy", "naver", 5)
		);
		
		List<String> names = service.findGuestNamesOfCompany("naver");
		assertThat(names).isEqualTo(Arrays.asList("chy","jsh"));
	}
}
