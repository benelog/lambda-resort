package com.naver.helloworld.resort.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.naver.helloworld.resort.domain.Guest;

public class MemoryRepository implements GuestRepository {
	
	private List<Guest> savedGuest = new ArrayList<>();

	@Override
	public void save(Guest... guests) {
		savedGuest.addAll(Arrays.asList(guests));
	}
	
	public List<Guest> findAll() {
		return new ArrayList<Guest>(savedGuest);
	}
}
