package com.observer.pattern.scheduling.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public abstract class Observer<T> {
    private final String name;
    private final Class<T> responseType;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public Observer(String name, Class<T> responseType, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.name = name;
        this.responseType = responseType;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void process(){
        try {
            T t;
            //call to rest template
            String restTemplateResponse = "restTemplateResponse";
            t = objectMapper.readValue(restTemplateResponse, responseType);
            update(t);
        }catch (Exception e){
            //handle exception
        }
    }

    public abstract void update(T t);

    public String getName() {
        return name;
    }
}
