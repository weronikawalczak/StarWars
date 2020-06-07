package com.weronika.nask.service;

import com.weronika.nask.client.StarwarsClient;
import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.swapi.dto.CharacterDTO;
import com.weronika.nask.swapi.dto.HomeworldDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StarwarsService {
    private final StarwarsClient starwarsClient;
    private final ModelMapper characterMapper;
    private final ModelMapper homeworldMapper;

    public StarwarsService(StarwarsClient starwarsClient,
                           @Qualifier("CharacterMapper") ModelMapper characterMapper,
                           @Qualifier("HomeworldMapper") ModelMapper homeworldMapper) {
        this.starwarsClient = starwarsClient;
        this.characterMapper = characterMapper;
        this.homeworldMapper = homeworldMapper;
    }

    public StarwarsCharacter getCharacter(String id){
        CharacterDTO characterDTO = starwarsClient.getCharacterById(id);
        String homeworldURL = characterDTO.getHomeworld();

        HomeworldDTO homeworldDTO = starwarsClient.getHomeworld(getLastDigitFromURL(homeworldURL));

        StarwarsCharacter starwarsCharacter = characterMapper.map(characterDTO, StarwarsCharacter.class);
        starwarsCharacter.setId(id);
        starwarsCharacter.setHomeworld(homeworldMapper.map(homeworldDTO, Homeworld.class));

        return starwarsCharacter;
    }

    private String getLastDigitFromURL(String url){
        String[] urlParts = url.split("/");
        return urlParts[urlParts.length-1];
    }

}
