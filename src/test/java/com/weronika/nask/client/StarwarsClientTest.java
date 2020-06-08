package com.weronika.nask.client;

import com.weronika.nask.swapi.dto.CharacterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StarwarsClientTest {

    @Mock
    private RestTemplate restTemplate;

    private final String SWAPI_PATH = "https://swapi.dev/api";

    private final int ID = 34;

    @InjectMocks
    private StarwarsClient subject;

    @BeforeEach
    public void setup() {
        subject = new StarwarsClient(restTemplate, SWAPI_PATH);
    }

    @Test
    void getCharacterById() {
        ArgumentCaptor acString = ArgumentCaptor.forClass(String.class);
        subject.getCharacterById(ID);
        //TODO use captor
        verify(restTemplate, times(1)).getForObject(eq("https://swapi.dev/api/people/34/"), eq(CharacterDTO.class));
    }

    @Test
    void getCharactersByPage() {
    }

    @Test
    void getHomeworld() {
    }

    @Test
    void getStarship() {
    }
}