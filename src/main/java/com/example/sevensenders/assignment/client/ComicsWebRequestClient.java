package com.example.sevensenders.assignment.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ComicsWebRequestClient {

    @Autowired
    private RestTemplate restTemplate;


    public <T> T getResultFromUrl(String url, Class<T> clazz) {
        return restTemplate.getForObject(url, clazz);
    }
}
