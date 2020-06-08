package com.weronika.nask.service;

import com.weronika.nask.client.StarwarsClient;
import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.Starship;
import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.model.StarwarsCharacters;
import com.weronika.nask.swapi.dto.CharacterDTO;
import com.weronika.nask.swapi.dto.CharactersDTO;
import com.weronika.nask.util.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StarwarsServiceImpl implements StarwarsService {
    private final StarwarsClient starwarsClient;

    public StarwarsServiceImpl(StarwarsClient starwarsClient) {
        this.starwarsClient = starwarsClient;
    }

    @Override
    public StarwarsCharacter getCharacter(int id){
        CharacterDTO characterDTO = starwarsClient.getCharacterById(id);
        return new StarwarsCharacter.Builder().ofDTO(characterDTO)
                .withId(id)
                .withHomeworld(getHomeworld(characterDTO))
                .withStarships(getStarships(characterDTO))
                .build();
    }

    @Override
    public StarwarsCharacters getCharacters(int page){
        CharactersDTO starwarsCharactersDTO = starwarsClient.getCharactersByPage(page);
        StarwarsCharacters starwarsCharacters = new StarwarsCharacters();

        CharacterDTO[] characterDTOs = starwarsCharactersDTO.getResults();
        for(int i = 0; i < characterDTOs.length; i++){
            StarwarsCharacter starwarsCharacter = new StarwarsCharacter.Builder().ofDTO(characterDTOs[i])
                    .withId(Util.calculateCharacterIdByPage(getPageSize(), page, i+1))
                    .withHomeworld(getHomeworld(characterDTOs[i]))
                    .withStarships(getStarships(characterDTOs[i]))
                    .build();

            starwarsCharacters.addElement(starwarsCharacter);
        }

        starwarsCharacters.setCount(starwarsCharactersDTO.getCount());
        starwarsCharacters.setPages(getPageCount());
        return starwarsCharacters;
    }

    /**
     * Because of Swapi providing only count of all characters, this method needs to calculate pages count based on first page.
     */
    @Override
    public int getPageCount(){
        CharactersDTO charactersDTO = getFirstPage();
        int elementsOnFirstPage = charactersDTO.getResults().length;
        int allElementsCount = charactersDTO.getCount();

        return Util.getCeilOfDivision(allElementsCount, elementsOnFirstPage);
    }

    /**
     * Because of Swapi providing only count of all characters, this method needs to calculate page size based on first page.
     */
    @Override
    public int getPageSize(){
        CharactersDTO charactersDTO = getFirstPage();
        return charactersDTO.getResults().length;
    }

    @Override
    public Homeworld getHomeworld(CharacterDTO characterDTO) {
        String homeworldURL = characterDTO.getHomeworld();
        return starwarsClient.getHomeworld(Util.getLastDigitFromURL(homeworldURL));
    }

    @Override
    public List<Starship> getStarships(CharacterDTO characterDTO) {
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
}
