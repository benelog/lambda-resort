package com.naver.helloworld.resort.service;

import java.util.List;

import jedi.functional.Comparables;
import jedi.functional.Filter;
import jedi.functional.FunctionalPrimitives;
import jedi.functional.Functor;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class JediResort implements ResortService {

	private GuestRepository repository;

	public JediResort(GuestRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<String> findGuestNamesOfCompany(final String company) {
		List<Guest> all = repository.findAll();
		List<Guest> filtered = FunctionalPrimitives.select(all, new Filter<Guest>() {
			public Boolean execute(Guest g) {
				return company.equals(g.getCompany());
			}
		});
		List<Guest> sorted = Comparables.sort(filtered, new Functor<Guest, Integer>() {
			public Integer execute(Guest g) {
				return g.getGrade();
			}
		});
		return FunctionalPrimitives.map(sorted, new Functor<Guest, String>() {
			public String execute(Guest g) {
				return g.getName();
			}
			
		});
	}

}
