package com.weronika.nask.client;

import com.weronika.nask.exception.ElementNotFoundException;
import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.Starship;
import com.weronika.nask.swapi.dto.CharacterDTO;
import com.weronika.nask.swapi.dto.CharactersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
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
        logger.debug(String.format("Fetching character by calling url: '%s'", url));

        try{
            return restTemplate.getForObject(url, CharacterDTO.class);
        }
        catch (HttpClientErrorException.NotFound e){
            logger.error(String.format("Error fetching character by calling url: '%s'", url));
            throw new ElementNotFoundException("Character", id);
        }
    }

    @Cacheable("characters")
    public CharactersDTO getCharactersByPage(int page){
        String url = String.format("%s/people/?page=%s", swapiPath, page);
        logger.debug(String.format("Fetching characters by calling url: '%s'", url));

        try{
            return restTemplate.getForObject(url, CharactersDTO.class);
        }
        catch (HttpClientErrorException.NotFound e){
            logger.error(String.format("Error fetching characters by calling url: '%s'", url));
            throw new ElementNotFoundException("Page", page);
        }
    }

    @Cacheable("homeworld")
    public Homeworld getHomeworld(int id){
        String url = String.format("%s/planets/%s/", swapiPath, id);
        logger.debug(String.format("Fetching homeworld from Swapi with url: %s", url));

        try{
            return restTemplate.getForObject(url, Homeworld.class);
        }
        catch (HttpClientErrorException.NotFound e){
            logger.error(String.format("Error fetching homeworld by calling url: '%s'", url));
            throw new ElementNotFoundException("Homeworld", id);
        }
    }

    @Cacheable("starship")
    public Starship getStarship(int id){
        String url = String.format("%s/starships/%s/", swapiPath, id);
        logger.debug(String.format("Fetching starship by calling url: '%s'", url));

        try{
            return restTemplate.getForObject(url, Starship.class);
        }
        catch (HttpClientErrorException.NotFound e){
            logger.error(String.format("Error fetching starship by calling url: '%s'", url));
            throw new ElementNotFoundException("Starship", id);
        }
    }
}

