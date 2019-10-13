package com.belenot.eatfood.repository;

import java.util.Random;


import com.belenot.eatfood.domain.Client;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RandomClientFactory {

    private final int MAX_RANDOM = 10;

    public Client createRandomClient() {
        Client client = new Client();
        client.setLogin(RandomStringUtils.random(RandomUtils.nextInt(4, 10), true, false));
        client.setName(RandomStringUtils.random(RandomUtils.nextInt(4, 10), true, false));
        client.setPassword(RandomStringUtils.random(RandomUtils.nextInt(4, 10), true, false));
        return client;
    }
}