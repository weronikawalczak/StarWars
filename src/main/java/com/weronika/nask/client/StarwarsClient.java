package com.weronika.nask.client;

import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.Starship;
import com.weronika.nask.swapi.dto.CharacterDTO;
import com.weronika.nask.swapi.dto.CharactersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StarwarsClient {
    private final RestTemplate restTemplate;
    private final String swapiPath;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public StarwarsClient(RestTemplate restTemplate,
                          @Value("${swapi.path}") String swapiPath) {
        this.restTemplate = restTemplate;
        this.swapiPath = swapiPath;
    }

    @Cacheable("character")
    public CharacterDTO getCharacterById(int id){
        String url = String.format("%s/people/%s/", swapiPath, id);
        logger.debug(String.format("Fetching character from Swapi with url: %s", url));
        return restTemplate.getForObject(url, CharacterDTO.class);
    }

    @Cacheable("characters")
    public CharactersDTO getCharactersByPage(int page){
        String url = String.format("%s/people/?page=%s", swapiPath, page);
        logger.debug(String.format("Fetching characters from Swapi with url: %s", url));
        return restTemplate.getForObject(url, CharactersDTO.class);
    }

    @Cacheable("homeworld")
    public Homeworld getHomeworld(String id){
        String url = String.format("%s/planets/%s/", swapiPath, id);
        logger.debug(String.format("Fetching homeworld from Swapi with url: %s", url));
        return restTemplate.getForObject(url, Homeworld.class);
    }

    @Cacheable("starship")
    public Starship getStarship(String id){
        String url = String.format("%s/starships/%s/", swapiPath, id);
        logger.debug(String.format("Fetching starship from Swapi with url: %s", url));
        return restTemplate.getForObject(url, Starship.class);
    }
}
