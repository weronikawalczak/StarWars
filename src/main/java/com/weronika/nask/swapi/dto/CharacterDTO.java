package com.weronika.nask.swapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CharacterDTO {
    String id;
    String name;
    int height;
    int mass;
    String hair_color;
    String skin_color;
    String eye_color;
    String birth_year;
    String gender;
    String homeworld;
    //List<String> starships;
}
