package com.bezkoder.spring.hibernate.onetomany.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "donations")
public class Donation {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donation_generator")
  private Long id;

  @Lob
  private String donation_type;
  
  @Lob
  private String amount;
  
  @Lob
  private String donation_desc;
  

  
  

//  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "person_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Person person;





public Long getId() {
	return id;
}





public void setId(Long id) {
	this.id = id;
}





public String getDonation_type() {
	return donation_type;
}





public void setDonation_type(String donation_type) {
	this.donation_type = donation_type;
}





public String getAmount() {
	return amount;
}





public void setAmount(String amount) {
	this.amount = amount;
}





public String getDonation_desc() {
	return donation_desc;
}





public void setDonation_desc(String donation_desc) {
	this.donation_desc = donation_desc;
}





public Person getPerson() {
	return person;
}





public void setPerson(Person person) {
	this.person = person;
}


}
