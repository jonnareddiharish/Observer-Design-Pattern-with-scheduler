package com.observer.pattern.scheduling.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.observer.pattern.scheduling.factory.ObservableScheduledFactory;
import com.observer.pattern.scheduling.service.FirstObserver;
import com.observer.pattern.scheduling.service.Observer;
import com.observer.pattern.scheduling.service.SecondObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    private ObservableScheduledFactory observableScheduledFactory;

    @Autowired
    public Controller(ObservableScheduledFactory observableScheduledFactory) {
        this.observableScheduledFactory = observableScheduledFactory;
    }

    @GetMapping("/health")
    private String healthCheck(){
        Observer<String> one = new FirstObserver<>("first",String.class,new RestTemplate(),new ObjectMapper());
        Observer<String> second = new SecondObserver<>("Second",String.class,new RestTemplate(),new ObjectMapper());
        observableScheduledFactory.addSubscriber(one);
        observableScheduledFactory.addSubscriber(second);
        return "success";
    }

}
