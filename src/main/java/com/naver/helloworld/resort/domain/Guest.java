package com.naver.helloworld.resort.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author sanghyk.jung
 *
 */
@Entity
public class Guest implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer grade;
	private String name;
	private String company;

	public Guest(){}
	public Guest(Integer id, String name, String company, Integer grade) {
		this.id = id;
		this.grade = grade;
		this.name = name;
		this.company = company;
	}
	public String getCompany() {
		return company;
	}
	public String getName() {
		return name;
	}
	public Integer getGrade() {
		return grade;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "Guest [id=" + id + ", grade=" + grade + ", name=" + name
				+ ", company=" + company + "]";
	}
}
