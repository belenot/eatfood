package com.belenot.eatfood.repository;

import com.belenot.eatfood.domain.Portion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortionRepository extends JpaRepository<Portion, Long> {
    
}