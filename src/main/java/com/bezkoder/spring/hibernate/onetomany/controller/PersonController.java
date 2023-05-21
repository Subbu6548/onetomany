package com.bezkoder.spring.hibernate.onetomany.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Person;
import com.bezkoder.spring.hibernate.onetomany.repository.PersonRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PersonController {

  @Autowired
  PersonRepository personRepository;

  @GetMapping("/persons")
  public ResponseEntity<List<Person>> getAllPersons(@RequestParam(required = false) String name) {
    List<Person> persons = new ArrayList<Person>();

    if (name == null)
      personRepository.findAll().forEach(persons::add);
    else
      personRepository.findByNameContaining(name).forEach(persons::add);

    if (persons.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(persons, HttpStatus.OK);
  }

  @GetMapping("/persons/{id}")
  public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
    Person person = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Person with id = " + id));

    return new ResponseEntity<>(person, HttpStatus.OK);
  }

  @PostMapping(path="/persons")
  public ResponseEntity<Person> createPerson(@RequestBody Person person) {
    Person _person = personRepository.save(new Person(person.getName(),person.getEmail(),person.getPhno(),person.getPincode(),person.getAddress(),person.getCity(),person.getState()));
    return new ResponseEntity<>(_person, HttpStatus.CREATED);
  }

  @PutMapping(path="/persons/{id}")
  public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
    Person _person = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Person with id = " + id));

    _person.setName(person.getName());
    _person.setEmail(person.getEmail());
    _person.setPhno(person.getPhno());
    _person.setPincode(person.getPincode());
    _person.setAddress(person.getAddress());
    _person.setCity(person.getCity());
    _person.setState(person.getState());
    
    
    return new ResponseEntity<>(personRepository.save(_person), HttpStatus.OK);
  }

  @DeleteMapping("/persons/{id}")
  public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id) {
    personRepository.deleteById(id);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/persons")
  public ResponseEntity<HttpStatus> deleteAllPersons() {
    personRepository.deleteAll();
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
