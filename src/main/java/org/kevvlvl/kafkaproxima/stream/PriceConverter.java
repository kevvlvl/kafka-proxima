package org.kevvlvl.kafkaproxima.stream;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PriceConverter {

    @ConfigProperty(name = "application.conversion-rate")
    private double conversionRate;

    /**
     * Reads the numbers received in the topic "prices" and outputs the converted number in the stream "my-data-stream"
     * @param priceInUsd
     * @return
     */
    @Incoming("prices")
    @Outgoing("my-data-stream")
    @Broadcast
    public double process(int priceInUsd) {
        return priceInUsd * conversionRate;
    }
}
