package com.belenot.eatfood.test.extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.belenot.eatfood.TestUtil;
import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class RandomDomainResolver implements ParameterResolver {

    @Retention( RetentionPolicy.RUNTIME )
    @Target( ElementType.PARAMETER )
    public @interface RandomDomain { }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
	throws ParameterResolutionException {
        return parameterContext.isAnnotated(RandomDomain.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
	throws ParameterResolutionException {
        return randomDomain(parameterContext.getParameter().getType());
    }

    private Object randomDomain(Class<?> type) {
	if (type.equals(Client.class)) {
	    return TestUtil.randomClient();
	}
	if (type.equals(Food.class)) {
	    return TestUtil.randomFood();
	}
	if (type.equals(Dose.class)) {
	    return TestUtil.randomDose();
	}
	throw new ParameterResolutionException("No such domain, or random generator not implemented for this domain");
    }
}
