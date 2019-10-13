package com.belenot.eatfood.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class FoodRepositoryMock implements FoodRepository {
    private List<Food> foods = new ArrayList<>();
    private Long id = 1L;

    @Override
    public List<Food> findByClient(Client client) {
        return foods.stream().filter(food->food.getClient()!=null&&food.getClient().getId()==client.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Food> findByNameAndClient(String name, Client client) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteInBatch(Iterable<Food> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Food> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Food> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Food> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Food> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Food> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public Food getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Food> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Food> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Food> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Food entity) {
        foods.removeIf(food->food.getId()==entity.getId());

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends Food> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<Food> findById(Long id) {
        return foods.stream().filter(food->food.getId()==id).findFirst();
    }

    @Override
    public <S extends Food> S save(S entity) {
        entity.setId(id++);
        foods.add(entity);
        return entity;
    }

    @Override
    public <S extends Food> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Food> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends Food> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Food> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }
}