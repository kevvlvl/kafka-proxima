package org.kevvlvl.kafkaproxima.Deserializer;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;
import org.kevvlvl.kafkaproxima.model.Price;

public class PriceDeserializer extends JsonbDeserializer<Price> {

    public PriceDeserializer() {
        super(Price.class);
    }
}
