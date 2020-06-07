package com.weronika.nask.client;

import com.weronika.nask.model.StarwarsCharacter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StarwarsClient {
    private final RestTemplate restTemplate;
    private final String swapiPath;

    public StarwarsClient(RestTemplate restTemplate,
                          @Value("${swapi.path}") String swapiPath) {
        this.restTemplate = restTemplate;
        this.swapiPath = swapiPath;
    }

    public StarwarsCharacter getCharacterById(String id){
        String url = String.format("%s/people/%s/", swapiPath, id);
        return restTemplate.getForObject(url, StarwarsCharacter.class);
    }
}
