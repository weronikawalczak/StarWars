package com.weronika.nask.service;

import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.model.StarwarsCharacters;

public interface StarwarsService {
    //TODO javaDocs
    StarwarsCharacter getCharacter(int id);
    StarwarsCharacters getCharacters(int id);
    int getPageCount();
    int getPageSize();
}
