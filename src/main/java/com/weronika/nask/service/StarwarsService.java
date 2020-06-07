package com.weronika.nask.service;

import com.weronika.nask.client.StarwarsClient;
import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.Starship;
import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.model.StarwarsCharacters;
import com.weronika.nask.swapi.dto.CharacterDTO;
import com.weronika.nask.swapi.dto.CharactersDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StarwarsService {
    private final StarwarsClient starwarsClient;
    private final ModelMapper characterMapper;

    public StarwarsService(StarwarsClient starwarsClient,
                           @Qualifier("CharacterMapper") ModelMapper characterMapper) {
        this.starwarsClient = starwarsClient;
        this.characterMapper = characterMapper;
    }

    public StarwarsCharacter getCharacter(int id){
        CharacterDTO characterDTO = starwarsClient.getCharacterById(id);
        String homeworldURL = characterDTO.getHomeworld();
        List<Starship> starships = getStarships(characterDTO);

        Homeworld homeworld = getHomeworld(homeworldURL);

        StarwarsCharacter starwarsCharacter = characterMapper.map(characterDTO, StarwarsCharacter.class);
        starwarsCharacter.setId(id);
        starwarsCharacter.setHomeworld(homeworld);
        starwarsCharacter.setStarships(starships);

        return starwarsCharacter;
    }

    public StarwarsCharacters getCharacters(int page){
        CharactersDTO starwarsCharactersDTO = starwarsClient.getCharactersByPage(page);
        StarwarsCharacters starwarsCharacters = new StarwarsCharacters();

        starwarsCharacters.setCount(starwarsCharactersDTO.getCount());

        starwarsCharacters.setPages(0);//TODO

        CharacterDTO[] charactersDTO = starwarsCharactersDTO.getResults();
        for(int i = 0; i < charactersDTO.length; i++){
            StarwarsCharacter starwarsCharacter = characterMapper.map(charactersDTO[i], StarwarsCharacter.class);
            starwarsCharacter.setId(i+1);
            starwarsCharacter.setHomeworld(getHomeworld(charactersDTO[i].getHomeworld()));
            starwarsCharacter.setStarships(getStarships(charactersDTO[i]));
            starwarsCharacters.addElement(starwarsCharacter);
        }

        return starwarsCharacters;
    }

    private Homeworld getHomeworld(String homeworldURL) {
        return starwarsClient.getHomeworld(getLastDigitFromURL(homeworldURL));
    }

    private List<Starship> getStarships(CharacterDTO characterDTO) {
        List<Starship> starships = new ArrayList<>();

        Arrays.asList(characterDTO.getStarships()).forEach(starshipUrl -> {
                    Starship starship = starwarsClient.getStarship(getLastDigitFromURL(starshipUrl));
                    starships.add(starship);
                }
        );
        return starships;
    }

    private String getLastDigitFromURL(String url){
        String[] urlParts = url.split("/");
        return urlParts[urlParts.length-1];
    }

    //TODO
    //getPagesCount
}
