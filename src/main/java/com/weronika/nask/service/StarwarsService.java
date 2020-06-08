package com.weronika.nask.service;

import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.model.StarwarsCharacters;

public interface StarwarsService {
    //TODO javaDocs
    // TODO integration test

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
    //TODO remove from that interface those two methods?
    int getPageCount();
    int getPageSize();
}
