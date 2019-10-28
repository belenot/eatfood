package com.belenot.eatfood.testutil.factory;

public interface RandomDomainFactory<T> {
    T generate();
}