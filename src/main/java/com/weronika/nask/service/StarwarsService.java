package com.weronika.nask.service;

import com.weronika.nask.client.StarwarsClient;
import com.weronika.nask.model.StarwarsCharacter;
import org.springframework.stereotype.Service;

@Service
public class StarwarsService {
    private final StarwarsClient starwarsClient;

    public StarwarsService(StarwarsClient starwarsClient) {
        this.starwarsClient = starwarsClient;
    }

    public StarwarsCharacter getCharacter(String id){
        return starwarsClient.getCharacterById(id);
    }
}
