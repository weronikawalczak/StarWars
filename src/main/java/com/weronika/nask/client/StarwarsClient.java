package com.weronika.nask.client;

import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.Starship;
import com.weronika.nask.swapi.dto.CharacterDTO;
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

    public CharacterDTO getCharacterById(int id){
        String url = String.format("%s/people/%s/", swapiPath, id);
        return restTemplate.getForObject(url, CharacterDTO.class);
    }

    public Homeworld getHomeworld(String id){
        String url = String.format("%s/planets/%s/", swapiPath, id);
        return restTemplate.getForObject(url, Homeworld.class);
    }

    public Starship getStarship(String id){
        String url = String.format("%s/starships/%s/", swapiPath, id);
        return restTemplate.getForObject(url, Starship.class);
    }
}
