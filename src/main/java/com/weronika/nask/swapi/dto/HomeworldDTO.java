package com.weronika.nask.swapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HomeworldDTO {
    String name;
    int rotation_period;
    int orbital_period;
    int diameter;
    String climate;
    String gravity;
    String terrain;
    int surface_water;
    int population;
}
