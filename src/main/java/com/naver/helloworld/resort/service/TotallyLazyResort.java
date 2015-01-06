package com.naver.helloworld.resort.service;

import java.util.List;

import com.googlecode.totallylazy.Callable1;
import com.googlecode.totallylazy.Predicate;
import com.googlecode.totallylazy.Sequences;
import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class TotallyLazyResort implements ResortService {
	private GuestRepository repository;
	public TotallyLazyResort(GuestRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<String> findGuestNamesOfCompany(String company) {
		List<Guest> all = repository.findAll();
		return Sequences.sequence(all)
			.filter(new Predicate<Guest>() {
				public boolean matches(Guest g) {
					return company.equals(g.getCompany());
				}
			})
			.sortBy(new Callable1<Guest, Integer>(){
				public Integer call(Guest g) {
					return g.getGrade();
				}
			})
			.map(new Callable1<Guest, String>(){
				public String call(Guest g) {
					return g.getName();
				}
			}).toList();
	}
}
