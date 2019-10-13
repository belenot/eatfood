package com.belenot.eatfood.testutil;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.repository.RandomClientFactory;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;



public class EatfoodParameterResolver implements ParameterResolver {

    private RandomClientFactory clientFactory;

    public EatfoodParameterResolver() {
        clientFactory = new RandomClientFactory();
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        if (parameterContext.getParameter().getType().equals(Client.class)) {
            return clientFactory.createRandomClient();
        }
        return null;
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        if (parameterContext.getParameter().getType().equals(Client.class)) {
            return true;
        }
        return false;
    }
    
}