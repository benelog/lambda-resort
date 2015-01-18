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
	public void modernJavaWithMethodReference(){
		assertImpl(new ModernJavaAdvancedResort(repository));
	}

	@Test
	public void bolts(){
		assertImpl(new BoltsResort(repository));
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

	@Test
	public void commonsCollections(){
		assertImpl(new CommonsCollectionsResort(repository));
	}
	
	@Test
	public void jedi(){
		assertImpl(new JediResort(repository));
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
