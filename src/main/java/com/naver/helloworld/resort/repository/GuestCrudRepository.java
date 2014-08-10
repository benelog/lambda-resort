package com.naver.helloworld.resort.repository;

import org.springframework.data.repository.CrudRepository;

import com.naver.helloworld.resort.domain.Guest;

public interface GuestCrudRepository extends CrudRepository<Guest, Integer> {
}
