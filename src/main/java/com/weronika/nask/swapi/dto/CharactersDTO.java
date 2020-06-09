package com.weronika.nask.swapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CharactersDTO {
    int count;
    CharacterDTO[] results;
}
