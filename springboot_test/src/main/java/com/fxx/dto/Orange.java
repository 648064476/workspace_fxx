package com.fxx.dto;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class Orange implements Condition {
    @Override
    public boolean matches (ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}
