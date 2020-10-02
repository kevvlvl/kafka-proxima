package org.kevvlvl.kafkaproxima.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private double priceUsd;
    private double priceCad;
}
