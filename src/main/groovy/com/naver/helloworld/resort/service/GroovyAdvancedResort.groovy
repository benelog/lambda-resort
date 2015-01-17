package com.naver.helloworld.resort.service

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

class GroovyAdvancedResort implements ResortService {
	private GuestRepository repository
	
	GroovyAdvancedResort(GuestRepository repository) {
		this.repository = repository
	}

	@Override
	List<String> findGuestNamesByCompany(String company) {
		List<Guest> all = repository.findAll()
		all.findAll { it.company == company }
			.sort { it.grade }
			.collect { it.name }
	}
}
