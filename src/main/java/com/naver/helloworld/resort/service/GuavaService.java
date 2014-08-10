package com.naver.helloworld.resort.service;

import java.util.Comparator;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class GuavaService implements ReservationService {
	private GuestRepository repository;
	public GuavaService(GuestRepository repository) {
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
			.toSortedList(new Comparator<Guest>() {
					public int compare(Guest o1, Guest o2) {
						return Integer.compare(o1.getGrade(), o2.getGrade());
					}
			});
		 	
		 return FluentIterable.from(sorted).transform(new Function<Guest, String>() {
			 public String apply(Guest g) {
				return g.getName();
			}
		}).toList();
	}
}
