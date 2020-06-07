package com.weronika.nask.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Homeworld {
    String name;

    @JsonProperty("rotation_period")
    String rotationPeriod;

    @JsonProperty("orbital_period")
    String orbitalPeriod;

    String diameter;
    String climate;
    String gravity;
    String terrain;

    @JsonProperty("surface_water")
    String surfaceWater;

    String population;
}
