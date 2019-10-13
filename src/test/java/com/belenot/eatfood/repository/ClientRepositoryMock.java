package com.belenot.eatfood.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.belenot.eatfood.domain.Client;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ClientRepositoryMock implements ClientRepository {

    private List<Client> clients = new ArrayList<>();
    private Long id = 1L;

    @Override
    public Client findByLogin(String login) {
        return clients.stream().filter(client->client.getLogin().equals(login)).findFirst().orElse(null);
    }
    
    @Override
    public List<Client> findAll() {
        return clients;
    }

    @Override
    public void deleteAllInBatch() {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public void deleteInBatch(Iterable<Client> entities) {
        throw new IllegalStateException("Method not supperted");

    }

    @Override
    public List<Client> findAll(Sort sort) {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public <S extends Client> List<S> findAll(Example<S> example) {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public <S extends Client> List<S> findAll(Example<S> example, Sort sort) {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public List<Client> findAllById(Iterable<Long> ids) {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public Client getOne(Long id) {
        return clients.stream().filter(client->client.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public <S extends Client> List<S> saveAll(Iterable<S> entities) {
        throw new IllegalStateException("Method not supperted");    
    }

    @Override
    public <S extends Client> S saveAndFlush(S entity) {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public long count() {
        return clients.size();
    }

    @Override
    public void delete(Client entity) {
        clients.removeIf(client->client.getId().equals(entity.getId()));

    }

    @Override
    public void deleteAll() {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public void deleteAll(Iterable<? extends Client> entities) {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public void deleteById(Long id) {
        clients.removeIf(client->client.getId().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return clients.stream().anyMatch(client->client.getId().equals(id));
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clients.stream().filter(client->client.getId().equals(id)).findFirst();
    }

    @Override
    public <S extends Client> S save(S entity) {
        if (clients.add(entity)) {
            entity.setId(id++);
            return entity;
        }
        return null;
    }

    @Override
    public <S extends Client> long count(Example<S> example) {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public <S extends Client> boolean exists(Example<S> example) {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public <S extends Client> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new IllegalStateException("Method not supperted");
    }

    @Override
    public <S extends Client> Optional<S> findOne(Example<S> example) {
        throw new IllegalStateException("Method not supperted");
    }

}