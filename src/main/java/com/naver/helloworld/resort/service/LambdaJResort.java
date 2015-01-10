package com.naver.helloworld.resort.service;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import ch.lambdaj.collection.LambdaCollections;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class LambdaJResort implements ResortService {
	private GuestRepository repository;
	public LambdaJResort(GuestRepository repository) {
		this.repository = repository;
	}

	public List<String> findGuestNamesOfCompany(final String company) {
		List<Guest> all = repository.findAll();
		return LambdaCollections.with(all)
			.retain(having(on(Guest.class).getCompany(), equalTo(company)))
			.sort(on(Guest.class).getGrade())
			.extract(on(Guest.class).getName());
			// last line has a same effect with 
			//'.convert(new PropertyExtractor<Guest, String>("name"));'
	}
}
