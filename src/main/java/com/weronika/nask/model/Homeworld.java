package com.weronika.nask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Homeworld {
    String name;
    int rotationPeriod;
    int orbitalPeriod;
    int diameter;
    String climate;
    String gravity;
    String terrain;
    int surfaceWater;
    int population;
}
