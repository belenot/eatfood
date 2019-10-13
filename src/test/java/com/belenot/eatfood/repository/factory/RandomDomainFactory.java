package com.belenot.eatfood.repository.factory;

public interface RandomDomainFactory<T> {
    T generate();
}