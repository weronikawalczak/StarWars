package com.weronika.nask.service;

import com.weronika.nask.client.StarwarsClient;
import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.Starship;
import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.model.StarwarsCharacters;
import com.weronika.nask.swapi.dto.CharacterDTO;
import com.weronika.nask.swapi.dto.CharactersDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StarwarsServiceTest {

    @Mock
    private StarwarsClient starwarsClient;

    @Mock
    private CharacterDTO characterDTO;

    @Mock
    private CharactersDTO charactersDTO;

    @Mock
    private Homeworld homeworld;

    @Mock
    private Starship starship;

    private final int TEST_HOMEWORLD_ID = 13;
    private final int TEST_STARSHIP_ID = 18;
    private final int FIRST_PAGE = 1;
    private final int TEST_PAGE = 3;

    private final String TEST_HOMEWORLD_URL = String.format("http://swapi.dev/api/planets/%s/", TEST_HOMEWORLD_ID);
    private final String[] TEST_STARSHIP_URLS = {String.format("http://swapi.dev/api/starships/%s/", TEST_STARSHIP_ID)};

    private CharacterDTO[] TEST_CHARACTER_DTOS;

    private final int TOTAL_ELEMENTS_COUNT = 82;

    @InjectMocks
    StarwarsServiceImpl subject;

    public StarwarsServiceTest() {
    }

    @BeforeEach
    public void beforeEach(){

        TEST_CHARACTER_DTOS = new CharacterDTO[] {characterDTO, characterDTO, characterDTO};
        when(characterDTO.getHomeworld()).thenReturn(TEST_HOMEWORLD_URL);
        when(starwarsClient.getHomeworld(TEST_HOMEWORLD_ID)).thenReturn(homeworld);
        when(characterDTO.getStarships()).thenReturn(TEST_STARSHIP_URLS);
        when(starwarsClient.getStarship(TEST_STARSHIP_ID)).thenReturn(starship);
    }

    @Test
    public void testGettingHomeworldOfCharacter(){

        Homeworld result = subject.getHomeworld(characterDTO);
        Assertions.assertEquals(homeworld, result);
    }

    @Test
    public void testGettingStarships(){

        List<Starship> result = subject.getStarships(characterDTO);
        Assertions.assertTrue(result.contains(starship));
    }

    @Test
    public void testGettingPageSize(){
        when(starwarsClient.getCharactersByPage(FIRST_PAGE)).thenReturn(charactersDTO);
        when(charactersDTO.getResults()).thenReturn(TEST_CHARACTER_DTOS);

        int result = subject.getPageSize();
        Assertions.assertEquals(charactersDTO.getResults().length, result);
    }

    @Test
    public void testGettingPageCount(){
        when(starwarsClient.getCharactersByPage(FIRST_PAGE)).thenReturn(charactersDTO);
        when(charactersDTO.getResults()).thenReturn(TEST_CHARACTER_DTOS);
        when(charactersDTO.getCount()).thenReturn(TOTAL_ELEMENTS_COUNT);

        int result = subject.getPageCount();
        Assertions.assertEquals(28, result);
    }

    @Test
    public void testGettingCharacterAssignsId(){
        when(starwarsClient.getCharacterById(TEST_PAGE)).thenReturn(characterDTO);

        StarwarsCharacter result = subject.getCharacter(TEST_PAGE);

        Assertions.assertEquals(TEST_PAGE, result.getId());
    }

    @Test
    public void testGettingStarwarsCharacters(){
       when(starwarsClient.getCharactersByPage(TEST_PAGE)).thenReturn(charactersDTO);
       when(charactersDTO.getResults()).thenReturn(TEST_CHARACTER_DTOS);
       when(charactersDTO.getCount()).thenReturn(TOTAL_ELEMENTS_COUNT);
       when(starwarsClient.getCharactersByPage(FIRST_PAGE)).thenReturn(charactersDTO);
       when(starwarsClient.getCharactersByPage(FIRST_PAGE)).thenReturn(charactersDTO);

       StarwarsCharacters result = subject.getCharacters(TEST_PAGE);

       Assertions.assertEquals(TEST_CHARACTER_DTOS.length, result.getElements().size());
       Assertions.assertEquals(TOTAL_ELEMENTS_COUNT, result.getCount());
    }
}