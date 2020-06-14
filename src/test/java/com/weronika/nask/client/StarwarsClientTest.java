package com.weronika.nask.client;

import com.weronika.nask.exception.ElementNotFoundException;
import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.Starship;
import com.weronika.nask.swapi.dto.CharacterDTO;
import com.weronika.nask.swapi.dto.CharactersDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StarwarsClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CharacterDTO characterDTO;

    @Mock
    private CharactersDTO charactersDTO;

    @Mock
    private Homeworld homeworld;

    @Mock
    private Starship starship;

    private final String SWAPI_PATH = "https://swapi.dev/api";

    private final int ID = 34;
    private final int PAGE = 3;

    private StarwarsClient subject;

    @BeforeEach
    public void setup() {
        subject = new StarwarsClient(restTemplate, SWAPI_PATH);
    }

    @Test
    void getCharacterById() {
        String path = String.format("%s/people/%s/",SWAPI_PATH, ID);
        when(restTemplate.getForObject(path, CharacterDTO.class)).thenReturn(characterDTO);

        CharacterDTO result = subject.getCharacterById(ID);

        verify(restTemplate, times(1)).getForObject(path, CharacterDTO.class);
        Assertions.assertEquals(characterDTO, result);
    }

    @Test
    void testGetCharacterByIdThrowsElementNotFoundException(){
        String path = String.format("%s/people/%s/",SWAPI_PATH, ID);
        when(restTemplate.getForObject(path, CharacterDTO.class)).thenThrow(HttpClientErrorException.NotFound.class);
        Assertions.assertThrows(ElementNotFoundException.class, () -> subject.getCharacterById(ID));
    }

    @Test
    void testGetCharactersByPage() {
        String path = String.format("%s/people/?page=%s",SWAPI_PATH, PAGE);
        when(restTemplate.getForObject(path, CharactersDTO.class)).thenReturn(charactersDTO);

        CharactersDTO result = subject.getCharactersByPage(PAGE);

        verify(restTemplate, times(1)).getForObject(path, CharactersDTO.class);
        Assertions.assertEquals(charactersDTO, result);
    }

    @Test
    void testGetCharactersByPageThrowsElementNotFoundException(){
        String path = String.format("%s/people/?page=%s",SWAPI_PATH, PAGE);
        when(restTemplate.getForObject(path, CharactersDTO.class)).thenThrow(HttpClientErrorException.NotFound.class);
        Assertions.assertThrows(ElementNotFoundException.class, () -> subject.getCharactersByPage(PAGE));
    }

    @Test
    void testGetHomeworld() {
        String path = String.format("%s/planets/%s/",SWAPI_PATH, ID);
        when(restTemplate.getForObject(path, Homeworld.class)).thenReturn(homeworld);

        Homeworld result = subject.getHomeworld(ID);

        verify(restTemplate, times(1)).getForObject(path, Homeworld.class);
        Assertions.assertEquals(homeworld, result);
    }

    @Test
    void getHomeworldThrowsElementNotFoundException() {
        String path = String.format("%s/planets/%s/",SWAPI_PATH, ID);
        when(restTemplate.getForObject(path, Homeworld.class)).thenThrow(HttpClientErrorException.NotFound.class);
        Assertions.assertThrows(ElementNotFoundException.class, () -> subject.getHomeworld(ID));
    }

    @Test
    void testGetStarship() {
        String path = String.format("%s/starships/%s/",SWAPI_PATH, ID);
        when(restTemplate.getForObject(path, Starship.class)).thenReturn(starship);

        Starship result = subject.getStarship(ID);

        verify(restTemplate, times(1)).getForObject(path, Starship.class);
        Assertions.assertEquals(starship, result);
    }

    @Test
    void testGetStarshipThrowsElementNotFoundException() {
        String path = String.format("%s/starships/%s/",SWAPI_PATH, ID);
        when(restTemplate.getForObject(path, Starship.class)).thenThrow(HttpClientErrorException.NotFound.class);
        Assertions.assertThrows(ElementNotFoundException.class, () -> subject.getStarship(ID));
    }
}