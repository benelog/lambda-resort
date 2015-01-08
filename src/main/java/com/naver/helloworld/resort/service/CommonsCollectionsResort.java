package com.naver.helloworld.resort.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class CommonsCollectionsResort implements ResortService {

	private GuestRepository repository;

	public CommonsCollectionsResort(GuestRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<String> findGuestNamesOfCompany(final String company) {
		List<Guest> all = repository.findAll();
		List<Guest> filtered = ListUtils.select(all, new Predicate<Guest>() {
			public boolean evaluate(Guest g) {
				return company.equals(g.getCompany());
			}
		});
		
		Collections.sort(filtered, new Comparator<Guest>() {
			@Override
			public int compare(Guest o1, Guest o2) {
				return Integer.compare(o1.getGrade(), o2.getGrade());
			}
		});

		Collection<String> names = CollectionUtils.collect(filtered, new Transformer<Guest, String>(){
			public String transform(Guest g) {
				return g.getName();
			}
		});
		
		return new ArrayList<>(names);
	}

}
