package com.carcompare.license;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class FileRSAKeyCondition implements Condition {
    static final String TYPE = "file";

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String value = conditionContext.getEnvironment().getProperty("custom.license.key.provider");
        return value.equals(TYPE);
    }
}
