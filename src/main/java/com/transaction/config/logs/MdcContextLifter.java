package com.transaction.config.logs;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Hooks;
import reactor.util.context.Context;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class MdcContextLifter {

    private static final String MDC_CONTEXT_REACTOR_KEY = MdcContextLifter.class.getName();

    @PostConstruct
    public void contextOperatorHook() {
        Hooks.onEachOperator(MDC_CONTEXT_REACTOR_KEY,
            Operators.lift((scannable, subscriber) ->
                new MdcContextLifterSubscriber<>(subscriber)));
    }

    @PreDestroy
    public void cleanupHook() {
        Hooks.resetOnEachOperator(MDC_CONTEXT_REACTOR_KEY);
    }
}