package com.weronika.nask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StarwarsCharacter {
    String id;
    String name;
    int height;
    int mass;
    String hairColor;
    String skinColor;
    String eyeColor;
    String birthYear;
    String gender;
    Homeworld homeworld;
    //List<Starship> starships;
}
