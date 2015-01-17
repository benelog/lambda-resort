package com.naver.helloworld.resort.service

import com.naver.helloworld.resort.repository.GuestRepository
import java.util.Comparator
import com.naver.helloworld.resort.domain.Guest
import java.util.stream.Collectors

public class KotlinAdvancedResort (private val repository: GuestRepository) : ResortService {
	override fun findGuestNamesByCompany(company: String): List<String> {
		val all = repository.findAll()
		return all.filter { it.getCompany() == company }
				.sortBy { it.getGrade() }
				.map { it.getName() }
    }
}