package com.naver.helloworld.resort.service

import com.naver.helloworld.resort.repository.GuestRepository

import scala.collection.JavaConversions._

class ScalaAdvancedResort(repository: GuestRepository) extends ResortService {
	override def findGuestNamesByCompany(company: String): java.util.List[String] = {
		val all = repository.findAll
		all.filter ( _.getCompany == company)
			.sortBy ( _.getGrade )
			.map ( _.getName )
	}
}