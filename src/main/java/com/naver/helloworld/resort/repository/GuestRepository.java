package com.naver.helloworld.resort.repository;

import java.util.List;

import com.naver.helloworld.resort.domain.Guest;

public interface GuestRepository {
	public void save(Guest ... guest);
	public List<Guest> findAll ();
}
