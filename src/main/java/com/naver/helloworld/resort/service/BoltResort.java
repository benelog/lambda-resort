package com.naver.helloworld.resort.service;

import java.util.List;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.function.Function;
import ru.yandex.bolts.function.Function1B;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class BoltResort implements ResortService {
	private GuestRepository repository;
	public BoltResort(GuestRepository repository) {
		this.repository = repository;
	}

	public List<String> findGuestNamesOfCompany(final String company) {
		List<Guest> all = repository.findAll();
		return Cf.list(all)
			.filter(new Function1B<Guest>() {
				public boolean apply(Guest g) {
					return company.equals(g.getCompany());
				}
			})
			.sortBy(new Function<Guest, Integer>() {
				public Integer apply(Guest g) {
					return g.getGrade();
				}
			})
			.map(new Function<Guest, String>() {
				public String apply(Guest g) {
					return g.getName();
				}
		});
	}
}
