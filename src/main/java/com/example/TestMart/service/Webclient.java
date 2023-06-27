package com.example.TestMart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class Webclient {


    private final WebClient client;


    public Webclient(WebClient.Builder builder) {
        this.client = WebClient.builder().baseUrl("https://dummyjson.com").build();
    }

    public WebClient getClient() {
        return client;
    }
}
