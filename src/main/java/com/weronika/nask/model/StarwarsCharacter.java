package com.weronika.nask.model;

import com.weronika.nask.swapi.dto.CharacterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StarwarsCharacter {
    int id;
    String name;
    String height;
    String mass;
    String hairColor;
    String skinColor;
    String eyeColor;
    String birthYear;
    String gender;
    Homeworld homeworld;
    List<Starship> starships;

    public static class Builder {
        private int id;
        private CharacterDTO characterDTO;
        private Homeworld homeworld;
        private List<Starship> starships;
        private static ModelMapper modelMapper;

        public Builder ofDTO(CharacterDTO characterDTO){
            this.characterDTO = characterDTO;
            return this;
        }

        public Builder withId(int id){
            this.id = id;
            return this;
        }

        public Builder withHomeworld(Homeworld homeworld){
            this.homeworld = homeworld;
            return this;
        }

        public Builder withStarships(List<Starship> starships){
            this.starships = starships;
            return this;
        }

        public StarwarsCharacter build(){
            StarwarsCharacter starwarsCharacter = getModelMapper().map(this.characterDTO, StarwarsCharacter.class);
            starwarsCharacter.setHomeworld(this.homeworld);
            starwarsCharacter.setStarships(this.starships);
            starwarsCharacter.setId(this.id);
            return starwarsCharacter;
        }

        private ModelMapper getModelMapper(){
            if (modelMapper == null) {
                modelMapper = new ModelMapper();
                modelMapper.typeMap(CharacterDTO.class, StarwarsCharacter.class).addMappings(mapper -> {
                    mapper.map(CharacterDTO::getHair_color, StarwarsCharacter::setHairColor);
                    mapper.map(CharacterDTO::getSkin_color, StarwarsCharacter::setSkinColor);
                    mapper.map(CharacterDTO::getEye_color, StarwarsCharacter::setEyeColor);
                    mapper.map(CharacterDTO::getBirth_year, StarwarsCharacter::setBirthYear);
                    mapper.skip(StarwarsCharacter::setHomeworld);
                    mapper.skip(StarwarsCharacter::setStarships);
                });
            }
            return modelMapper;
        }
    }
}
