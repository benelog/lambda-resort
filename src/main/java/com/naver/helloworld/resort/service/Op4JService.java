package com.naver.helloworld.resort.service;

import java.util.Comparator;
import java.util.List;

import org.op4j.Op;
import org.op4j.functions.ExecCtx;
import org.op4j.functions.IFunction;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class Op4JService implements ReservationService {
	private GuestRepository repository;
	public Op4JService(GuestRepository repository) {
		this.repository = repository;
	}

	public List<String> findGuestNamesOfCompany(final String company) {
		List<Guest> all = repository.findAll();
		return Op.on(all)
			.removeAllFalse(new IFunction<Guest, Boolean>() {
				public Boolean execute(Guest g, ExecCtx ctx) throws Exception {
					return company.equals(g.getCompany());
				}
			})
			.sort(new Comparator<Guest>() {
				public int compare(Guest o1, Guest o2) {
					return Integer.compare(o1.getGrade(), o2.getGrade());
				}
			})
			.map(new IFunction<Guest, String>() {
				public String execute(Guest g, ExecCtx ctx) throws Exception {
					return g.getName();
				}
			}).get();
		
	}
}
