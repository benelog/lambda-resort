package com.naver.helloworld.resort.service;

import java.util.List;

import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.impl.list.mutable.FastList;
import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class GsCollectionsResort implements ResortService {
	private GuestRepository repository;

	public GsCollectionsResort(GuestRepository repository) {
		this.repository = repository;
	}

	@SuppressWarnings("serial")
	@Override
	public List<String> findGuestNamesByCompany(final String company) {
		List<Guest> all = repository.findAll();
		return FastList.newList(all)
			.select(new Predicate<Guest>() {
				public boolean accept(Guest g) {
					return company.equals(g.getCompany());
				}
			})
			.sortThisBy(new Function<Guest, Integer>() {
				public Integer valueOf(Guest g) {
					return g.getGrade();
				}
			})
			.collect(new Function<Guest, String> () {
				public String valueOf(Guest g) {
					return g.getName();
				}
			});
		
	}

}
