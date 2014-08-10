package com.naver.helloworld.resort.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class ClassicJavaService implements ReservationService {
	private GuestRepository repository;
	public ClassicJavaService(GuestRepository repository) {
		this.repository = repository;
	}

	public List<String> findGuestNamesOfCompany(String company) {
		List<Guest> all = repository.findAll();

		List<Guest> filtered = filter(all, company);
		sort(filtered);
		return mapNames(filtered);
	}
	
	private List<Guest> filter(List<Guest> guests, String company) {
		List<Guest> filtered = new  ArrayList<>();
		for(Guest guest : guests ) {
			if (company.equals(guest.getCompany())) {
				filtered.add(guest);
			}
		}
		return filtered;
	}

	private void sort(List<Guest> guests) {
		Collections.sort(guests, new Comparator<Guest>() {
			@Override
			public int compare(Guest o1, Guest o2) {
				return Integer.compare(o1.getGrade(), o2.getGrade());
			}
		});
	}

	private List<String> mapNames(List<Guest> guests) {
		List<String> names = new ArrayList<>();
		for(Guest guest : guests ) {
			names.add(guest.getName());
		}
		return names;
	}
}
