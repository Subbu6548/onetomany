package com.bezkoder.spring.hibernate.onetomany.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.hibernate.onetomany.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
  List<Donation> findByPersonId(Long postId);
  
  @Transactional
  void deleteByPersonId(long doctorId);
}
