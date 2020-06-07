package com.weronika.nask.service;

import com.weronika.nask.client.StarwarsClient;
import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.swapi.dto.CharacterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StarwarsService {
    private final StarwarsClient starwarsClient;
    private final ModelMapper modelMapper;

    public StarwarsService(StarwarsClient starwarsClient, ModelMapper modelMapper) {
        this.starwarsClient = starwarsClient;
        this.modelMapper = modelMapper;
    }

    public StarwarsCharacter getCharacter(String id){
        CharacterDTO characterDTO = starwarsClient.getCharacterById(id);
        String homeworldURL = characterDTO.getHomeworld();

        //Homeworld homeworld = starwarsClient.getHomeworld(homeworldURL);

        StarwarsCharacter starwarsCharacter = modelMapper.map(characterDTO, StarwarsCharacter.class);
        starwarsCharacter.setId(id);

        return starwarsCharacter;
    }

}
