package com.naver.helloworld.resort.service

import com.naver.helloworld.resort.repository.GuestRepository
import java.util.Comparator
import com.naver.helloworld.resort.domain.Guest
import java.util.stream.Collectors

public class KotlinResort (private val repository: GuestRepository) : ResortService {
	override fun findGuestNamesOfCompany(company: String): List<String> {
		val all = repository.findAll()
		return all.filter { it.getCompany() == company }
			.sortBy { it.getGrade() }
			.map { it.getName() }
		/*
		return all.filter {g -> g.getCompany() == company }
					.sortBy {g -> g.getGrade() }
					.map {g -> g.getName() }		
		*/
    }
}