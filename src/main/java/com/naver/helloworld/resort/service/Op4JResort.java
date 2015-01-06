package com.naver.helloworld.resort.service;

import java.util.List;

import org.op4j.Op;
import org.op4j.functions.ExecCtx;
import org.op4j.functions.IFunction;

import com.naver.helloworld.resort.domain.Guest;
import com.naver.helloworld.resort.repository.GuestRepository;

public class Op4JResort implements ResortService {
	private GuestRepository repository;
	public Op4JResort(GuestRepository repository) {
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
			.sortBy(new IFunction<Guest, Integer>() {
				public Integer execute(Guest g, ExecCtx ctx) throws Exception {
					return g.getGrade();
				}
			})
			.map(new IFunction<Guest, String>() {
				public String execute(Guest g, ExecCtx ctx) throws Exception {
					return g.getName();
				}
			}).get();
	}
}
