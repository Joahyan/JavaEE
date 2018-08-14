package com.briup.net;

import java.io.Serializable;

public class Student implements Serializable{
	private int id;
	private String name;
	private int age;
	private String gender;
	private int score;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Student(int id, String name, int age, String gender, int score) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.score = score;
	}
	public Student() {
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", score=" + score
				+ "]";
	}
	
	

}
