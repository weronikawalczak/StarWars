package com.weronika.nask.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    String crew;
    String passengers;
    String length;
    String consumables;

    @JsonProperty("cost_in_credits")
    String costInCredits;

    @JsonProperty("max_atmosphering_speed")
    String maxAtmosphericSpeed;

    @JsonProperty("cargo_capacity")
    String cargoCapacity;

    @JsonProperty("hyperdrive_rating")
    String hyperdriveRating;

    @JsonProperty("MGLT")
    String mglt;

    @JsonProperty("starship_class")
    String starshipClass;
}
