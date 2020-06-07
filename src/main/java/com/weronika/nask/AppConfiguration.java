package com.weronika.nask;

import com.weronika.nask.model.Homeworld;
import com.weronika.nask.model.StarwarsCharacter;
import com.weronika.nask.swapi.dto.CharacterDTO;
import com.weronika.nask.swapi.dto.HomeworldDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean(name="CharacterMapper")
    public ModelMapper characterModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(CharacterDTO.class, StarwarsCharacter.class).addMappings(mapper -> {
            mapper.map(CharacterDTO::getHair_color, StarwarsCharacter::setHairColor);
            mapper.map(CharacterDTO::getSkin_color, StarwarsCharacter::setSkinColor);
            mapper.map(CharacterDTO::getEye_color, StarwarsCharacter::setEyeColor);
            mapper.map(CharacterDTO::getBirth_year, StarwarsCharacter::setBirthYear);
            mapper.skip(StarwarsCharacter::setHomeworld);
        });

        return modelMapper;
    }

    @Bean(name="HomeworldMapper")
    public ModelMapper homeworldModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(HomeworldDTO.class, Homeworld.class).addMappings(mapper -> {
            mapper.map(HomeworldDTO::getRotation_period, Homeworld::setRotationPeriod);
            mapper.map(HomeworldDTO::getOrbital_period, Homeworld::setOrbitalPeriod);
            mapper.map(HomeworldDTO::getSurface_water, Homeworld::setSurfaceWater);
        });

        return modelMapper;
    }
}
