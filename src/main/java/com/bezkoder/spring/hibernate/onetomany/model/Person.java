package com.bezkoder.spring.hibernate.onetomany.model;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "phno")
  private long phno;

  @Column(name = "pincode")
  private long pincode;
  
  @Column(name = "address")
  private String address;
  
  @Column(name = "city")
  private String city;
  
  @Column(name = "state")
  private String state;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public long getPhno() {
	return phno;
}

public void setPhno(long phno) {
	this.phno = phno;
}

public long getPincode() {
	return pincode;
}

public void setPincode(long pincode) {
	this.pincode = pincode;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public Person(String name, String email, long phno, long pincode, String address, String city, String state) {
	
	this.name = name;
	this.email = email;
	this.phno = phno;
	this.pincode = pincode;
	this.address = address;
	this.city = city;
	this.state = state;
}

public Person() {
	
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Person [id=" + id + ", name=" + name + ", email=" + email + ", phno=" + phno + ", pincode=" + pincode
			+ ", address=" + address + ", city=" + city + ", state=" + state + "]";
}


}
