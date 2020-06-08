package com.weronika.nask.service;

import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.Starship;
import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.model.StarwarsCharacters;
import com.weronika.nask.swapi.dto.CharacterDTO;

import java.util.List;

public interface StarwarsService {
    /**
     * Returns a single Star Wars character from Swapi.
     * This method throws a NotFoundException if a Star Wars character was not found.
     *
     * @param  id  a unique id of a Star Wars character
     * @return      StarwarsCharacter
     */
    StarwarsCharacter getCharacter(int id);

    /**
     * Returns a list of Star Wars characters in a specified page.
     * This method throws a NotFoundException if there is no such page.
     *
     * @param  page number of page
     * @return      StarwarsCharacters
     */
    StarwarsCharacters getCharacters(int page);

    /**
     * Returns a Homeworld of a Character.
     * This method throws a NotFoundException if character's Homeworld could not be found.
     *
     * @param  characterDTO character
     * @return      Homeworld
     */
    Homeworld getHomeworld(CharacterDTO characterDTO);

    /**
     * Returns a list of character's starships.
     * This method throws a NotFoundException if there is no such starship.
     *
     * @param  characterDTO character
     * @return      List of starships
     */
    List<Starship> getStarships(CharacterDTO characterDTO);

    /**
     * Returns total number of Star Wars characters pages.
     * @return      number of pages
     */
    int getPageCount();

    /**
     * Returns max count of Star Wars characters on a single page.
     * @return      max count of Star Wars characters
     */
    int getPageSize();
}
