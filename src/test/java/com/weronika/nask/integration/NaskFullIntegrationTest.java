package com.weronika.nask.integration;

import com.weronika.nask.controller.StarwarsController;
import com.weronika.nask.exception.ElementNotFoundException;
import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.model.StarwarsCharacters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NaskFullIntegrationTest {

	@Autowired
	StarwarsController starwarsController;

	@Value("${swapi.path}")
	String swapiPath;


	@Test
	void contextLoads() {
	}

	/**
	 * There is an error in Swapi
	 * Some characters are skipped by Ids in Swapi f.ex: https://swapi.dev/api/people/17/
	 * Therefore this test won't work for page greater than 2
	 */
	@Test
	void testCalculatedIdMatchesTheOneInSwapi(){
		StarwarsCharacters characters = starwarsController.getCharacters(2);
		StarwarsCharacter characterFromPage = characters.getElements().get(5);

		StarwarsCharacter character = starwarsController.getCharacter(characterFromPage.getId());

		Assertions.assertEquals(characterFromPage.getName(), character.getName());
	}

	@Test
	void testGetCharacterThrowsExceptionForNonExistingId(){
		Assertions.assertThrows(ElementNotFoundException.class, ()->starwarsController.getCharacter(159));
	}
}


