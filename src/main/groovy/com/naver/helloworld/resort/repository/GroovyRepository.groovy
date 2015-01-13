package com.naver.helloworld.resort.repository

import java.util.List

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.naver.helloworld.resort.domain.Guest;

class GroovyRepository implements GuestRepository {

	private JdbcTemplate jdbc;
	
	GroovyRepository(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}
	@Override
	public void save(Guest... guests) {
		SimpleJdbcInsert insertStmt = new SimpleJdbcInsert(jdbc).withTableName("guest")
		for ( Guest guest: guests) {
			BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(guest);
			insertStmt.execute(params);
		}
	}

	@Override
	public List<Guest> findAll() {
		jdbc.query(SELECT_ALL, { rs, rowNum -> 
				new Guest (
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("company"),
					rs.getInt("grade") 
				)
			}
		)
	}
}
