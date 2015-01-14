package com.naver.helloworld.resort.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.helloworld.resort.domain.Guest;

@Repository
public class JinqResort implements ResortService {
	private EntityManager em;
	@Autowired
	public JinqResort(EntityManager em) {
		this.em = em;
	}
	public List<String> findGuestNamesOfCompany(String company) {
		return guests()
			.where(g -> g.getCompany().equals(company))
			.sortedByIntAscending(Guest::getGrade)
			.select(Guest::getName)
			.toList();
	}

	private JinqStream<Guest> guests() {
		return new JinqJPAStreamProvider(em.getEntityManagerFactory()).streamAll(em, Guest.class);
	}
}