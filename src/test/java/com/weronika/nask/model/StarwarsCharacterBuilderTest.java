package com.weronika.nask.model;

import com.weronika.nask.swapi.dto.CharacterDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StarwarsCharacterBuilderTest {

    private CharacterDTO characterDTO = new CharacterDTO();
    private final String HAIR_COLOR = "blonde";
    private final String SKIN_COLOR = "fair";
    private final String EYE_COLOR = "green";
    private final String BIRTH_YEAR = "1989";
    private final int ID = 3;
    private final String HOMEWORLD_URL = "http://swapi.dev/api/planets/1/";
    private final String[] STARSHIP_URLS = {"http://swapi.dev/api/starships/12/", "http://swapi.dev/api/starships/22/"};

    @Mock
    private Homeworld homeworld;

    @Mock
    private List<Starship> starships;


    @BeforeEach
    public void beforeEach() {
        characterDTO.setHair_color(HAIR_COLOR);
        characterDTO.setSkin_color(SKIN_COLOR);
        characterDTO.setEye_color(EYE_COLOR);
        characterDTO.setBirth_year(BIRTH_YEAR);
        characterDTO.setHomeworld(HOMEWORLD_URL);
        characterDTO.setStarships(STARSHIP_URLS);
    }

    @Test
    public void testMapper() {
        StarwarsCharacter result = new StarwarsCharacter.Builder().ofDTO(characterDTO).build();

        Assertions.assertEquals(BIRTH_YEAR, result.getBirthYear());
        Assertions.assertEquals(SKIN_COLOR, result.getSkinColor());
        Assertions.assertEquals(EYE_COLOR, result.getEyeColor());
        Assertions.assertEquals(BIRTH_YEAR, result.getBirthYear());
        Assertions.assertNull(result.getHomeworld());
        Assertions.assertNull(result.getStarships());
    }

    @Test
    public void testBuilder() {
        StarwarsCharacter result = new StarwarsCharacter.Builder()
                .ofDTO(characterDTO)
                .withHomeworld(homeworld)
                .withStarships(starships)
                .withId(ID)
                .build();

        Assertions.assertEquals(homeworld, result.getHomeworld());
        Assertions.assertEquals(starships, result.getStarships());
        Assertions.assertEquals(ID, result.getId());

    }

}