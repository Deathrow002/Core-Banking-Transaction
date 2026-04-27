package com.transaction.config.webflux;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Component
public class TraceIdFilter implements WebFilter {

    public static final String TRACE_ID = "traceId";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String traceId = exchange.getRequest().getHeaders()
            .getFirst("X-Trace-Id");                       // accept from upstream
        if (traceId == null) {
            traceId = UUID.randomUUID().toString();        // generate if not provided
        }

        final String finalTraceId = traceId;
        exchange.getResponse().getHeaders().add("X-Trace-Id", finalTraceId);

        return chain.filter(exchange)
            .contextWrite(ctx -> ctx.put(TRACE_ID, finalTraceId));  // inject into Reactor Context
    }
}