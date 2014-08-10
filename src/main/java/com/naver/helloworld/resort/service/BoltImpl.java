package com.naver.helloworld.resort.service;

import java.util.Comparator;
import java.util.List;

import ru.yandex.bolts.collection.Cf;
import ru.yandex.bolts.collection.ListF;
import ru.yandex.bolts.function.Function;
import ru.yandex.bolts.function.Function1B;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class BoltImpl implements ReservationService {
	private GuestRepository repository;
	public BoltImpl(GuestRepository repository) {
		this.repository = repository;
	}

	public List<String> findGuestNamesOfCompany(final String company) {
		List<Guest> all = repository.findAll();
		// filter
		ListF<Guest> filtered = Cf.list(all)
			.filter(new Function1B<Guest>() {
				public boolean apply(Guest g) {
					return company.equals(g.getCompany());
				}
			});
		
		// sort
		filtered.sort(new Comparator<Guest>() {
			public int compare(Guest o1, Guest o2) {
				return Integer.compare(o1.getGrade(), o2.getGrade());
			}
		});
		
		// map
		ListF<String> names = filtered.map(new Function<Guest, String>() {
			public String apply(Guest g) {
				return g.getName();
			}
		});
		return names.toList();
	}
}
