package com.bezkoder.spring.hibernate.onetomany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Donation;
import com.bezkoder.spring.hibernate.onetomany.repository.DonationRepository;
import com.bezkoder.spring.hibernate.onetomany.repository.PersonRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class DonationController {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private DonationRepository donationRepository;

  @GetMapping("/persons/{personId}/donations")
  public ResponseEntity<List<Donation>> getAllDonationsByPersonId(@PathVariable(value = "personId") Long personId) {
    if (!personRepository.existsById(personId)) {
      throw new ResourceNotFoundException("Not found Person with id = " + personId);
    }

    List<Donation> donations = donationRepository.findByPersonId(personId);
    return new ResponseEntity<>(donations, HttpStatus.OK);
  }

  @GetMapping("/donations/{id}")
  public ResponseEntity<Donation> getDonationsByPersonId(@PathVariable(value = "id") Long id) {
    Donation donation = donationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found donation with id = " + id));

    return new ResponseEntity<>(donation, HttpStatus.OK);
  }

  @PostMapping("/persons/{personId}/donations")
  public ResponseEntity<Donation> createDonation(@PathVariable(value = "personId") Long personId,
      @RequestBody Donation donationRequest) {
    Donation donation = personRepository.findById(personId).map(person -> {
      donationRequest.setPerson(person);
      return donationRepository.save(donationRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Person with id = " + personId));

    return new ResponseEntity<>(donation, HttpStatus.CREATED);
  }

  @PutMapping("/donations/{id}")
  public ResponseEntity<Donation> updateDonation(@PathVariable("id") long id, @RequestBody Donation donationRequest) {
    Donation donation = donationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("DonationId" + id + "not found"));

    donation.setDonation_type(donationRequest.getDonation_type());
    donation.setAmount(donationRequest.getAmount());
    donation.setDonation_desc(donationRequest.getDonation_desc());
    

    return new ResponseEntity<>(donationRepository.save(donation), HttpStatus.OK);
  }

  @DeleteMapping("/donations/{id}")
  public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") long id) {
    donationRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/persons/{personId}/donations")
  public ResponseEntity<List<Donation>> deleteAllDonationsOfPerson(@PathVariable(value = "personId") Long personId) {
    if (!personRepository.existsById(personId)) {
      throw new ResourceNotFoundException("Not found Person with id = " + personId);
    }

    donationRepository.deleteByPersonId(personId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
