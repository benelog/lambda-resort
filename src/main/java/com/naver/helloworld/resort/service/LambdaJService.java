package com.naver.helloworld.resort.service;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import ch.lambdaj.function.convert.PropertyExtractor;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class LambdaJService implements ReservationService {
	private GuestRepository repository;
	public LambdaJService(GuestRepository repository) {
		this.repository = repository;
	}

	public List<String> findGuestNamesOfCompany(String company) {
		List<Guest> all = repository.findAll();
		List<Guest> filtered = filter(having(on(Guest.class).getCompany(), equalTo(company)), all);
		List<Guest> sorted = sort(filtered, on(Guest.class).getGrade());
		return convert(sorted, new PropertyExtractor<Guest, String>("name"));
	}
}
