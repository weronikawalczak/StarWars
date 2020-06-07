package com.weronika.nask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Starship {
    String name;
    String model;
    String manufacturer;
    int costInCredits;
    int length;
    int maxAtmosphericSpeed;
    int crew;
    int passengers;
    int cargoCapacity;
    String consumables;
    float hyperdriveRating;
    int mglt;
    String starshipClass;
}
