package com.naver.helloworld.resort.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.naver.helloworld.resort.domain.Guest;

public class ModernJdbcRepository implements GuestRepository {
	private JdbcTemplate jdbc;
	public ModernJdbcRepository(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	private static final String SELECT_ALL = "SELECT name, grade, company FROM guests";
	private List<Guest> savedGuest = new ArrayList<>();

	@Override
	public void save(Guest... guests) {
		savedGuest.addAll(Arrays.asList(guests));
	}
	
	public List<Guest> findAll() {
		return jdbc.query(SELECT_ALL, 
			(rs, rowNum) ->new Guest (
					rs.getInt("id"), 
					rs.getString("name"), 
					rs.getString("company"),
					rs.getInt("grade")
			)
		);
	}
}
