package com.weronika.nask.integration;

import com.weronika.nask.model.Homeworld;
import com.weronika.nask.service.StarwarsService;
import com.weronika.nask.swapi.dto.CharacterDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class NaskInternalTest {

	@Autowired
	StarwarsService starwarsService;

	@Autowired
	RestTemplate restTemplate;

	@Value("${swapi.path}")
	String swapiPath;

	@Mock
	CharacterDTO characterDTO;

	@Mock
	Homeworld homeworld;

	@Test
	void testCacheOnHomeworld(){
		int homeworldId = 22;
		final String homeworldPath = String.format("%s/planets/%s/", swapiPath, homeworldId);

		when(characterDTO.getHomeworld()).thenReturn(homeworldPath);
		when(restTemplate.getForObject(homeworldPath, Homeworld.class)).thenReturn(homeworld);

		starwarsService.getHomeworld(characterDTO);
		starwarsService.getHomeworld(characterDTO);

		verify(restTemplate, times(1)).getForObject(homeworldPath, Homeworld.class);
	}
}
