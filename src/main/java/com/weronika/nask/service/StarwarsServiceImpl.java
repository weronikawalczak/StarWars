package com.weronika.nask.service;

import com.weronika.nask.client.StarwarsClient;
import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.Starship;
import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.model.StarwarsCharacters;
import com.weronika.nask.swapi.dto.CharacterDTO;
import com.weronika.nask.swapi.dto.CharactersDTO;
import com.weronika.nask.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StarwarsServiceImpl implements StarwarsService {
    private final StarwarsClient starwarsClient;
    private final ModelMapper characterMapper;

    public StarwarsServiceImpl(StarwarsClient starwarsClient,
                               @Qualifier("CharacterMapper") ModelMapper characterMapper) {
        this.starwarsClient = starwarsClient;
        this.characterMapper = characterMapper;
    }

    @Override
    public StarwarsCharacter getCharacter(int id){
        CharacterDTO characterDTO = starwarsClient.getCharacterById(id);
        return mapToStarwarsCharacter(characterDTO, id);
    }

    @Override
    public StarwarsCharacters getCharacters(int page){
        CharactersDTO starwarsCharactersDTO = starwarsClient.getCharactersByPage(page);
        StarwarsCharacters starwarsCharacters = new StarwarsCharacters();

        CharacterDTO[] charactersDTO = starwarsCharactersDTO.getResults();
        for(int i = 0; i < charactersDTO.length; i++){
            StarwarsCharacter starwarsCharacter = mapToStarwarsCharacter(charactersDTO[i], calculateCharacterId(page, i));
            starwarsCharacters.addElement(starwarsCharacter);
        }

        starwarsCharacters.setCount(starwarsCharactersDTO.getCount());
        starwarsCharacters.setPages(getPageCount());
        return starwarsCharacters;
    }

    @Override
    public int getPageCount(){
        CharactersDTO charactersDTO = getFirstPage();
        int elementsOnFirstPage = charactersDTO.getResults().length;
        int allElementsCount = charactersDTO.getCount();

        return Util.getCeilOfDivision(allElementsCount, elementsOnFirstPage);
    }

    @Override
    public int getPageSize(){
        CharactersDTO charactersDTO = getFirstPage();
        return charactersDTO.getResults().length;
    }

    private Homeworld getHomeworld(CharacterDTO characterDTO) {
        String homeworldURL = characterDTO.getHomeworld();
        return starwarsClient.getHomeworld(Util.getLastDigitFromURL(homeworldURL));
    }

    private List<Starship> getStarships(CharacterDTO characterDTO) {
        List<Starship> starships = new ArrayList<>();

        Arrays.asList(characterDTO.getStarships()).forEach(starshipUrl -> {
                    Starship starship = starwarsClient.getStarship(Util.getLastDigitFromURL(starshipUrl));
                    starships.add(starship);
                }
        );
        return starships;
    }

    private CharactersDTO getFirstPage(){
        return starwarsClient.getCharactersByPage(1);
    }

    private int calculateCharacterId(int page, int elementIndex){
        return getPageSize() * (page - 1) + (elementIndex+1);
    }

    private StarwarsCharacter mapToStarwarsCharacter(CharacterDTO characterDTO, int id){
        StarwarsCharacter starwarsCharacter = characterMapper.map(characterDTO, StarwarsCharacter.class);
        starwarsCharacter.setHomeworld(getHomeworld(characterDTO));
        starwarsCharacter.setStarships(getStarships(characterDTO));
        starwarsCharacter.setId(id);
        return starwarsCharacter;
    }

    //TODO do we really need to calculate pageSize and pageCount manually? Let's read more in the docs

}
