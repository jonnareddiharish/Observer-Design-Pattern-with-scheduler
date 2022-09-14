package com.observer.pattern.scheduling.factory;

import com.observer.pattern.scheduling.service.Observer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@EnableScheduling
@ConditionalOnProperty(name = "myapp.scheduling.enable",havingValue = "true")
public class ObservableScheduledFactory {
    private final Map<String, Observer> observerList;


    public ObservableScheduledFactory() {
        this.observerList = new ConcurrentHashMap<>();
    }

    public void addSubscriber(Observer observer){
        observerList.put(observer.getName(),observer);
    }

    public void removeSubscriber(Observer observer){
        observerList.remove(observer.getName(),observer);
    }

    @Scheduled(fixedDelayString = "${myapp.scheduling.timeInMillis}")
    public void schedule(){
        System.out.println("scheduler started");
        observerList.values()
                .stream()
                .forEach(e->{
                    e.process();
                });
    }

}
