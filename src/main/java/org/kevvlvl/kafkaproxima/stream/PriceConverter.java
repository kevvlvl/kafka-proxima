package org.kevvlvl.kafkaproxima.stream;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.kevvlvl.kafkaproxima.model.Price;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class PriceConverter {

    @ConfigProperty(name = "application.usd-cad-rate")
    double conversionRate;

    /**
     * Reads the numbers received in the topic "prices" and outputs the converted number in the Outgoing stream to all subscribers (Broadcast annotation)
     * @param price
     * @return
     */
    @Incoming("prices")
    @Outgoing("price-cad-data")
    @Broadcast
    public Price process(Price price) {

        log.info("process() - price to convert {}", price);

        price.setPriceCad(price.getPriceUsd() * conversionRate);

        return price;
    }
}
