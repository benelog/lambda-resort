package com.naver.helloworld.resort.service;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Ordering;
import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class GuavaResort implements ResortService {
	private GuestRepository repository;
	public GuavaResort(GuestRepository repository) {
		this.repository = repository;
	}

	public List<String> findGuestNamesOfCompany(final String company) {
		List<Guest> all = repository.findAll();

		// filter and sort
		List<Guest> sorted = FluentIterable.from(all)
			.filter(new Predicate<Guest>() {
				public boolean apply(Guest g) {
					return company.equals(g.getCompany());
				}
			})
			.toSortedList(Ordering.natural()
				.onResultOf(new Function<Guest, Integer>() {
					 public Integer apply(Guest g) {
						 return g.getGrade();
					 }
			}));
		
		 return FluentIterable.from(sorted).transform(new Function<Guest, String>() {
			 public String apply(Guest g) {
				return g.getName();
			}
		}).toList();
	}
}
