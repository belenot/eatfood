package com.belenot.eatfood.testutil;

import java.util.HashMap;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.testutil.factory.RandomClientFactory;
import com.belenot.eatfood.testutil.factory.RandomDomainFactory;
import com.belenot.eatfood.testutil.factory.RandomFoodFactory;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;



public class EatfoodParameterResolver implements ParameterResolver {

    private Map<Class<?>, RandomDomainFactory<?>> factories = new HashMap<>();

    public EatfoodParameterResolver() {
        factories.put(Client.class, new RandomClientFactory());
        factories.put(Food.class, new RandomFoodFactory());
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        RandomDomainFactory<?> factory = factories.get(parameterContext.getParameter().getType());
        if (factory != null) {
            return factory.generate();
        }
        return null;
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        if (factories.keySet().contains(parameterContext.getParameter().getType())) {
            return true;
        }
        return false;
    }
    
}