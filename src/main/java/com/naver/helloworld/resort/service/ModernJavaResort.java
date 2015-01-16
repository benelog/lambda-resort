package com.naver.helloworld.resort.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;
public class ModernJavaResort implements ResortService {
	private GuestRepository repository;
	public ModernJavaResort(GuestRepository repository) {
		this.repository = repository;
	}

	public List<String> findGuestNamesByCompany(String company) {
		List<Guest> guests = repository.findAll();
		return guests.stream()
			.filter(g -> company.equals(g.getCompany()))
			.sorted(Comparator.comparing(Guest::getGrade))
			.map(Guest::getName)
			.collect(Collectors.toList());

		/*
		return guests.stream()
			.filter(g -> company.equals(g.getCompany()))
			.sorted(Comparator.comparing(g -> g.getGrade())
			.map(g -> g.getName())
			.collect(Collectors.toList());
		 */
	}
}