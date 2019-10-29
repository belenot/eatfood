package com.belenot.eatfood.repository;

import com.belenot.eatfood.domain.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByLogin(String login);
}