package com.observer.pattern.scheduling.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


public class FirstObserver<T> extends Observer<T>{
    private static final Logger logger = LoggerFactory.getLogger(FirstObserver.class);

    public FirstObserver(String name, Class<T> responseType, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(name, responseType, restTemplate, objectMapper);
    }

    @Override
    public void update(T t) {
        logger.info("First Observer Data: {}",t);
    }
}
