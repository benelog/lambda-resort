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
	@Autowired
	public JinqResort(EntityManager em) {
		this.em = em;
	}
	private EntityManager em;
	public List<String> findGuestNamesByCompany(String company) {
		return stream(Guest.class)
			.where(g -> g.getCompany().equals(company))
			.sortedBy(Guest::getGrade)
			.select(Guest::getName)
			.toList();
	}

	private <T> JinqStream<T> stream(Class<T> clazz) {
		return new JinqJPAStreamProvider(em.getEntityManagerFactory()).streamAll(em, clazz);
	}
}