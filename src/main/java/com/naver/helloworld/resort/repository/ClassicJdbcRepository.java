package com.naver.helloworld.resort.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.naver.helloworld.resort.domain.Guest;

public class ClassicJdbcRepository implements GuestRepository {
	private JdbcTemplate jdbc;
	public ClassicJdbcRepository(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	private static final String SELECT_ALL = "SELECT name, grade, company FROM guest";

	@Override
	public void save(Guest... guests) {
		SimpleJdbcInsert insertStmt = new SimpleJdbcInsert(jdbc).withTableName("guest");
		for ( Guest guest: guests) {
			BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(guest);
			insertStmt.execute(params);
		}
	}
	
	public List<Guest> findAll() {
		return jdbc.query(SELECT_ALL, new RowMapper<Guest>(){
			@Override
			public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
				return  new Guest (
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("company"),
						rs.getInt("grade")
						);
			}

		});
	}
}
