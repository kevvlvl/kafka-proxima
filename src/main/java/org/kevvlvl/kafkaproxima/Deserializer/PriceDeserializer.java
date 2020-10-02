package org.kevvlvl.kafkaproxima.Deserializer;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.kevvlvl.kafkaproxima.model.Price;

public class PriceDeserializer extends ObjectMapperDeserializer<Price> {

    public PriceDeserializer() {
        super(Price.class);
    }
}
