package com.belenot.eatfood.repository;

import com.belenot.eatfood.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}