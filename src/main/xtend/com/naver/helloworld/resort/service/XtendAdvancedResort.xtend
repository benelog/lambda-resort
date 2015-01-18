package com.naver.helloworld.resort.service

import com.naver.helloworld.resort.repository.GuestRepository

class XtendAdvancedResort implements ResortService {
	GuestRepository repository
	new (GuestRepository repository) {
		this.repository = repository
	}
	override findGuestNamesByCompany(String aCompany) {
		val all = repository.findAll()
		
		all.filter [company == aCompany]
			.sortBy[grade]
			.map[name]
	}
}
