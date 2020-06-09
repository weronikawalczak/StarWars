package com.weronika.nask.swapi.dto;

import lombok.Data;

@Data
public class CharactersDTO {
    int count;
    CharacterDTO[] results;
}
