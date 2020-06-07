package com.weronika.nask.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StarwarsCharacters {
    int count;
    int pages;
    List<StarwarsCharacter> elements = new ArrayList<>();

    public void addElement(StarwarsCharacter starwarsCharacter){
        elements.add(starwarsCharacter);
    }
}
