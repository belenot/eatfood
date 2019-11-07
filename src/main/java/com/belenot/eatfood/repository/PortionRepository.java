package com.belenot.eatfood.repository;

import com.belenot.eatfood.domain.Portion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PortionRepository extends JpaRepository<Portion, Long> {
    
}