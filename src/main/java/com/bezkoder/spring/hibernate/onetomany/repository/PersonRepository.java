package com.bezkoder.spring.hibernate.onetomany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.hibernate.onetomany.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
  

  List<Person> findByNameContaining(String name);
}
